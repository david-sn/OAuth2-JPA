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
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        Users user = findByEmail(emailAddress);

        if (user == null) {
            throw new UsernameNotFoundException(emailAddress);
        }

        return new UserDetailsImpl(user);
    }

    public String save(Users user) {
        Users isuser = userRepository.findByEmail(user.getEmail());
        if (isuser != null) {
            return "User Exist";
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            SystemRoles role = roleRepository.findOne((short) 2);
            List<SystemRoles> roles = new ArrayList<>();
            roles.add(role);
            user.setSystemRolesList(roles);
            userRepository.save(user);
            return "success";
        }
    }

}
