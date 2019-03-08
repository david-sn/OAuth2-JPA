/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.CustomConfig;

import java.util.Map;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

/**
 *
 * @author Client 1
 */
public class ScopeMappingOAuth2RequestFactory extends DefaultOAuth2RequestFactory {

    public ScopeMappingOAuth2RequestFactory(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {
        TokenRequest createTokenRequest = super.createTokenRequest(requestParameters, authenticatedClient);
        return createTokenRequest;
    }

}
