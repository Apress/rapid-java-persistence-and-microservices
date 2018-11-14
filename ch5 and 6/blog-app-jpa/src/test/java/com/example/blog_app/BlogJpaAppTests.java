package com.example.blog_app;

import com.example.blog_app.model.jpa.Blog;
import com.example.blog_app.model.jpa.File;
import com.example.blog_app.model.jpa.Post;
import com.example.blog_app.model.jpa.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BlogJpaAppTests extends BlogTestBase {

    private boolean generateData = true;

    @PostConstruct
    public void init()  {
        log.info("Checking if already initialized");
        if(dataGenerator.getInitialized().get() == false && generateData)
            dataGenerator.generateSampleData();
    }

    @Test
    public void exampleTestHome() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).isEqualTo("Hello");
    }

    @Test
    @Transactional()
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
    //@Commit
    public void testSaveAndRefresh() {

        User user = User.builder().email("Akira@none.com").isActive(true)
                .username("Akira").password("none").build();

        user = userRepository.save(user);
        log.info("User object's ID: {}, activatedAt: {}", user.getId(), user.getActivatedAt() );

        Assert.assertNull("activatedAt is null", user.getActivatedAt());

        userRepository.refresh(user);

        log.info("Again user object's ID: {}, activatedAt: {}", user.getId(), user.getActivatedAt() );
        Assert.assertNotNull("activatedAt is not null", user.getActivatedAt());
    }

    @Test
    @Transactional
    public void testForOptional()   {

        userRepository.deleteByUsername("Simon");

        User user = User.builder().email("Simon@none.com").isActive(true)
                .username("Simon").password("none").build();
        userRepository.save(user);

        Optional<User> optionalUser = userRepository.findByUsername("Simon");
        Assert.assertNotNull("Found Simon", optionalUser.get());

        long count = userRepository.streamAll().filter(u->u.getUsername()!="Simon").count();
        log.info("Count : {}", count);
        Assert.assertThat("Count is greater than zero", count, greaterThan(0l));
    }


    // NOT WORKING YET
    @Test
    @Transactional
    @Commit
    public void deleteWithoutOrphan()   {

        List<Post> postWithIdAndTitle = postRepository.findPostWithIdAndTitle();

        Blog blog = blogRepository.findById(1l).get();
        Predicate<User> userPredicate = u-> u.getUsername() == "Akira";
        blog.getUsers().removeIf(userPredicate);
        log.info("Users: {}", blog.getUsers());
        blogRepository.saveAndFlush(blog);
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
    public void generate()  {

    }

}
