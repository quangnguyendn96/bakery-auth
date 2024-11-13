package com.bakery.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class UsersEntity {
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;
    private String username;
    private String password;
    private String email;
}

