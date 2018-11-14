package com.example.blog_app.web;

import com.example.blog_app.helper.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class IndexController {

    @GetMapping("/")
    public String hello()   {
        return "Hello";
    }
}
