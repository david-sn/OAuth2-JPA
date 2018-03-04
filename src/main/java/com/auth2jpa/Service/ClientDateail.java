/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Service;

import com.auth2jpa.Entity.OAuthClient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

/**
 *
 * @author Client 1
 */
public class ClientDateail implements ClientDetails {

    OAuthClient client;

    public ClientDateail(OAuthClient client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return StringUtils.commaDelimitedListToSet(client.getResourceIds());
    }

    @Override
    public boolean isSecretRequired() {
        return !StringUtils.isEmpty(client.getClientSecret());
    }

    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return this.getScope().size() > 0;
    }

    @Override
    public Set<String> getScope() {
        return StringUtils.commaDelimitedListToSet(client.getScope());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return StringUtils.commaDelimitedListToSet(client.getAuthorizedGrantTypes());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return StringUtils.commaDelimitedListToSet(client.getRegisteredRedirectUri());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<String> set = StringUtils.commaDelimitedListToSet(client.getAuthorities());
        Set<GrantedAuthority> result = new HashSet<>();
        set.forEach(authority -> result.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return authority;
            }
        }));
        return result;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return client.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return client.getAutoApproveScope().contains(scope);
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

}
