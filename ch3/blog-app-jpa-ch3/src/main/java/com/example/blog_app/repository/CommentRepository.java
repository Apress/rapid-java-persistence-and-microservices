package com.example.blog_app.repository;

import com.example.blog_app.model.jpa.Comment;
import com.example.blog_app.model.jpa.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

}
