package com.divyesh.panchasara.SQLAutoCompleter.model;

import java.util.List;

public class SuggestionResponse {
    private String prefix;
    private List<String> words;

    public SuggestionResponse() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
