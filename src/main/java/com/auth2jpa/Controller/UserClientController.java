/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Controller;

import com.auth2jpa.Entity.OAuthClient;
import com.auth2jpa.Entity.SystemRoles;
import com.auth2jpa.Entity.Users;
import com.auth2jpa.Repository.UserRepository;
import com.auth2jpa.Service.ClientService;
import com.auth2jpa.Service.UserServiceImpl;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class UserClientController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ClientService clientService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthorizationServerEndpointsConfiguration configuration;

    
      @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    
    
    
    @PostMapping("/add-user")
    public String adduser(@RequestBody Users user) {
        userServiceImpl.save(user);
        return "success";
    }

    @PostMapping("/add-client")
    public String addClient(@RequestBody OAuthClient user) {
        clientService.add_Client(user);
        return "success";
    }

    @PostMapping("/test")
    public Object generateManualToken() {
        Users user = userRepository.findByEmail("test@test.com");
        return generateOAuth2AccessToken(user, null, Arrays.asList("mobile2"));
    }

    private Object generateOAuth2AccessToken(Users user, List<SystemRoles> roles, List<String> scopes) {

        Map<String, String> requestParameters = new HashMap<String, String>();
        Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();

        boolean approved = true;
        Set<String> responseTypes = new HashSet<String>();
        responseTypes.add("code");

        // Authorities
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        //loop roles
        authorities.add(new SimpleGrantedAuthority("ROLE_"));

        OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "asd", authorities, approved, new HashSet<String>(scopes), new HashSet<String>(Arrays.asList("resourceIdTest")), null, responseTypes, extensionProperties);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), "N/A", authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);
        AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();
        OAuth2AccessToken token = tokenService.createAccessToken(auth);
        return token;
    }
}
