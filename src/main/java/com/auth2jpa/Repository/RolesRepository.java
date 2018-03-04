package com.auth2jpa.Repository;

import com.auth2jpa.Entity.SystemRoles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<SystemRoles, Short> {

    SystemRoles findByRoleId(Short roleId);

    List<SystemRoles> findByRoleIdIn(Short[] roleId);

}
