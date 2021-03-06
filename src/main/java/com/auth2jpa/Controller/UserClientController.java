/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Controller;

import com.auth2jpa.Entity.OauthAccessToken;
import com.auth2jpa.Entity.OauthClient;
import com.auth2jpa.Entity.SystemRoles;
import com.auth2jpa.Entity.Users;
import com.auth2jpa.Repository.OauthAccessTokenRepository;
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
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private AuthorizationServerEndpointsConfiguration configuration;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenRepository;
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        //this important if use this service as authorization server for other microservces
        return user;
    }

    @PostMapping("/add-user")
    public String adduser(@RequestBody Users user) {
        userServiceImpl.save(user);
        return "success";
    }

    @PostMapping("/add-client")
    public String addClient(@RequestBody OauthClient user) {
        clientService.add_Client(user);
        return "success";
    }

    @PostMapping("/kill-token")
    public String logout(Principal principal) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        OAuth2AccessToken accessToken = authorizationServerTokenServices.getAccessToken(oAuth2Authentication);
        consumerTokenServices.revokeToken(accessToken.getValue());
        return "token >" + accessToken.toString() + "< killed";
    }

    @PostMapping("/list-logged-in")
    public List<OauthAccessToken> getLoggedInUsers() {
        return oauthAccessTokenRepository.findAll();
    }

    @PostMapping("/generate-token")
    public Object generateManualToken() {
        //create a new user to generate token for this new token like user will register from another app like facebook linked-in instagram etc those app will give json object for user data
        return generateOAuth2AccessToken("test@test.com", null);
    }

    @PostMapping(value = "/Tok")
    public Object s(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return "DONE";
//        return tokenEndpoint.postAccessToken(principal, parameters);
    }

    private OAuth2AccessToken generateOAuth2AccessToken(String userEmail, List<SystemRoles> roles) {

        Map<String, String> requestParameters = new HashMap<>();
        Map<String, Serializable> extensionProperties = new HashMap<>();

        boolean approved = true;
        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");

        // Authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
        //loop roles
        authorities.add(new SimpleGrantedAuthority("ROLE_"));

        //here must get client Detail from database and set clientId, resourceId and scopes for social users
        OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "asd", authorities, approved, new HashSet<>(Arrays.asList("social")), new HashSet<>(Arrays.asList("resourceIdTest")), null, responseTypes, extensionProperties);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, "N/A", authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);
        AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();
        OAuth2AccessToken token = tokenService.createAccessToken(auth);
        return token;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tokens/revoke/{tokenId:.*}")
    public Boolean revokeToken(@PathVariable String tokenId) {
        return consumerTokenServices.revokeToken(tokenId);
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException npe) {
        return "NullPointerException";
    }
}
