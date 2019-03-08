package com.auth2jpa.Service;

import com.auth2jpa.Entity.SystemRoles;
import com.auth2jpa.Entity.Users;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private Users user;

    public UserDetailsImpl(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> medorities = new HashSet<>();
        List<SystemRoles> roles = user.getSystemRolesList();
        for (SystemRoles role : roles) {
            medorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return medorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

}
