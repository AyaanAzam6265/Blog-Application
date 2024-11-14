package com.example.Blog.Application.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String created_at;
    private String content;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Users users;
}
