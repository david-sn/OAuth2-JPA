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
@Table(name = "oauth_access_token")
@NamedQueries({
    @NamedQuery(name = "OauthAccessToken.findAll", query = "SELECT o FROM OauthAccessToken o")})
public class OauthAccessToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "token_id")
    private String tokenId;
    @Lob
    @Column(name = "token")
    private byte[] token;
    @Size(max = 255)
    @Column(name = "authentication_id")
    private String authenticationId;
    @Size(max = 255)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 255)
    @Column(name = "client_id")
    private String clientId;
    @Lob
    @Column(name = "authentication")
    private byte[] authentication;
    @Size(max = 255)
    @Column(name = "refresh_token")
    private String refreshToken;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dbid")
    private Integer dbid;

    public OauthAccessToken() {
    }

    public OauthAccessToken(Integer dbid) {
        this.dbid = dbid;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
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
        if (!(object instanceof OauthAccessToken)) {
            return false;
        }
        OauthAccessToken other = (OauthAccessToken) object;
        if ((this.dbid == null && other.dbid != null) || (this.dbid != null && !this.dbid.equals(other.dbid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.auth2jpa.Entity.OauthAccessToken[ dbid=" + dbid + " ]";
    }
    
}
