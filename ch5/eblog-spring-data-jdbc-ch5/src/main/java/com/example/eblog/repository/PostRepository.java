package com.example.eblog.repository;

import com.example.eblog.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
