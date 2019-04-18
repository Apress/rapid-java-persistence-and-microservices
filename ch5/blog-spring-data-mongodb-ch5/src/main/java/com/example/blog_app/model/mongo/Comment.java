package com.example.blog_app.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Comment {

    @Id
    private String id;
    private String content;

    public Comment(String content)   {
        this.content = content;
    }
}
