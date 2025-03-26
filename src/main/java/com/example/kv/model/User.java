package com.example.kv.model;

//import com.example.kv.model.enumeration.UserType;
import jakarta.persistence.*;
import lombok.Data;

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

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
