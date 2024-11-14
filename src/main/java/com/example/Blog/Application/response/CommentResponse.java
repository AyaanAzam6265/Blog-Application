package com.example.Blog.Application.response;

import com.example.Blog.Application.model.Comments;
import com.example.Blog.Application.model.Posts;
import com.example.Blog.Application.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String created_at;
    private String content;
    private Long postId;
    private Long authorId;

    public CommentResponse(Comments source) {
        this.commentId = source.getCommentId();
        this.created_at = source.getCreated_at();
        this.content = source.getContent();
        this.postId = mapPost(source.getPosts());
        this.authorId = mapAuthor(source.getUsers());
    }

    private Long mapAuthor(Users users) {
        if(users!=null){
            return users.getUserId();
        }
        return null;
    }

    private Long mapPost(Posts posts) {
        if(posts!=null){
            return posts.getPostId();
        }
        return null;
    }
}
