package com.example.Blog.Application.response;

import com.example.Blog.Application.model.Posts;
import com.example.Blog.Application.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private String created_at;
    private String updated_at;
    private Long authorId;

    public PostResponse(Posts source) {
        this.postId = source.getPostId();
        this.title = source.getTitle();
        this.content = source.getContent();
        this.created_at = source.getCreated_at();
        this.updated_at = source.getUpdated_at();
        this.authorId = mapAuthor(source.getUsers());
    }

    private Long mapAuthor(Users author) {
        if (author != null) {
            return author.getUserId();
        }
        return null;
    }
}