//package com.example.security.config;
//
//import com.example.security.entity.Customer;
//import com.example.security.repo.CustomerRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//import java.util.List;
//
//public class CustomerAuthenticationProvider implements AuthenticationProvider  {
//    @Autowired
//    private CustomerRepo customerRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//    String username=authentication.getName();
//        List<Customer> customers=customerRepo.findAllById(Collections.singleton(username));
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
