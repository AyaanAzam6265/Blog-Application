package com.example.Blog.Application.serviceImpl;

import com.example.Blog.Application.model.Posts;
import com.example.Blog.Application.model.Users;
import com.example.Blog.Application.repository.PostRepository;
import com.example.Blog.Application.repository.UserRepository;
import com.example.Blog.Application.request.PostRequest;
import com.example.Blog.Application.response.PostResponse;
import com.example.Blog.Application.response.list.PostResponseList;
import com.example.Blog.Application.service.PostService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    PostRepository repository;
    UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void createPost(PostRequest request) {
        Posts entity = new Posts();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setCreated_at(request.getCreated_at());
        entity.setUpdated_at(request.getUpdated_at());
        entity.setUsers(checkUser(request.getAuthorId()));
        repository.save(entity);
    }

    @Override
    public PostResponse getPostById(Long postId) {
        return new PostResponse(repository.findById(postId).orElseThrow(()-> new RuntimeException("Post Not Found")));
    }

    @Override
    public void deleteById(Long postId) {
        Posts entity = repository.findById(postId).orElseThrow(()-> new RuntimeException("Post Not Found"));
        repository.delete(entity);
    }

    @Override
    public PostResponse updateById(Long postId, PostRequest request) {
        Posts entity = repository.findById(postId).orElseThrow(()-> new RuntimeException("Post Not Found"));
        entity.setUpdated_at(request.getUpdated_at());
        entity.setContent(request.getContent());
        entity.setTitle(request.getTitle());
        entity.setCreated_at(request.getCreated_at());
        entity.setUsers(checkUser(request.getAuthorId()));
        repository.save(entity);
        return new PostResponse(entity);
    }

    @Override
    public PostResponseList getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageWithSort = PageRequest.of(pageNumber, pageSize, sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Page<Posts> postsPage = repository.findAll(pageWithSort);
        List<PostResponse> skillResponses = postsPage.stream().map(PostResponse::new).collect(Collectors.toList());
        return new PostResponseList(pageNumber, pageSize, sortBy, sortDir, postsPage.getTotalPages(), skillResponses);
    }

    @Override
    public boolean isOwner(String userEmail, Long postId) {
        Posts post = repository.findById(postId).orElseThrow(()->new RuntimeException("Post Not found"));
        return post.getUsers().getEmail().equals(userEmail);
    }

    public Users checkUser(Long authorId) {
        return userRepository.findById(authorId).orElseThrow(()-> new RuntimeException("User not Found"));
    }
}
