package com.example.blog_app.repository;

import com.example.blog_app.model.jpa.Post;
import com.example.blog_app.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends BaseRepository<Post, Long> {

    @Query("Select p.id, p.title from Post p")
    List<Post> findPostWithIdAndTitle();

}
