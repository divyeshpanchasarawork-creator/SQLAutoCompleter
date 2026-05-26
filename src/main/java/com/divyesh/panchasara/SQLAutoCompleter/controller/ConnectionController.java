package com.divyesh.panchasara.SQLAutoCompleter.controller;

import com.divyesh.panchasara.SQLAutoCompleter.beans.Trie;
import com.divyesh.panchasara.SQLAutoCompleter.model.ConnectionRequest;
import com.divyesh.panchasara.SQLAutoCompleter.model.ConnectionSession;
import com.divyesh.panchasara.SQLAutoCompleter.service.ConnectionService;
import com.divyesh.panchasara.SQLAutoCompleter.service.SchemaExtractorService;
import com.divyesh.panchasara.SQLAutoCompleter.service.TrieBuilderService;
import com.divyesh.panchasara.SQLAutoCompleter.sessions.SessionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class ConnectionController {
    private final ConnectionService connectionService;
    private final SchemaExtractorService schemaExtractorService;
    private final TrieBuilderService trieBuilderService;
    private final SessionManager sessionManager;
    private final List<String> sqlKeywords;

    public ConnectionController(ConnectionService connectionService,
                                SchemaExtractorService schemaExtractorService,
                                TrieBuilderService trieBuilderService,
                                SessionManager sessionManager,
                                List<String> sqlKeywords) {
        this.connectionService = connectionService;
        this.schemaExtractorService = schemaExtractorService;
        this.trieBuilderService = trieBuilderService;
        this.sessionManager = sessionManager;
        this.sqlKeywords = sqlKeywords;
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connect(@RequestBody ConnectionRequest request) {
        Connection connection = connectionService.getConnection(request);
        List<String> schema = schemaExtractorService.extractSchema(connection);
        Trie trie = new Trie();
        sqlKeywords.forEach(trie::insert);
        trieBuilderService.buildTrie(schema, trie);

        ConnectionSession session = new ConnectionSession();
        session.setSessionID(UUID.randomUUID().toString());
        session.setTrie(trie);
        session.setConnectionRequest(request);
        session.setLastAccessedTime(LocalDateTime.now());
        sessionManager.addSession(session);

        return ResponseEntity.ok(session.getSessionID());
    }
}
