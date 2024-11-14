package com.example.Blog.Application.serviceImpl;

import com.example.Blog.Application.model.Comments;
import com.example.Blog.Application.model.Posts;
import com.example.Blog.Application.model.Users;
import com.example.Blog.Application.repository.CommentRepository;
import com.example.Blog.Application.repository.PostRepository;
import com.example.Blog.Application.repository.UserRepository;
import com.example.Blog.Application.request.CommentRequest;
import com.example.Blog.Application.response.CommentResponse;
import com.example.Blog.Application.response.list.CommentResponseList;
import com.example.Blog.Application.response.list.PostCommentsList;
import com.example.Blog.Application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    PostRepository postRepository;
    UserRepository userRepository;
    CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(CommentRequest commentRequest) {
        Comments entity = new Comments();
        entity.setCreated_at(commentRequest.getCreated_at());
        entity.setContent(commentRequest.getContent());
        entity.setPosts(checkPost(commentRequest.getPostId()));
        entity.setUsers(checkUser(commentRequest.getAuthorId()));
        commentRepository.save(entity);
    }

    @Override
    public PostCommentsList getCommentByPostId(Long postId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageWithSort = PageRequest.of ( pageNumber, pageSize, sortDir.equalsIgnoreCase ( "asc" ) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy );
        Page<Comments> commentsPage = commentRepository.findByPostId ( postId, pageWithSort );
        List<CommentResponse> commentResponseList = commentsPage.stream ( ).map ( CommentResponse::new ).toList ( );
        return new PostCommentsList(postId, pageNumber, pageSize, sortBy, sortDir, commentsPage.getTotalPages(), commentResponseList);
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        return new CommentResponse(commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment Not Found")));
    }

    @Override
    public CommentResponseList getAllComment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageWithSort = PageRequest.of(pageNumber,pageSize, sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC:Sort.Direction.DESC, sortBy);
        Page<Comments> commentsPage = commentRepository.findAll(pageWithSort);
        System.out.println("commentsPage.getTotalElements() = " + commentsPage.getTotalElements());
        List<CommentResponse> commentResponses = commentsPage.stream().map(CommentResponse::new).toList();
        return new CommentResponseList(pageNumber,pageSize,sortBy,sortDir,commentsPage.getTotalPages(),commentResponses);
    }

    @Override
    public void updateCommentById(Long commentId, CommentRequest commentRequest) {
        Comments entity = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment Not Found"));
        entity.setContent(commentRequest.getContent());
        entity.setCreated_at(commentRequest.getCreated_at());
        entity.setUsers(checkUser(commentRequest.getAuthorId()));
        entity.setPosts(checkPost(commentRequest.getPostId()));
        commentRepository.save(entity);
    }

    @Override
    public void deleteByCommentId(Long commentId) {
        Comments entity = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment Not Found"));
        commentRepository.delete(entity);
    }

    private Users checkUser(Long authorId) {
        return userRepository.findById(authorId).orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    private Posts checkPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post Not Found"));
    }

}
