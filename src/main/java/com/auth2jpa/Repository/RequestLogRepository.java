/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth2jpa.Repository;

import com.auth2jpa.Entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Integer> {

    RequestLog findByRequestUuidOrResponseUuid(String requestUuid, String responseUuid);
}
