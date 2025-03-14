package com.example.kv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SuperPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String passwordHash;

    public SuperPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public SuperPassword() {
    }
}
