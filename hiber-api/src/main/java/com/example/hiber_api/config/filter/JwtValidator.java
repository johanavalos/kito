package com.example.hiber_api.config.filter;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hiber_api.util.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter{

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = jwt.substring(7);

        DecodedJWT decodedJWT = jwtUtils.validateToken(jwt);

        String username = jwtUtils.extractUsername(decodedJWT);

        String authoritiesString = jwtUtils.extractClaim(decodedJWT, "authorities").asString();
        
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString);

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
    
}
