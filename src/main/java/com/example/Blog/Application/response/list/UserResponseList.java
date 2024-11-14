package com.example.Blog.Application.response.list;

import com.example.Blog.Application.response.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponseList {
    private Integer pageNumber;

    private Integer pageSize;

    private String sortDir;

    private String sortBy;

    private Integer totalPages;

    private List<UserResponse> userResponses;

    public UserResponseList(Integer pageNumber, Integer pageSize, String sortDir, String sortBy, Integer totalPages, List<UserResponse> userResponses) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortDir = sortDir;
        this.sortBy = sortBy;
        this.totalPages = totalPages;
        this.userResponses = userResponses;
    }
}
