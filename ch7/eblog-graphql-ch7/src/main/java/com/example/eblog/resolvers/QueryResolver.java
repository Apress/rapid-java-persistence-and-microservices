package com.example.eblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.eblog.model.Author;
import com.example.eblog.model.Post;
import com.example.eblog.repository.AuthorRepository;
import com.example.eblog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;

    public List<Post> recentPosts(Integer limit, Integer offset, String orderBy){
        log.info("recentPosts, params: {}, {}", limit, offset);
        PageRequest pageRequest = PageRequest.of(limit, offset, Sort.Direction.DESC, orderBy);
        return postRepository.findAll(pageRequest).getContent();
    }

    public List<Author> authorsWithTopPosts()   {
        log.info("authorsWithTopPosts");
        return authorRepository.findAuthorsWithPosts();
    }

}
