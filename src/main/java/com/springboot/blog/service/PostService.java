package com.springboot.blog.service;

import com.springboot.blog.payload.Dto.PostDTO;
import com.springboot.blog.payload.Response.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO , Long id);

    String deletePost(Long id);

    PostResponse paginate(Integer pageNo , Integer pageSize, String sortBy , String sortDir);
}
