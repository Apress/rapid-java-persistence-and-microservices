package com.example.blog_app.repository;

import com.example.blog_app.model.mongo.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostMongoRepository extends MongoRepository<Post, String> {
    Post findByTitle(String title);
    List<Post> findByBlogName(String blogName);
}
