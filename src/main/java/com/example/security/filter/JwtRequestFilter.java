package com.example.security.filter;

import com.example.security.entity.Customer;
import com.example.security.service.CustomerService;
import com.example.security.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final ApplicationContext applicationContext;  // Use ApplicationContext instead of direct service

    // Constructor injection
    @Autowired
    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, ApplicationContext applicationContext) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String name = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            name = jwtTokenUtil.extractUsername(jwt);
        }

        if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Dynamically retrieve CustomerService from ApplicationContext
            CustomerService customerService = applicationContext.getBean(CustomerService.class);
            Customer customer = customerService.loadUserByname(name);

            if (jwtTokenUtil.validateToken(jwt, customer.getName())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        customer, null, customer.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
