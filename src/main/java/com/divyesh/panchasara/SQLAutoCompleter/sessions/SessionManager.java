package com.divyesh.panchasara.SQLAutoCompleter.sessions;

import com.divyesh.panchasara.SQLAutoCompleter.model.ConnectionSession;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    ConcurrentHashMap<String, ConnectionSession> sessionManager = new ConcurrentHashMap<>();

    public void addSession(ConnectionSession session) {
        sessionManager.put(session.getSessionID(), session);
    }

    public ConnectionSession getSession(String sessionID) {
        return sessionManager.get(sessionID);
    }

    public boolean removeSession(String sessionID) {
        return sessionManager.remove(sessionID) != null;
    }
}
