package com.example.Blog.Application.controller;

import com.example.Blog.Application.request.PostRequest;
import com.example.Blog.Application.response.PostResponse;
import com.example.Blog.Application.response.list.PostResponseList;
import com.example.Blog.Application.response.util.MessageResponse;
import com.example.Blog.Application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private PostService service;


    @PostMapping("/createPost")
    public ResponseEntity<MessageResponse> createPost(@RequestBody PostRequest request){
        try {
            service.createPost(request);
            return new ResponseEntity<>(new MessageResponse("Post Created"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Not Working"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId){
        try {
            return new ResponseEntity<>(service.getPostById(postId),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long postId){
        service.deleteById(postId);
        return new ResponseEntity<>(new MessageResponse("Deleted Successfully"),HttpStatus.OK);
    }
    @PutMapping("/update/{postId}")
    public ResponseEntity<MessageResponse> updateById(@PathVariable Long postId, @RequestBody PostRequest request){
            PostResponse postResponse = service.updateById(postId,request);
        return new ResponseEntity<>(new MessageResponse("Skill Updated Successfully\n"+postResponse.toString()), HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<PostResponseList> getAllPost(@RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                                       @RequestParam(defaultValue = "20", required = false) Integer pageSize,
                                                       @RequestParam(defaultValue = "postId", required = false) String sortBy,
                                                       @RequestParam(defaultValue = "asc", required = false) String sortDir){
            return new ResponseEntity<>(service.getAllPost(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
}
