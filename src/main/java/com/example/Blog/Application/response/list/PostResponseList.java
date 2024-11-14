package com.example.Blog.Application.response.list;

import com.example.Blog.Application.response.PostResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PostResponseList {
    private Integer pageNumber;

    private Integer pageSize;

    private String sortDir;

    private String sortBy;

    private Integer totalPages;

    private List<PostResponse> postResponses;

    public PostResponseList(Integer pageNumber, Integer pageSize, String sortDir, String sortBy, Integer totalPages, List<PostResponse> postResponses) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortDir = sortDir;
        this.sortBy = sortBy;
        this.totalPages = totalPages;
        this.postResponses = postResponses;
    }
}
