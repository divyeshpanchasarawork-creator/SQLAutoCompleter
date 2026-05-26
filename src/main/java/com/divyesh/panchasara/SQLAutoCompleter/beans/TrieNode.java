package com.divyesh.panchasara.SQLAutoCompleter.beans;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean isEnd;
    private String word;

    public TrieNode() {
        children = new HashMap<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }
}
