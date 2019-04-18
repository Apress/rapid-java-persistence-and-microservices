package com.example.blogs.api;

import com.codahale.metrics.annotation.Timed;
import com.example.blogs.model.Post;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private String blogName;

    public PostResource(String blogName)   {
        this.blogName = blogName;
    }

    @GET
    @Timed
    public Post getRandomPost(@QueryParam("name") Optional<String> name) {
        return new Post(1l, name.orElse("This is a random post name") + " from " + blogName );
    }

}
