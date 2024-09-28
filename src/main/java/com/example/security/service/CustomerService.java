package com.example.security.service;

import com.example.security.dto.LoginRequest;
import com.example.security.entity.Customer;
import com.example.security.repo.CustomerRepo;
import com.example.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class CustomerService implements UserDetailsService {
@Autowired
private CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public CustomerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public Customer loadUserByname(String name) throws UsernameNotFoundException {
        return customerRepo.findByname(name);

    }
    public Customer loadUserByName(String name) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByname(name);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return customer;
    }

    public boolean checkPassword(Customer customer, String rawPassword) {
        return passwordEncoder.matches(rawPassword, customer.getPassword());
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public String createJwtToken(LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword())
            );

            Customer customer = (Customer) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(customer);
        } catch (Exception e) {
            throw new Exception("Invalid credentials!");
        }
    }

    public String login(LoginRequest loginRequest) throws Exception {
        Customer customer = customerRepo.findByname(loginRequest.getName());

        // Password verification
        if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        // Return JWT Token (use your JWT generation logic)
        return generateJwtToken(customer);
    }

    private String generateJwtToken(Customer customer) {
        // Logic to generate JWT
        return "token";
    }
}
