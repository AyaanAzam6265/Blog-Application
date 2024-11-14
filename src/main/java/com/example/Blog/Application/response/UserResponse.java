package com.example.Blog.Application.response;

import com.example.Blog.Application.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String username;
    private String password;
    private String email;

    public UserResponse(Users source) {
        this.userId = source.getUserId();
        this.username = source.getUsername();
        this.password = source.getPassword();
        this.email = source.getEmail();
    }
}
