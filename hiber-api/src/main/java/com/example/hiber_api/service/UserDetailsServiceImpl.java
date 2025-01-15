package com.example.hiber_api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hiber_api.model.security.User;
import com.example.hiber_api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist.")); 

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles()
            .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        user.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            user.isAccountNoExpired(),
            user.isCredentialNoExpired(),
            user.isAccountNoLocked(),
            authorities
        );
    }
    
}
