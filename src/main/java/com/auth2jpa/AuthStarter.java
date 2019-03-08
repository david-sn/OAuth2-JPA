package com.auth2jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class AuthStarter {

//    @Autowired
//    private ApiFilter apiFilter;
//
//    public FilterRegistrationBean apiFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(apiFilter);
//        registration.setEnabled(true);       
//        // In case you want the filter to apply to specific URL patterns only
//        registration.addUrlPatterns("/**");
//        return registration;
//    }
    public static void main(String[] args) {
        SpringApplication.run(AuthStarter.class, args);
    }
}
