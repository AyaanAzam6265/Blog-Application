package com.example.Blog.Application.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;



    private String title;
    private String content;
    private String created_at;
    private String updated_at;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users users;

}
