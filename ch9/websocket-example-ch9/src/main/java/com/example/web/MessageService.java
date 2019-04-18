package com.example.web;

import org.eclipse.jetty.websocket.api.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageService {

    private static MessageService instance = new MessageService();

    public static MessageService getInstance() {
        return instance;
    }

    private MessageService()    {}

    AtomicInteger counter = new AtomicInteger(0);

    Map<Session, String> sessionMap = new ConcurrentHashMap<>();

    public String newUserJoined(Session session) {
        String username = "User" + counter.addAndGet(1);
        sessionMap.put(session, username);
        return username;
    }

    public void broadcastMessage(String sender, String message) {
        System.out.println("Sending another message");
        sessionMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(sender + " : " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public String removeUser(Session session) {
        String username = sessionMap.get(session);
        sessionMap.remove(session);
        return username;
    }

    public String getUser(Session session) {
        return sessionMap.get(session);
    }
}
