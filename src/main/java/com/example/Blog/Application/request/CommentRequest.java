package com.example.Blog.Application.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {
    private String Created_at;
    private String content;
    @NotBlank
    private Long postId;
    @NotBlank
    private Long authorId;
}
