package com.springboot.blog.controller;

import com.springboot.blog.payload.Dto.PostDTO;
import com.springboot.blog.payload.Response.PostResponse;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO ) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        List<PostDTO> postList = postService.getAllPosts();
        return postList;
    }

    @GetMapping(path = "/pagination")
    public PostResponse postPagination(
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy" , defaultValue = "id" ,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC" ,required = false) String sortDir
    ) {
        return postService.paginate(pageNo , pageSize ,sortBy ,sortDir);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid
                                              @PathVariable(name = "id") Long id,
                                              @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>( postService.updatePost(postDTO, id) , HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name ="id") Long id) {
        return new ResponseEntity(postService.deletePost(id) , HttpStatus.OK);
    }
}
