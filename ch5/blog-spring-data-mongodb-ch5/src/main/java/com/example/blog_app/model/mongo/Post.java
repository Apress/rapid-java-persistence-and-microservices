package com.example.blog_app.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(exclude = {"comments"})
public class Post implements Serializable {

    @Id
    private String id;
    private String title;
    private String content;
    private PostStatus postStatus;
    private String blogName;
    private String userName;
    private Set<Comment> comments;

}
