package com.example.Blog.Application.response.list;

import com.example.Blog.Application.response.CommentResponse;
import com.example.Blog.Application.service.CommentService;
import lombok.Data;

import java.util.List;

@Data
public class PostCommentsList {
    private Long postId;
    private Integer pageNumber;
    private Integer pageSize;

    private String sortDir;

    private String sortBy;

    private Integer totalPages;

    private List<CommentResponse> commentResponses;

    public PostCommentsList(Long postId, Integer pageNumber, Integer pageSize, String sortDir, String sortBy, Integer totalPages, List<CommentResponse> commentResponses) {
        this.postId = postId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortDir = sortDir;
        this.sortBy = sortBy;
        this.totalPages = totalPages;
        this.commentResponses = commentResponses;
    }
}
