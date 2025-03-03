package com.example.kito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.kito.dto.auth.AuthCreateUserRequest;
import com.example.kito.dto.auth.AuthLoginRequest;
import com.example.kito.dto.auth.AuthResponse;
import com.example.kito.exception.UsernameAlreadyExistsException;

import com.example.kito.model.security.Role;
import com.example.kito.model.security.RoleEnum;
import com.example.kito.model.security.User;
import com.example.kito.repository.RoleRepository;
import com.example.kito.repository.UserRepository;
import com.example.kito.util.JwtUtils;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setRoleEnum(RoleEnum.USER);

        user = User.builder()
                .username("testuser")
                .password("encodedpassword")
                .roles(Set.of(role))
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        UserDetails userDetails = authenticationService.loadUserByUsername("testuser");
        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("unknown"))
                .thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,
                () -> authenticationService.loadUserByUsername("unknown"));
    }

    @Test
    void testLoginUser_Successful() {
        AuthLoginRequest loginRequest = new AuthLoginRequest("testuser", "password");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedpassword")).thenReturn(true);
        when(jwtUtils.createToken(any())).thenReturn("fake-jwt-token");

        AuthResponse response = authenticationService.loginUser(loginRequest);

        assertNotNull(response);
        assertEquals("testuser", response.username());
        assertEquals("Logged succesfuly.", response.message());
        assertTrue(response.status());
        assertEquals("fake-jwt-token", response.jwt());
    }

    @Test
    void testLoginUser_InvalidPassword() {
        AuthLoginRequest loginRequest = new AuthLoginRequest("testuser", "wrongpassword");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", "encodedpassword")).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> authenticationService.loginUser(loginRequest));
    }

    @Test
    void testCreateUser_Success() {
        AuthCreateUserRequest userRequest = new AuthCreateUserRequest("newuser", "newpassword", List.of("USER"));
        when(roleRepository.findRoleEntitiesByRoleEnumIn(List.of("USER"))).thenReturn(List.of(role));
        when(passwordEncoder.encode("newpassword")).thenReturn("encodedpassword");
        when(userRepository.save(any())).thenReturn(user);
        when(jwtUtils.createToken(any())).thenReturn("fake-jwt-token");

        AuthResponse response = authenticationService.createUser(userRequest);

        assertNotNull(response);
        assertEquals("newuser", response.username());
        assertEquals("User created successfully", response.message());
        assertEquals("fake-jwt-token", response.jwt());
        assertTrue(response.status());
    }

    @Test
    void testCreateUser_UsernameAlreadyExists() {
        AuthCreateUserRequest userRequest = new AuthCreateUserRequest("existinguser", "password", List.of("USER"));
        when(roleRepository.findRoleEntitiesByRoleEnumIn(List.of("USER"))).thenReturn(List.of(role));
        when(userRepository.save(any())).thenThrow(new UsernameAlreadyExistsException("existinguser"));

        assertThrows(UsernameAlreadyExistsException.class, () -> authenticationService.createUser(userRequest));
    }
}
