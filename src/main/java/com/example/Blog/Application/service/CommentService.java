package com.example.Blog.Application.service;

import com.example.Blog.Application.request.CommentRequest;
import com.example.Blog.Application.response.CommentResponse;
import com.example.Blog.Application.response.list.CommentResponseList;
import com.example.Blog.Application.response.list.PostCommentsList;

public interface CommentService {

    void createComment(CommentRequest commentRequest);

    PostCommentsList getCommentByPostId(Long postId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    CommentResponse getCommentById(Long commentId);

    CommentResponseList getAllComment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    void updateCommentById(Long commentId, CommentRequest commentRequest);

    void deleteByCommentId(Long commentId);
}
