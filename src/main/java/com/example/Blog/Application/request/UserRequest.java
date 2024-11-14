package com.example.Blog.Application.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String userName;
    @Size(min = 6, max = 30)
    private String password;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
}
