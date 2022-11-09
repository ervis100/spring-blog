package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIexception;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.Dto.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto create(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post" ,"id" , String.valueOf(postId)));

        comment.setPost(post);
        commentRepository.save(comment);

        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
       List<Comment> commentList = this.commentRepository.findByPostId(postId);

       return commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }


    @Override
    public CommentDto getComment(long postId , long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post" ,"id" , String.valueOf(postId)));

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment" , "Id"  , String.valueOf(commentId)));

        if(comment.getPost().getId() != (post.getId())) {
            throw new BlogAPIexception( "Comment with id "+commentId+" doesn't belong to post with id "+postId+" !" ,
                    HttpStatus.BAD_REQUEST);
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId , long commentId, CommentDto commentDto) {
        Post post = this.postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post" , "id" , String.valueOf(postId))
        );
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment" ,  "id" , String.valueOf(commentId))
        );

        if (comment.getPost().getId() != post.getId()){
            throw new BlogAPIexception("Comment with id "+commentId+" does not belog to post with id "+ postId ,
                    HttpStatus.BAD_REQUEST);
        }

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        this.commentRepository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public Boolean deleteComment(long postId , long commentId) {
        Post post = this.postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post" , "id" , String.valueOf(postId))
        );
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment" ,  "id" , String.valueOf(commentId))
        );

        if (comment.getPost().getId() != post.getId()){
            throw new BlogAPIexception("Comment with id "+commentId+" does not belog to post with id "+ postId ,
                    HttpStatus.BAD_REQUEST);
        }

        this.commentRepository.delete(comment);
        return true;
    }

    public CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment , CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
        return commentDto;
    }

    public Comment mapToEntity(CommentDto commentDto){
        Comment comment =modelMapper.map(commentDto , Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getEmail());
        return comment;
    }

}
