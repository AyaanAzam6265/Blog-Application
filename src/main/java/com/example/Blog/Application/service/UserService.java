package com.example.Blog.Application.service;

import com.example.Blog.Application.request.UserRequest;
import com.example.Blog.Application.response.UserResponse;
import com.example.Blog.Application.response.list.UserResponseList;

public interface UserService {

    void createUser(UserRequest userRequest, String role);

    UserResponse GetUserByID(Long userId);

    void updateUser(Long userId, UserRequest request);

    void deleteUser(Long userId);

    UserResponseList getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    String getUserRoleByEmail(String username);
}
