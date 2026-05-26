package com.divyesh.panchasara.SQLAutoCompleter.model;

import com.divyesh.panchasara.SQLAutoCompleter.beans.Trie;

import java.time.LocalDateTime;

public class ConnectionSession {
    private String sessionID;
    private Trie trie;
    private ConnectionRequest connectionRequest;
    private LocalDateTime lastAccessedTime;

    public ConnectionSession() {
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public Trie getTrie() {
        return trie;
    }

    public void setTrie(Trie trie) {
        this.trie = trie;
    }

    public ConnectionRequest getConnectionRequest() {
        return connectionRequest;
    }

    public void setConnectionRequest(ConnectionRequest connectionRequest) {
        this.connectionRequest = connectionRequest;
    }

    public LocalDateTime getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(LocalDateTime lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }
}
