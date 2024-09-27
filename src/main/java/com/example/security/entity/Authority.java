package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;



    @ManyToOne
    @JoinColumn(name = "authority_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;


    @Override
    public String getAuthority() {
        return null;
    }
}
