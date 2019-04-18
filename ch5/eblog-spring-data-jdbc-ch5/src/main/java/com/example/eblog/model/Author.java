package com.example.eblog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@ToString(exclude = {"posts"})
@EqualsAndHashCode(exclude = {"posts"})
public class Author implements Serializable {

    @Id
    private Long id;
    private String name;
    private int age;

    private Set<Post> posts;

    @PersistenceConstructor
    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Author withId(Long id)  {
        return new Author(id, this.name, this.age, this.posts);
    }

}
