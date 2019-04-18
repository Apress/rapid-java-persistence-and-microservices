package com.example.blogs.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Post {

    private Long id;

    @Length(min = 5, max = 300)
    private String content;

    public Post(Long id, String content)   {
        this.id = id;
        this.content = content;
    }
}
