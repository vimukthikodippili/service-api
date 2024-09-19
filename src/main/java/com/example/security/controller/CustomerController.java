package com.example.security.controller;

import com.example.security.entity.Customer;
import com.example.security.repo.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping(path = "/welcome")
    public String getWelcome(){
        return "fuck you";

    }
    @PostMapping(path = "/save")
    public ResponseEntity<String> userRegistry(@RequestBody Customer customer){
        Customer savedCustomer=null;
        ResponseEntity response=null;
        try {
             String encodePassword=passwordEncoder.encode(customer.getPassword());
             customer.setPassword(encodePassword);
            savedCustomer=customerRepo.save(customer);
            response=ResponseEntity.status(HttpStatus.CREATED)
                    .body("registerd");

        }catch (Exception exception){

            response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("not registerd"+exception.getMessage());
        }
return response;
    }
}
