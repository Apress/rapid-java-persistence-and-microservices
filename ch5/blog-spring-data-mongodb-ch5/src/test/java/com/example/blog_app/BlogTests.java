package com.example.blog_app;

import com.example.blog_app.model.mongo.Comment;
import com.example.blog_app.model.mongo.Post;
import com.example.blog_app.model.mongo.PostStatus;
import com.example.blog_app.repository.PostMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class BlogTests {

    @Autowired
    PostMongoRepository postMongoRepository;

    List<Post> postList = null;

    @Before
    public void setup()    {
        postList = new ArrayList<>();
        IntStream.range(1,2).forEach(i->{
            Post nextSamplePost = createNextSamplePost(i);
            postList.add(nextSamplePost);
        });
    }

    private Post createNextSamplePost(int i) {
        Post post = Post.builder()
                .blogName("Blog" + i)
                .title("Blog Title" + i)
                .content("Blog" + i + " content")
                .postStatus(PostStatus.ACTIVE)
                .userName("User" + i)
                .build();
        Set<Comment> comments = new HashSet<>();
        comments.add(new Comment("Comment" + (i+1)) );
        comments.add(new Comment("Comment" + (i+2)) );
        post.setComments(comments);
        return post;
    }

    @Test
    public void testAddNewPosts()   {
        postMongoRepository.saveAll(postList);
        log.info("All objects: {}", postMongoRepository.findAll());
        Post blogByTitle = postMongoRepository.findByTitle("Blog Title1");
        Assert.assertEquals("Blog names do not match", blogByTitle.getBlogName(), "Blog1");
        log.info("Achieved saving and retrieving back Posts from Mongo");
    }

    @After
    public void tearDown()  {
        postMongoRepository.deleteAll();
    }

}
