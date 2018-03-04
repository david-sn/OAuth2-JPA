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
@Table(name = "oauth_code")
@NamedQueries({
    @NamedQuery(name = "OauthCode.findAll", query = "SELECT o FROM OauthCode o")})
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Lob
    @Column(name = "authentication")
    private byte[] authentication;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dbid")
    private Integer dbid;

    public OauthCode() {
    }

    public OauthCode(Integer dbid) {
        this.dbid = dbid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
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
        if (!(object instanceof OauthCode)) {
            return false;
        }
        OauthCode other = (OauthCode) object;
        if ((this.dbid == null && other.dbid != null) || (this.dbid != null && !this.dbid.equals(other.dbid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.auth2jpa.Entity.OauthCode[ dbid=" + dbid + " ]";
    }
    
}
