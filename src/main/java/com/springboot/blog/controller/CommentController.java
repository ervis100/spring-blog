package com.springboot.blog.controller;

import com.springboot.blog.payload.Dto.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @Valid
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.create(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(
            @PathVariable(value = "postId") long postId ,
            @PathVariable(value = "commentId") long commentId) {
        return new ResponseEntity<>(this.commentService.getComment(postId , commentId) , HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(name = "postId") long postId ,
            @PathVariable(name = "commentId") long commentId,
            @Valid
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(this.commentService.updateComment(postId , commentId , commentDto) , HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Boolean> deleteComment(
            @PathVariable(name = "postId") long postId ,
            @PathVariable(name = "commentId") long commentId
    ){
        return new ResponseEntity<>(this.commentService.deleteComment(postId , commentId) , HttpStatus.ACCEPTED);
    }
}