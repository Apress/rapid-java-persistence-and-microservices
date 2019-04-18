package com.example.eblog;

import com.example.eblog.model.Author;
import com.example.eblog.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = {"com.example.eblog.repository"})
@Slf4j
public class App implements CommandLineRunner {

    @Autowired
    AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Author> authorList = authorRepository.findByAge(35);
        //List<Author> authorList = authorRepository.findByAge(35).collect(Collectors.toList());
        //List<Author> authorList = authorRepository.findByAge(35).get();
        log.info("list: {}", authorList);
    }
}
