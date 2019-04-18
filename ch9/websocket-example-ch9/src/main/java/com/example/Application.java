package com.example;

import com.example.web.TweetController;
import com.example.web.WSHandler;

import static spark.Spark.init;
import static spark.Spark.webSocket;

public class Application {
    public static void main(String[] args) {
        webSocket("/messages", WSHandler.class);
        TweetController tweetController = new TweetController();
    }
}
