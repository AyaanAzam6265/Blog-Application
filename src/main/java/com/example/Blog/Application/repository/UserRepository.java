package com.example.Blog.Application.repository;

import com.example.Blog.Application.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmailIgnoreCase(String email);
}
