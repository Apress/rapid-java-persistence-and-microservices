package com.example.eblog.repository;

import com.example.eblog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("Select distinct a from Author a left join fetch a.posts")
    List<Author> findAuthorsWithPosts();

}
