package com.example.Blog.Application.controller.security;

import com.example.Blog.Application.request.LoginRequest;
import com.example.Blog.Application.response.util.MessageResponse;
import com.example.Blog.Application.utility.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signin")
    @CrossOrigin("**")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken( loginRequest.getEmail ( ), loginRequest.getPassword ( ) ) );
            return new ResponseEntity<> ( jwtTokenUtil.generateToken ( authenticate ), HttpStatus.OK );
        }
        catch ( AuthenticationException e ) {
            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED )
                    .body ( new MessageResponse( "Invalid username or password" ) );
        }
    }
}
