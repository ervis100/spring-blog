package com.springboot.blog.payload.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostDTO {
    private Long id;

    @NotBlank
    @Size(min = 2 , message = "Post title should have at least 2 characters.")
    private String title;

    @NotEmpty
    @Size(min = 10 , message = "Post description should be at least 10 characters.")
    private String description;

    @NotEmpty
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
