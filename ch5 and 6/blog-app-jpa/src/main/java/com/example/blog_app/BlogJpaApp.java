package com.example.blog_app;

import com.example.blog_app.repository.base.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BlogJpaApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogJpaApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App started");
    }

}
