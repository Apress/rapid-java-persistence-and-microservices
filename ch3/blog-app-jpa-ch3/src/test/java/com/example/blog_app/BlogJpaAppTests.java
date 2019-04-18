package com.example.blog_app;

import com.example.blog_app.model.jpa.File;
import com.example.blog_app.model.jpa.Post;
import com.example.blog_app.model.jpa.PostStatus;
import com.example.blog_app.model.jpa.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(classes = BlogJpaApp.class)
@Slf4j
public class BlogJpaAppTests extends BlogTestBase {

    private boolean generateData = true;

    @PostConstruct
    public void init()  {
        log.info("Checking if already initialized");
        if(dataGenerator.getInitialized().get() == false && generateData)
            dataGenerator.generateSampleData();
    }

    @Test
    @Transactional()
    @Commit
    public void testOneToManyDeletion() {
        log.info("Deleted user : {}", "Akira");
        userCRUDService.deleteUser("Akira");
        userRepository.findAll().stream().forEach(u->
                log.info("User: {}, {}", u.getId(), u.getUsername())
        );
        log.info("Printed all users");
        long count = userRepository.findAll().stream().filter(u ->
                "Akira".equals(u.getUsername())).count();

        Assert.assertEquals(0l, count);
    }

    @Test
    @Transactional
    @Commit
    public void testManyToManyWithPostAndFiles() {

        Post post = postRepository.findById(1l).get();

        File file = new File();
        file.setName("main_image");
        fileRepository.save(file);

        File file1 = new File();
        file1.setName("second_image");
        fileRepository.save(file1);
        fileRepository.refresh(file);
        fileRepository.refresh(file1);

        Set<File> files = post.getFiles();
        if(files == null)
            files = new HashSet<>();
        files.add(file);
        files.add(file1);

        post.setFiles(files);
        postRepository.save(post);

        List<File> allFiles = fileRepository.findAll();
        allFiles.forEach(f->{
            Set<Post> posts = f.getPosts();
            posts.forEach(p->{
                log.info("Post : {} attached with file : {}", p.getTitle(), f.getName());
            });
        });

        post.getFiles().forEach(f->{
            log.info("File: {} attached to Post: {}", f.getName(), post.getTitle());
        });
    }

    @Test
    @Transactional
    @Commit
    public void testForArrayTypes() {
        Post post = Post.builder()
                .title("Sample Title for arrays")
                .content("Sample content for arrays")
                .postStatus(PostStatus.ACTIVE)
                .tags(new String[] {"sample", "text"})
                .build();
        postRepository.save(post);
    }

}
