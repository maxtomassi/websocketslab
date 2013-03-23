package com.toodoo.server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/hello")
public class Server {

    private static List<Session> openSessions = new ArrayList<Session>();

    @OnOpen
    public void onOpenSession(Session session) {
        openSessions.add(session);
    }

    @OnClose
    public void onCloseSession(Session session) {
        openSessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message) {
        for (Session session : openSessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}