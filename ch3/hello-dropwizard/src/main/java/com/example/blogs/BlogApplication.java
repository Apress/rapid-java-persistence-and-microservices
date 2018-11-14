package com.example.blogs;

import com.example.blogs.api.PostResource;
import com.example.blogs.config.BlogAppConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BlogApplication extends Application<BlogAppConfig> {

    @Override
    public String getName() {
        return "BlogApplication";
    }

    @Override
    public void initialize(Bootstrap<BlogAppConfig> bootstrap) {
        log.info("Application initialized");
    }

    @Override
    public void run(BlogAppConfig configuration, Environment environment) throws Exception {
        log.info("Application started");
        final PostResource resource = new PostResource(configuration.getBlogName());
        environment.jersey().register(resource);
    }

    public static void main(String[] args) throws Exception {
        new BlogApplication().run(args);
    }

}
