package com.example.Blog.Application.controller;

import com.example.Blog.Application.request.CommentRequest;
import com.example.Blog.Application.response.CommentResponse;
import com.example.Blog.Application.response.list.CommentResponseList;
import com.example.Blog.Application.response.list.PostCommentsList;
import com.example.Blog.Application.response.util.MessageResponse;
import com.example.Blog.Application.service.CommentService;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/createComment")
    public ResponseEntity<MessageResponse> CreateComment(@RequestBody CommentRequest commentRequest){
        commentService.createComment(commentRequest);
        return new ResponseEntity<>(new MessageResponse("Comment Created Successfully"), HttpStatus.OK);
    }
    @GetMapping("/postComments")
    public ResponseEntity<PostCommentsList> getCommentByPostId(@RequestParam(defaultValue = "postId")Long postId,
                                                               @RequestParam(defaultValue = "0")Integer pageNumber,
                                                               @RequestParam(defaultValue = "20")Integer pageSize,
                                                               @RequestParam(defaultValue = "commentId")String sortBy,
                                                               @RequestParam(defaultValue = "asc")String sortDir){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId,pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId),HttpStatus.OK);
    }
    @GetMapping("/allComments")
    public ResponseEntity<CommentResponseList> getAllComment(@RequestParam(defaultValue = "0")Integer pageNumber,
                                                             @RequestParam(defaultValue = "20")Integer pageSize,
                                                             @RequestParam(defaultValue = "commentId")String sortBy,
                                                             @RequestParam(defaultValue = "asc")String sortDir){
        System.out.println("in controller");
        return new ResponseEntity<>(commentService.getAllComment(pageNumber, pageSize, sortBy, sortDir),HttpStatus.OK);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<MessageResponse> updateCommentById(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest){
        try {
            commentService.updateCommentById(commentId,commentRequest);
            return new ResponseEntity<>(new MessageResponse("Comment Updated successfully"),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Not Found"),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteByCommentId(@PathVariable Long commentId){
        commentService.deleteByCommentId(commentId);
        return new ResponseEntity<>(new MessageResponse("Comment Deleted Successfully"),HttpStatus.OK);
    }
}
