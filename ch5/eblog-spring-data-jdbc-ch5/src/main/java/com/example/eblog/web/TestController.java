package com.example.eblog.web;

import com.example.eblog.model.Author;
import com.example.eblog.model.Post;
import com.example.eblog.repository.AuthorRepository;
import com.example.eblog.repository.PostRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> recentPosts(@RequestParam Integer limit, @RequestParam Integer offset, @RequestParam String orderBy){
        log.info("recentPosts, params: {}, {}", limit, offset);
        PageRequest pageRequest = PageRequest.of(limit, offset, Sort.Direction.DESC, orderBy);
        return Lists.newArrayList(postRepository.findAll());
    }

    @GetMapping("/authors")
    public List<Author> authorsWithTopPosts()   {
        log.info("authorsWithTopPosts");
        return authorRepository.findAuthorsWithPosts();
    }
}
