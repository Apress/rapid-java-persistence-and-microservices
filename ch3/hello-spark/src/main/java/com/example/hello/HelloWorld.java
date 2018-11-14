package com.example.hello;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        
    	get("/hello", (request, response) -> {
    	    return "Hello World";
    	});

    	post("/", (request, response) -> {
    		return "Hello World";
    	});

    	put("/", (request, response) -> {
    		return "Hello World";
    	});

    	delete("/", (request, response) -> {
    		return "Deleted - Hello World";
    	});

    }
}

