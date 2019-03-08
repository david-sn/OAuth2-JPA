package com.auth2jpa.Service;

import com.auth2jpa.Entity.SystemRoles;
import com.auth2jpa.Entity.Users;
import com.auth2jpa.Repository.RolesRepository;
import com.auth2jpa.Repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RolesRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username is key contain a value from request body while obtain new token 
        Users user = findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
    }

    public String save(Users user) {
        Users isuser = userRepository.findByEmail(user.getEmail());
        if (isuser != null) {
            return "User Exist";
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setFullName("user_" + ((int) Math.random() * 1000));
            SystemRoles role = roleRepository.findOne((short) 2);
            List<SystemRoles> roles = new ArrayList<>();
            roles.add(role);
            user.setSystemRolesList(roles);
            userRepository.save(user);
            return "success";
        }
    }

}
