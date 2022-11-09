package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data //generates getters and setters
@AllArgsConstructor //generates constructor with all argumens
@NoArgsConstructor //generate contructor with no arguments
@Entity //specifies class as entity
@Table(
        name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)

public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    public long id;
    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
