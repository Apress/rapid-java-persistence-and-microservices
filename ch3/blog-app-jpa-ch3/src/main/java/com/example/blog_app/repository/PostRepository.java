package com.example.blog_app.repository;

import com.example.blog_app.model.jpa.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("Select p.id, p.title from Post p")
    List<Post> findPostWithIdAndTitle(PageRequest pageRequest);

}
