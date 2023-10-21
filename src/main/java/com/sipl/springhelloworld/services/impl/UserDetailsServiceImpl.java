package com.sipl.springhelloworld.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sipl.springhelloworld.entities.UserMaster;
import com.sipl.springhelloworld.repositories.UserMasterRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMasterRepository userMasterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserMaster> isUserMaster = userMasterRepository.findByUsername(username);

        if (!isUserMaster.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        UserMaster userMaster = isUserMaster.get();

        // Create and return the Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(userMaster.getUsername())
                .password(userMaster.getPassword())
                .roles("ADMIN","USER") // Replace with appropriate roles from userDetails if available
                .build();
    }
}
