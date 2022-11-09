package com.springboot.blog.payload.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CommentDto {
    private Long id;
    @NotEmpty(message = "Comment's name shouldn't be empty!")
    private String name;

    @NotEmpty(message = "Comment's email should not be empty!")
    @Email(message = "Comment's email should be a valid email address!")
    private String email;

    @NotEmpty(message = "Comment's body should not be empty!")
    private String body;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
