package com.example.springtutorial;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // getting authorization string from request
        String authHeader = request.getHeader("Authorization");

        String authToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            // Getting JWT token. It starts with "Bearer"
            authToken = authHeader.substring(7);
        }

        // check if the spring security context is null
        if(authToken!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Claims claims = jwtService.verifySignatureAndExtractAllClaims(authToken);
            String role = claims.get("Role", String.class);
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            if (!jwtService.isTokenExpired(authToken)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, simpleGrantedAuthorities);
                // if there are other details with token we can set it to security context
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Setting spring security with jwt security and set the context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
