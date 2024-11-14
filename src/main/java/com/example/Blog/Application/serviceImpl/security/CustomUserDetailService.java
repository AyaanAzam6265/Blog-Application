package com.example.Blog.Application.serviceImpl.security;

import com.example.Blog.Application.model.Users;
import com.example.Blog.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find user by email (case-insensitive)
        Users users = userRepository.findByEmailIgnoreCase(email);

        if (users == null) {
            throw new UsernameNotFoundException("User not found ");
        }
        return new CustomUserDetails(users);
    }
}
