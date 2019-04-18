package com.example.web;

import static spark.Spark.*;

public class TweetController {

    MessageService messageService = MessageService.getInstance();

    public TweetController()    {

        post("/tweet", (request, response) -> {
            String message = request.queryParamOrDefault("message", "SERVER");
            messageService.broadcastMessage("Server", message);
            return "Sent";
        });
    }
}
