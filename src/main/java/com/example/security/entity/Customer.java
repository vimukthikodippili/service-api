package com.example.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;



    @OneToMany(mappedBy ="customer", fetch = FetchType.EAGER)
    private Set<Authority> authorities;
}
