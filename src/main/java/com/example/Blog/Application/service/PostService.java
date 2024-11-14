package com.example.Blog.Application.service;

import com.example.Blog.Application.request.PostRequest;
import com.example.Blog.Application.response.PostResponse;
import com.example.Blog.Application.response.list.PostResponseList;

public interface PostService {
    void createPost(PostRequest request);

    PostResponse getPostById(Long postId);

    void deleteById(Long postId);

    PostResponse updateById(Long postId, PostRequest request);

    PostResponseList getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    boolean isOwner(String userEmail, Long postId);
}
