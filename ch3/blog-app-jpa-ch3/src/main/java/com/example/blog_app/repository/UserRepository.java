package com.example.blog_app.repository;

import com.example.blog_app.model.jpa.User;
import com.example.blog_app.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    void deleteByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("select u from User u")
    Stream<User> streamAll();

}
