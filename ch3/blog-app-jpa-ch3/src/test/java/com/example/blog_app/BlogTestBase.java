package com.example.blog_app;

import com.example.blog_app.helper.DataGenerator;
import com.example.blog_app.repository.BlogRepository;
import com.example.blog_app.repository.FileRepository;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.repository.UserRepository;
import com.example.blog_app.service.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class BlogTestBase {

    @Autowired
    DataGenerator dataGenerator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCRUDService userCRUDService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    protected TestRestTemplate restTemplate;

}
