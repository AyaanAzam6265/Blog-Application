package com.example.Blog.Application.repository;

import com.example.Blog.Application.model.Comments;
import com.example.Blog.Application.response.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {
    @Query("select c from Comments c where c.posts.id = :postId")
    Page<Comments> findByPostId(Long postId, Pageable pageWithSort);
}
