package com.example.Blog.Application.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class PostRequest {
    private String title;
    private String content;
    private String created_at;
    private String updated_at;
    @NotBlank
    private Long authorId;

}
