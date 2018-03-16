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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "oauth_client")
@NamedQueries({
    @NamedQuery(name = "OauthClient.findAll", query = "SELECT o FROM OauthClient o")})
public class OauthClient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "client_id")
    private String clientId;
    @Size(max = 255)
    @Column(name = "resource_ids")
    private String resourceIds;
    @Size(max = 255)
    @Column(name = "client_secret")
    private String clientSecret;
    @Size(max = 255)
    @Column(name = "scope")
    private String scope;
    @Size(max = 255)
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    @Size(max = 255)
    @Column(name = "registered_redirect_uri")
    private String registeredRedirectUri;
    @Size(max = 255)
    @Column(name = "authorities")
    private String authorities;
    @Column(name = "access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;
    @Column(name = "refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;
    @Size(max = 255)
    @Column(name = "auto_approve_scope")
    private String autoApproveScope;
    @Size(max = 255)
    @Column(name = "additional_information")
    private String additionalInformation;

    public OauthClient() {
    }

    public OauthClient(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getAutoApproveScope() {
        return autoApproveScope;
    }

    public void setAutoApproveScope(String autoApproveScope) {
        this.autoApproveScope = autoApproveScope;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OauthClient)) {
            return false;
        }
        OauthClient other = (OauthClient) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.auth2jpa.Entity.OauthClient[ clientId=" + clientId + " ]";
    }
    
}
