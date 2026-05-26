package com.divyesh.panchasara.SQLAutoCompleter.controller;

import com.divyesh.panchasara.SQLAutoCompleter.model.SuggestionResponse;
import com.divyesh.panchasara.SQLAutoCompleter.service.AutoCompleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AutoCompleteController {

    private AutoCompleteService autoCompleteService;

    public AutoCompleteController(AutoCompleteService autoCompleteService) {
        this.autoCompleteService = autoCompleteService;
    }

    @GetMapping("/suggest")
    public ResponseEntity<SuggestionResponse> suggest(
            @RequestParam String sessionId,
            @RequestParam String prefix) {
        return ResponseEntity.ok(autoCompleteService.getSuggestions(sessionId, prefix));
    }
}
