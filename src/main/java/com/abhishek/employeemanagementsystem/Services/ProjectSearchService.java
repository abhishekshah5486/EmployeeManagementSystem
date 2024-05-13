package com.abhishek.employeemanagementsystem.Services;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
class TrieNode {
    private HashMap<Character, TrieNode>  map;
    private boolean isEnd;
    private int count;
    private Long projectId;

    public TrieNode() {
        map = new HashMap<>();
        isEnd = false;
        count = 0;
        projectId = null;
    }
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProjectSearchService {
    private TrieNode root = new TrieNode();

    public void insertIntoTrie(String str, Long projectId){
        TrieNode curr = root;
        for (int j=0; j<str.length(); j++) {
            char ch = str.charAt(j);
            if (!curr.getMap().containsKey(ch)){
                curr.getMap().put(ch, new TrieNode());
            }
            curr = curr.getMap().get(ch);
            curr.setCount(curr.getCount() + 1);
        }
        curr.setEnd(true);
        curr.setProjectId(projectId);
    }
    // Searching for a string in trie
    public boolean searchIntoTrie(String str){
        TrieNode curr = root;
        for (int j=0; j<str.length(); j++) {
            char ch = str.charAt(j);
            if (!curr.getMap().containsKey(ch)){
                return false;
            }
            curr = curr.getMap().get(ch);
        }
        if (!curr.isEnd()) return false;
        return true;
    }

    // Prefix Search in trie
    public List<Long> prefixSearchIntoTrie(String str){
        TrieNode curr = root;
        List<Long> projectIdList = new ArrayList<>();
        for (int j=0; j<str.length(); j++) {
            char ch = str.charAt(j);
            if (!curr.getMap().containsKey(ch)){
                return projectIdList;
            }
            curr = curr.getMap().get(ch);
        }
        recursiveFiltering(curr, projectIdList);
        return projectIdList;
    }
    // Recursive filtering based on keywords
    public void recursiveFiltering(TrieNode curr, List<Long> projectIdList){
        if (curr.isEnd()) {
            projectIdList.add(curr.getProjectId());
        }
        // Loop through the HashMap using entrySet()
        for (Map.Entry<Character, TrieNode> entry : curr.getMap().entrySet()) {
            curr = entry.getValue();
            recursiveFiltering(curr, projectIdList);
        }
    }

    public void deleteFromTrie(String str){
        TrieNode curr = root;
        for (int j=0; j<str.length(); j++) {
            char ch = str.charAt(j);
            TrieNode temp = curr;
            curr = curr.getMap().get(ch);
            curr.setCount(curr.getCount() - 1);

            if (curr.getCount() == 0) {
                temp.getMap().remove(ch);
            }
        }
        curr.setEnd(false);
        curr.setProjectId(null);
    }
}
