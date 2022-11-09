package com.springboot.blog.service;

import com.springboot.blog.payload.Dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentDto create(Long postId,CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getComment(long postId  ,long commentId);

    CommentDto updateComment(long postId , long commentId , CommentDto commentDto);

    Boolean deleteComment(long postId, long commentId);
}
