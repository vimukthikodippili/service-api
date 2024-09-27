package com.example.security.service;

import com.example.security.entity.Customer;
import com.example.security.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public Customer loadUserByname(String name) throws UsernameNotFoundException {
        return customerRepo.findByname(name);
    }

    public boolean checkPassword(Customer customer, String rawPassword) {
        return passwordEncoder.matches(rawPassword, customer.getPassword());
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }
}
