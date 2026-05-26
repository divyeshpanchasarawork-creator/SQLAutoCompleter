package com.divyesh.panchasara.SQLAutoCompleter.beans;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;

        for (char ch: word.toCharArray()) {
            if (!curr.getChildren().containsKey(ch)) {
                curr.getChildren().put(ch, new TrieNode());
            }
            curr = curr.getChildren().get(ch);
        }

        curr.setEnd(true);
        curr.setWord(word);
    }

    public boolean search(String word) {
        TrieNode curr = root;

        for (char ch: word.toCharArray()) {
            if (!curr.getChildren().containsKey(ch)) return false;
            curr = curr.getChildren().get(ch);
        }

        return curr.isEnd();
    }

    public boolean searchPrefix(String word) {
        TrieNode curr = root;

        for (char ch: word.toCharArray()) {
            if (!curr.getChildren().containsKey(ch)) return false;
            curr = curr.getChildren().get(ch);
        }

        return true;
    }

    public List<String> wordsWithPrefix(String prefix) {
        TrieNode curr = root;
        List<String> words = new ArrayList<>();

        for (char ch: prefix.toCharArray()) {
            if (!curr.getChildren().containsKey(ch)) return words;
            curr = curr.getChildren().get(ch);
        }

        findAllWords(curr, words);

        return words;
    }

    private void findAllWords(TrieNode root, List<String> words) {
        if (root == null) return;
        if (root.isEnd()) {
            words.add(root.getWord());
        }
        for (TrieNode node: root.getChildren().values()) {
            findAllWords(node, words);
        }
    }
    // hi
}