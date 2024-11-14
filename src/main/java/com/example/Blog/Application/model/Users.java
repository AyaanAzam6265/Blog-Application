package com.example.Blog.Application.model;

import com.example.Blog.Application.model.security.RoleEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role")
    private RoleEntity role;

}
