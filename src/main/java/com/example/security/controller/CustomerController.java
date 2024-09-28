package com.example.security.controller;

import com.example.security.dto.JwtResponse;
import com.example.security.dto.LoginRequest;
import com.example.security.entity.Customer;
import com.example.security.service.CustomerService;
import com.example.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class CustomerController {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/welcome")
    public String getWelcome(){
        return "fuck you";

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = customerService.login(loginRequest);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



    @PostMapping(path = "/register")
    public String register(@RequestBody Customer customer) {
        // Check if user already exists
        if (customerService.loadUserByname(customer.getName()) != null) {
            return "User already exists!";
        }

        // Encrypt the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        // Save the customer to the database
        customerService.saveCustomer(customer);

        // Generate JWT token for the customer
        return jwtTokenUtil.generateToken(customer);
    }


}
