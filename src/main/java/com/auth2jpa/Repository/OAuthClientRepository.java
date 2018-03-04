/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Repository;

import com.auth2jpa.Entity.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface OAuthClientRepository extends JpaRepository<OAuthClient, String> {
    
}
