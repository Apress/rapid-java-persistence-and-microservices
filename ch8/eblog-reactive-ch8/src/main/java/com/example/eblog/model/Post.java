package com.example.eblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Post implements Serializable {

    @Id
    private Long id;

    private String title;

    @Column("content")
    private String content;

    public Post()   {}

    public Post(String title, String content)   {
        this.title = title;
        this.content = content;
    }
}
