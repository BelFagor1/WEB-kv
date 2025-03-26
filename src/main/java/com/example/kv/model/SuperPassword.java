package com.example.kv.model;

import jakarta.persistence.*;


@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
