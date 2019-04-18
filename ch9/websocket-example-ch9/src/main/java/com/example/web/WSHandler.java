package com.example.web;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WSHandler {

    MessageService messageService = MessageService.getInstance();

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        String username = messageService.newUserJoined(session);
        messageService.broadcastMessage("Server", username + " joined the chat");
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        String username = messageService.removeUser(session);
        messageService.broadcastMessage("Server", username + " left the chat");
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        String username = messageService.getUser(session);
        messageService.broadcastMessage(username, message);
    }

}
