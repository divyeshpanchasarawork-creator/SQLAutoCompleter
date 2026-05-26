package com.divyesh.panchasara.SQLAutoCompleter.service;

import com.divyesh.panchasara.SQLAutoCompleter.beans.Trie;
import com.divyesh.panchasara.SQLAutoCompleter.model.ConnectionSession;
import com.divyesh.panchasara.SQLAutoCompleter.model.SuggestionResponse;
import com.divyesh.panchasara.SQLAutoCompleter.sessions.SessionManager;
import org.springframework.stereotype.Service;

@Service
public class AutoCompleteService {
    private SessionManager sessionManager;

    public AutoCompleteService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public SuggestionResponse getSuggestions(String sessionId, String prefix) {
        ConnectionSession session  = sessionManager.getSession(sessionId);
        if (session == null) {
            throw new RuntimeException("Session not found: " + sessionId);
        }
        Trie trie = session.getTrie();
        SuggestionResponse response = new SuggestionResponse();
        response.setPrefix(prefix);
        response.setWords(trie.wordsWithPrefix(prefix));
        return response;
    }
}
