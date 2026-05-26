package com.divyesh.panchasara.SQLAutoCompleter.service;

import com.divyesh.panchasara.SQLAutoCompleter.beans.Trie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieBuilderService {
    public void buildTrie(List<String> schema, Trie trie) {
        for (String s: schema) trie.insert(s);
    }
}
