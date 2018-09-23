package suffix.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    public static List<Map<Character, Integer>> buildTrie(List<String> patterns) {
        List<Map<Character, Integer>> result = new ArrayList<>();
        HashMap<Character, Integer> temp = new HashMap<>();
        temp.put(patterns.get(0).charAt(0), 1);
        result.add(temp);
        int nextNodeIndex = 2;
        for (String pattern : patterns) {
            Map<Character, Integer> root = result.get(0);
            for (int i = 0; i < pattern.length(); i++) {
                char ch = pattern.charAt(i);
                if (root.containsKey(ch)) {
                    Integer key = root.get(ch);
                    if (result.size() > key) {
                        root = result.get(key);
                    } else {
                        root = new HashMap<>();
                        result.add(root);
                    }
                } else {
                    root.put(ch, nextNodeIndex++);
                    Map<Character, Integer> newNode = new HashMap<>();
                    result.add(newNode);
                    root = newNode;
                }
            }

        }
        return result;
    }
}

