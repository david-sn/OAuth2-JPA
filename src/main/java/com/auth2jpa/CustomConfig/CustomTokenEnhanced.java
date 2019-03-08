/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.CustomConfig;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 *
 * @author David Shiref
 */
@Component
public class CustomTokenEnhanced implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("user_id", authentication.getPrincipal());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        DefaultOAuth2AccessToken s = new DefaultOAuth2AccessToken("sd");
        s.setTokenType("DAVID");
        return accessToken;
    }
}
