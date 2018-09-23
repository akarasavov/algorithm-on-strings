package suffix.tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrieMatching implements Runnable {

    List<Integer> solve(String text, List<String> patterns) {
        List<Integer> result = new ArrayList<>();
        Trie trie = new Trie();

        List<Map<Character, Integer>> maps = trie.buildTrie(patterns);

        for (String pattern : patterns) {
            result.addAll(findIndex(text, maps, pattern.length()));
        }

        // write your code here

        return result;
    }

    List<Integer> findIndex(String text, List<Map<Character, Integer>> trie, int patternSize) {
        List<Integer> startPosition = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            boolean match = match(text.substring(i), trie, 0, patternSize);
            if (match) {
                startPosition.add(i);
            }
        }

        return startPosition;
    }

    boolean match(String text, List<Map<Character, Integer>> trie, int trieIndex, int patternSize) {
        if (patternSize == 0) {
            return true;
        }
        if (text.isEmpty()) {
            return false;
        }
        Map<Character, Integer> map = trie.get(trieIndex);
        char ch = text.charAt(0);
        return map.containsKey(ch) && match(text.substring(1), trie, map.get(ch), patternSize - 1);
    }


    public void run() {
        try {
            String path = Trie.class.getClassLoader().getResource("trie_matching/sample1").getFile();
            BufferedReader in = new BufferedReader(new FileReader(path));
            String text = in.readLine();
            int n = Integer.parseInt(in.readLine());
            List<String> patterns = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                patterns.add(in.readLine());
            }

            List<Integer> ans = solve(text, patterns);

            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Thread(new TrieMatching()).start();
    }
}
