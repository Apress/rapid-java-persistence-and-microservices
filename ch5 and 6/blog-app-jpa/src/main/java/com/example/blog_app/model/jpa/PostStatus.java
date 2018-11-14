package com.example.blog_app.model.jpa;

import java.io.Serializable;

public enum PostStatus implements Serializable {

    ACTIVE(1), NOT_ACTIVE(2);

    int status;

    PostStatus(int status)  {
        this.status = status;
    }

    public static PostStatus fromValue(String value) {
        return valueOf(value.toUpperCase());
    }

    public static String toValue(PostStatus postStatus) {
        return postStatus.name().toLowerCase();
    }

    int showStatus() {
        return status;
    }



}
