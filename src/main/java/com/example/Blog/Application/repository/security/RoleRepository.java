package com.example.Blog.Application.repository.security;

import com.example.Blog.Application.model.security.RoleEntity;
import com.example.Blog.Application.utility.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

    Optional<RoleEntity> findByName(Role name);
}
