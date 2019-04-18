package com.example.eblog.repository;

import com.example.eblog.model.Author;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface AuthorRepository extends Repository<Author, Long> {

    @Query("Select a.* from Author a")
    List<Author> findAuthorsWithPosts();

    @Query(("Select * from Author where age= :age"))
    //List<Author> findByAge(Integer age);
    List<Author> findByAge(@Param("age") Integer age);
    //Stream<Author> findByAge(@Param("age") Integer age);
    //Optional<List<Author>> findByAge(@Param("age") Integer age);

}
