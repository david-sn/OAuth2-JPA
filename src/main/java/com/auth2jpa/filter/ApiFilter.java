/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.filter;

import com.auth2jpa.Entity.RequestLog;
import com.auth2jpa.Repository.RequestLogRepository;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class ApiFilter extends GenericFilterBean {

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sr;
        String requestUUI = null;
        //first check header in reques it has UUID header or not
        if (httpServletRequest.getHeader("UUID") == null) {
            //raise Error UUID not Found
            HttpServletResponse response = (HttpServletResponse) sr1;
            response.sendError(HttpStatus.NOT_FOUND.value(), "ERROR UUID HEADER_NOT_FOUND");
            return;
        } else {
            //get actual UUID
            requestUUI = httpServletRequest.getHeader("UUID");
            //validate UUID pattern
            boolean isMatchedPattern = Pattern.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}", requestUUI);
            //select by this UUID from Database if found raise error it must be unique value
            boolean isMatchedDB = requestLogRepository.findByRequestUuidOrResponseUuid(requestUUI, requestUUI) == null;
            if (!isMatchedPattern && !isMatchedDB) {
                HttpServletResponse response = (HttpServletResponse) sr1;
                response.sendError(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), "ERROR INVALID_UUID_HEADER_TRY_ANOTHER_ONE");
                return;
            }
        }

        //get request information
        BufferedRequestWrapper bufferedReqest = new BufferedRequestWrapper(httpServletRequest);
        StringBuilder headers = new StringBuilder("{");

        //get header from request
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            headers.append("\"");
            String key = headerNames.nextElement().toString();
            headers.append(key).append("\" : \"");
            headers.append(httpServletRequest.getHeader(key));
            headers.append("\",");
        }
        //save request data
        RequestLog log = new RequestLog(
                requestUUI, httpServletRequest.getMethod(), httpServletRequest.getPathInfo(), this.getTypesafeRequestMap(httpServletRequest).toString(),
                bufferedReqest.getRequestBody(), httpServletRequest.getRequestURI(), httpServletRequest.getServletPath(), httpServletRequest.getContextPath(),
                httpServletRequest.getContentLength(), httpServletRequest.getContentType(), httpServletRequest.getRemoteHost(), httpServletRequest.getRemoteAddr(), httpServletRequest.getRemotePort(),
                httpServletRequest.getRemoteUser(), httpServletRequest.getScheme(), httpServletRequest.getServerName(), httpServletRequest.getServerPort(), httpServletRequest.getProtocol(),
                headers.append("}").toString()
        );
        RequestLog logSaved = requestLogRepository.save(log);

        HttpServletResponse response = (HttpServletResponse) sr1;
        ByteArrayPrintWriter pw = new ByteArrayPrintWriter();
        HttpServletResponse wrappedResp = new HttpServletResponseWrapper(response) {
            public PrintWriter getWriter() {
                return pw.getWriter();
            }

            public ServletOutputStream getOutputStream() {
                return pw.getStream();
            }

        };

        fc.doFilter(sr, wrappedResp);

        //add custom header in response UUID header
        String responseUUID = UUID.randomUUID().toString();
        response.addHeader("UUID", responseUUID);

        //wirte acutal response
        response.getOutputStream().write(pw.toByteArray());

        //get header from response
        headers = new StringBuilder("{");
        for (String headerName : response.getHeaderNames()) {
            headers.append("\"").append(headerName).append("\":\"");
            headers.append(response.getHeader(headerName));
            headers.append("\",");
        }
        //save response date
        logSaved.setResponseUuid(responseUUID);
        logSaved.setResponseBufferSize(response.getBufferSize());
        logSaved.setResponseCharachterEncoding(response.getCharacterEncoding());
        logSaved.setResponseStatus(response.getStatus());
        logSaved.setResponseHeader(headers.append("}").toString());
        logSaved.setResponseBody(new String(pw.toByteArray()));
        requestLogRepository.saveAndFlush(logSaved);

    }

    private Map<String, String> getTypesafeRequestMap(HttpServletRequest request) {
        Map<String, String> typesafeRequestMap = new HashMap<>();
        Enumeration<?> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            String requestParamName = (String) requestParamNames.nextElement();
            String requestParamValue = request.getParameter(requestParamName);
            typesafeRequestMap.put(requestParamName, requestParamValue);
        }
        return typesafeRequestMap;
    }

    private class BufferedRequestWrapper extends HttpServletRequestWrapper {

        private ByteArrayInputStream bais = null;
        private ByteArrayOutputStream baos = null;
        private BufferedServletInputStream bsis = null;
        private byte[] buffer = null;

        public BufferedRequestWrapper(HttpServletRequest req) throws IOException {
            super(req);
            // Read InputStream and store its content in a buffer.
            InputStream is = req.getInputStream();
            this.baos = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int letti;
            while ((letti = is.read(buf)) > 0) {
                this.baos.write(buf, 0, letti);
            }
            this.buffer = this.baos.toByteArray();
        }

        @Override
        public ServletInputStream getInputStream() {
            this.bais = new ByteArrayInputStream(this.buffer);
            this.bsis = new BufferedServletInputStream(this.bais);
            return this.bsis;
        }

        String getRequestBody() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
            String line = null;
            StringBuilder inputBuffer = new StringBuilder();
            do {
                line = reader.readLine();
                if (null != line) {
                    inputBuffer.append(line.trim());
                }
            } while (line != null);
            reader.close();
            return inputBuffer.toString().trim();
        }

    }

    private class BufferedServletInputStream extends ServletInputStream {

        private ByteArrayInputStream bais;

        public BufferedServletInputStream(ByteArrayInputStream bais) {
            this.bais = bais;
        }

        @Override
        public int available() {
            return this.bais.available();
        }

        @Override
        public int read() {
            return this.bais.read();
        }

        @Override
        public int read(byte[] buf, int off, int len) {
            return this.bais.read(buf, off, len);
        }

        @Override
        public boolean isFinished() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isReady() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setReadListener(ReadListener rl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class ByteArrayServletStream extends ServletOutputStream {

        ByteArrayOutputStream baos;

        ByteArrayServletStream(ByteArrayOutputStream baos) {
            this.baos = baos;
        }

        public void write(int param) throws IOException {
            baos.write(param);
        }

        @Override
        public boolean isReady() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setWriteListener(WriteListener wl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class ByteArrayPrintWriter {

        private ByteArrayOutputStream baos = new ByteArrayOutputStream();

        private PrintWriter pw = new PrintWriter(baos);

        private ServletOutputStream sos = new ByteArrayServletStream(baos);

        public PrintWriter getWriter() {
            return pw;
        }

        public ServletOutputStream getStream() {
            return sos;
        }

        byte[] toByteArray() {
            return baos.toByteArray();
        }
    }

}
