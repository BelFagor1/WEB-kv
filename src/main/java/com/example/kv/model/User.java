package com.example.kv.model;

import com.example.kv.model.enumeration.UserType;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String salt;

    private UserType userType;

    public User(String email, String passwordHash,String salt, UserType userType) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.userType = userType;
        this.salt = salt;
    }

    public User(String email, String passwordHash,String salt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.userType = UserType.USER;
    }

    public User() {}
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public UserType getUserType() {
        return userType;
    }
}
