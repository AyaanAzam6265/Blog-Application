package com.example.Blog.Application.controller;

import com.example.Blog.Application.request.UserRequest;
import com.example.Blog.Application.response.UserResponse;
import com.example.Blog.Application.response.list.UserResponseList;
import com.example.Blog.Application.response.util.MessageResponse;
import com.example.Blog.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserRequest userRequest){
        String role = "user";
        userService.createUser(userRequest,role);
        return new ResponseEntity<>(new MessageResponse("User Created"), HttpStatus.OK);
    }
    @PostMapping("/createAdmin")
    public ResponseEntity<MessageResponse> createAdmin(@RequestBody UserRequest userRequest){
        String role = "admin";
        userService.createUser(userRequest, role);
        return new ResponseEntity<>(new MessageResponse("User Created"), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> GetUserByID(@PathVariable Long userId){
        return new ResponseEntity<>(userService.GetUserByID(userId),HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<MessageResponse> UpdateUser(@RequestBody UserRequest request,@PathVariable Long userId){
        userService.updateUser(userId,request);
        return new ResponseEntity<>(new MessageResponse("Updated Successfully"), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResponse> DeleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new MessageResponse("Deleted successfully"),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<UserResponseList> getAllUsers(@RequestParam(defaultValue = "0", required = false)Integer pageNumber,
                                                        @RequestParam(defaultValue = "20",required = false)Integer pageSize,
                                                        @RequestParam(defaultValue = "userId", required = false)String sortBy,
                                                        @RequestParam(defaultValue = "asc",required = false)String sortDir){
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

}
