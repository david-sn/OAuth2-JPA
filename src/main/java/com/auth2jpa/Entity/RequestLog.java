/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "request_log")
@NamedQueries({
    @NamedQuery(name = "RequestLog.findAll", query = "SELECT r FROM RequestLog r")})
public class RequestLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dbid")
    private Integer dbid;
    @Size(max = 255)
    @Column(name = "request_uuid")
    private String requestUuid;
    @Size(max = 255)
    @Column(name = "request_method")
    private String requestMethod;
    @Size(max = 255)
    @Column(name = "request_path_info")
    private String requestPathInfo;
    @Size(max = 500)
    @Column(name = "request_parameters")
    private String requestParameters;
    @Size(max = 255)
    @Column(name = "request_body")
    private String requestBody;
    @Size(max = 255)
    @Column(name = "request_url")
    private String requestUrl;
    @Size(max = 255)
    @Column(name = "request_servlet_path")
    private String requestServletPath;
    @Size(max = 255)
    @Column(name = "request_context_path")
    private String requestContextPath;
    @Column(name = "request_content_lenth")
    private Integer requestContentLenth;
    @Size(max = 255)
    @Column(name = "request_content_type")
    private String requestContentType;
    @Size(max = 255)
    @Column(name = "remote_host")
    private String remoteHost;
    @Size(max = 255)
    @Column(name = "remote_Address")
    private String remoteAddress;
    @Column(name = "remote_port")
    private Integer remotePort;
    @Size(max = 255)
    @Column(name = "remote_user")
    private String remoteUser;
    @Size(max = 255)
    @Column(name = "schema_")
    private String schema_;
    @Size(max = 255)
    @Column(name = "server_name")
    private String serverName;
    @Column(name = "server_port")
    private Integer serverPort;
    @Size(max = 255)
    @Column(name = "protocol")
    private String protocol;
    @Size(max = 10000)
    @Column(name = "headers")
    private String headers;
    @Column(name = "response_status")
    private Integer responseStatus;
    @Column(name = "response_buffer_size")
    private Integer responseBufferSize;
    @Size(max = 255)
    @Column(name = "response_charachter_encoding")
    private String responseCharachterEncoding;
    @Size(max = 255)
    @Column(name = "response_uuid")
    private String responseUuid;
    @Size(max = 1000)
    @Column(name = "response_header")
    private String responseHeader;
    @Size(max = 255)
    @Column(name = "response_content_type")
    private String responseContentType;
    @Lob
    @Column(name = "response_body")
    private String responseBody;

    public RequestLog() {
    }

    public RequestLog(Integer dbid) {
        this.dbid = dbid;
    }

    public RequestLog(String requestUuid, String requestMethod, String requestPathInfo, String requestParameters, String requestBody, String requestUrl, String requestServletPath, String requestContextPath, Integer requestContentLenth, String requestContentType, String remoteHost, String remoteAddress, Integer remotePort, String remoteUser, String schema, String serverName, Integer serverPort, String protocol, String headers) {
        this.requestUuid = requestUuid;
        this.requestMethod = requestMethod;
        this.requestPathInfo = requestPathInfo;
        this.requestParameters = requestParameters;
        this.requestBody = requestBody;
        this.requestUrl = requestUrl;
        this.requestServletPath = requestServletPath;
        this.requestContextPath = requestContextPath;
        this.requestContentLenth = requestContentLenth;
        this.requestContentType = requestContentType;
        this.remoteHost = remoteHost;
        this.remoteAddress = remoteAddress;
        this.remotePort = remotePort;
        this.remoteUser = remoteUser;
        this.schema_ = schema;
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.protocol = protocol;
        this.headers = headers;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPathInfo() {
        return requestPathInfo;
    }

    public void setRequestPathInfo(String requestPathInfo) {
        this.requestPathInfo = requestPathInfo;
    }

    public String getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(String requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestServletPath() {
        return requestServletPath;
    }

    public void setRequestServletPath(String requestServletPath) {
        this.requestServletPath = requestServletPath;
    }

    public String getRequestContextPath() {
        return requestContextPath;
    }

    public void setRequestContextPath(String requestContextPath) {
        this.requestContextPath = requestContextPath;
    }

    public Integer getRequestContentLenth() {
        return requestContentLenth;
    }

    public void setRequestContentLenth(Integer requestContentLenth) {
        this.requestContentLenth = requestContentLenth;
    }

    public String getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(String requestContentType) {
        this.requestContentType = requestContentType;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getSchema() {
        return schema_;
    }

    public void setSchema(String schema) {
        this.schema_ = schema;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbid != null ? dbid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestLog)) {
            return false;
        }
        RequestLog other = (RequestLog) object;
        if ((this.dbid == null && other.dbid != null) || (this.dbid != null && !this.dbid.equals(other.dbid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.auth2jpa.Entity.RequestLog[ dbid=" + dbid + " ]";
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Integer getResponseBufferSize() {
        return responseBufferSize;
    }

    public void setResponseBufferSize(Integer responseBufferSize) {
        this.responseBufferSize = responseBufferSize;
    }

    public String getResponseCharachterEncoding() {
        return responseCharachterEncoding;
    }

    public void setResponseCharachterEncoding(String responseCharachterEncoding) {
        this.responseCharachterEncoding = responseCharachterEncoding;
    }

    public String getResponseUuid() {
        return responseUuid;
    }

    public void setResponseUuid(String responseUuid) {
        this.responseUuid = responseUuid;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType) {
        this.responseContentType = responseContentType;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

}
