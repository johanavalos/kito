package com.example.kito.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.kito.dto.auth.AuthCreateUserRequest;
import com.example.kito.dto.auth.AuthLoginRequest;
import com.example.kito.dto.auth.AuthResponse;
import com.example.kito.exception.RoleDoesNotExistException;
import com.example.kito.exception.UsernameAlreadyExistsException;
import com.example.kito.model.security.Role;
import com.example.kito.model.security.User;
import com.example.kito.repository.RoleRepository;
import com.example.kito.repository.UserRepository;
import com.example.kito.util.JwtUtils;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist."));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles()
                .forEach(
                        role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

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
                authorities);
    }

    public AuthResponse loginUser(AuthLoginRequest request) {
        String username = request.username();
        String password = request.password();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "Logged succesfuly.", accessToken, true);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails details = loadUserByUsername(username);

        if(details == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, details.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username, details.getPassword(), details.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest userRequest) {
        String username = userRequest.username();
        String password = userRequest.password();
        Set<Role> roles = extractRoles(userRequest);

        User userEntity = User.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .roles(roles).isEnabled(true)
            .accountNoLocked(true)
            .accountNoExpired(true)
            .credentialNoExpired(true)
            .build();

        User userSaved;
        try {
            userSaved = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameAlreadyExistsException(username);
        }

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> 
            authorities.add(
                new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userSaved.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
        return authResponse;
    }

    private Set<Role> extractRoles(AuthCreateUserRequest userRequest) {
        List<String> rolesRequest = userRequest.roles();

        Set<Role> roles;
        try {
            roles = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest).stream().collect(Collectors.toSet());
        } catch (Exception e) {
            String[] splitted = e.getMessage().split("\\.");
            String role = splitted[splitted.length - 1];
            throw new RoleDoesNotExistException(role);
        }
        return roles;
    }
}