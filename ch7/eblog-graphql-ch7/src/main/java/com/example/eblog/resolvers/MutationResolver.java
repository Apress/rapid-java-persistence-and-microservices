package com.example.eblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.eblog.dto.AuthorInputRequest;
import com.example.eblog.dto.PostInputRequest;
import com.example.eblog.model.Author;
import com.example.eblog.model.Post;
import com.example.eblog.model.Status;
import com.example.eblog.repository.AuthorRepository;
import com.example.eblog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;

    public Post newPost(PostInputRequest postInputRequest){

        Author author = authorRepository.findById(postInputRequest.getAuthorId())
                                            .orElse(new Author("Raj", 35));
        if(author.getId() == null)
            authorRepository.save(author);

        Post post = Post.builder()
                            .title(postInputRequest.getTitle())
                            .content(postInputRequest.getContent())
                            .author(author)
                            .status(Status.ACTIVE)
                            .build();

        post = postRepository.save(post);

        return post;
    }

    public Author newAuthor(AuthorInputRequest authorInputRequest)   {
        Author author = new Author(authorInputRequest.getName(), authorInputRequest.getAge());
        author = authorRepository.save(author);
        return author;
    }

}
