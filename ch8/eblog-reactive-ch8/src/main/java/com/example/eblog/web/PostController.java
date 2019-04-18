package com.example.eblog.web;

import com.example.eblog.dto.PostInputRequest;
import com.example.eblog.model.Post;
import com.example.eblog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/posts")
    public Mono<Post> newPost(@RequestBody PostInputRequest postInputRequest){

        Post lclPost = Post.builder()
                .title(postInputRequest.getTitle())
                .content(postInputRequest.getContent())
                .build();

        Mono<Post> post = postRepository.save(lclPost);

        return post;
    }

    @GetMapping("/posts")
    Flux<Post> allPosts() {
        return this.postRepository.findAll();
    }

    @GetMapping("/recentPosts")
    public Flux<Post> recentPosts(
            @Param("limit") Integer limit, @Param("offset") Integer offset,
            @Param("orderBy") String orderBy){

        log.info("recentPosts, params: {}, {}", limit, offset);
        PageRequest pageRequest = PageRequest.of(limit, offset, Sort.Direction.DESC, orderBy);
        return postRepository.findAll();
    }

}
