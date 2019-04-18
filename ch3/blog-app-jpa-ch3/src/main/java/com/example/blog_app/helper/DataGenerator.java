package com.example.blog_app.helper;

import com.example.blog_app.model.jpa.Blog;
import com.example.blog_app.model.jpa.Post;
import com.example.blog_app.model.jpa.PostStatus;
import com.example.blog_app.model.jpa.User;
import com.example.blog_app.repository.BlogRepository;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@Data
public class DataGenerator {

    private AtomicBoolean initialized = new AtomicBoolean(false);

    List<String> blogNames = Arrays.asList("PartyBlog", "ScienceBlog");

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Transactional
    public void generateSampleData()    {

        this.initialized.compareAndSet(false, true);

        //log.info("data being generated");
        List<Blog> blogs = new ArrayList<>();

        User user1 = new User( "Akira", "password", "none@none.com");
        User user2 = new User( "Lisa", "password", "none@none.com");
        User user3 = new User( "James", "password", "none@none.com");

        userRepository.save(user1);
        userRepository.refresh(user1);
        userRepository.save(user2);
        userRepository.refresh(user2);
        userRepository.save(user3);
        userRepository.refresh(user3);

        blogNames.forEach(name->{

            Blog blog = Blog.builder()
                    .name(name)
                    .guid(UUID.randomUUID())
                    .about("Sample blog")
                    .publishedAt(LocalDateTime.now()).build();

            blogRepository.save(blog);
            blogRepository.refresh(blog); //populate generated id
            blogs.add(blog);

            Post post1 = Post.builder()
                    .title("Lorem Ispum1")
                    .content("Sample content1")
                    .user(user1)
                    .blog(blog)
                    .postStatus(PostStatus.ACTIVE)
                    .build();

            Post post2 = Post.builder()
                    .title("Lorem Ispum2")
                    .content("Sample content2")
                    .user(user2)
                    .blog(blog)
                    .postStatus(PostStatus.ACTIVE)
                    .build();

            Post post3 = Post.builder()
                    .title("Lorem Ispum3")
                    .content("Sample content3")
                    .user(user3)
                    .blog(blog)
                    .postStatus(PostStatus.ACTIVE)
                    .build();

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

        });
        user1.setBlogId(blogs.get(0).getId());
        user2.setBlogId(blogs.get(0).getId());
        user3.setBlogId(blogs.get(0).getId());
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //log.info("data generated");

    }
}
