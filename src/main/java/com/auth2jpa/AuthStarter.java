package com.auth2jpa;

import com.auth2jpa.filter.ApiFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class AuthStarter {

    @Autowired
    private ApiFilter apiFilter;

    public FilterRegistrationBean apiFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(apiFilter);
        registration.setEnabled(true);       
        // In case you want the filter to apply to specific URL patterns only
        registration.addUrlPatterns("/**");
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthStarter.class, args);
    }
}
