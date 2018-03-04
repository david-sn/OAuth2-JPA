/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Service;

import com.auth2jpa.Entity.OAuthClient;
import com.auth2jpa.Repository.OAuthClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class ClientService implements ClientDetailsService {

    @Autowired
    private OAuthClientRepository oAuthClientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OAuthClient clientDB = oAuthClientRepository.findOne(clientId);
        return new ClientDateail(clientDB);
    }

    public void add_Client(OAuthClient clientDB) {
        clientDB.setClientSecret(bCryptPasswordEncoder.encode(clientDB.getClientSecret()));
        oAuthClientRepository.save(clientDB);
    }

}
