package com.example.project3.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String role;
    private boolean active = true;
}