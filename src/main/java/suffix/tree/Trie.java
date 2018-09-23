package suffix.tree;

import java.io.*;
import java.util.*;

public class Trie {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner(String path) throws FileNotFoundException {
            in = new BufferedReader(new FileReader(path));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        return buildTrie(Arrays.asList(patterns));
    }

    public List<Map<Character, Integer>> buildTrie(List<String> patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<>();
        Map<Character, Integer> root = new HashMap<>();
        root.put(patterns.get(0).charAt(0), 1);
        trie.add(root);
        trie.add(new HashMap<>());
        int newNodeIndex = 1;

        for (String pattern : patterns) {
            Map<Character, Integer> currentRoot = trie.get(0);
            for (int i = 0; i < pattern.length(); i++) {
                char ch = pattern.charAt(i);
                if (currentRoot.containsKey(ch)) {
                    Integer index = currentRoot.get(ch);
                    currentRoot = trie.get(index);
                } else {
                    currentRoot.put(ch, ++newNodeIndex);
                    trie.add(new HashMap<>());
                    currentRoot = trie.get(newNodeIndex);
                }
            }
        }

        return trie;
    }


    static public void main(String[] args) throws IOException {
        String path = Trie.class.getClassLoader().getResource("trie/sample1").getFile();
        new Trie().run(path);
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run(String path) throws IOException {
        FastScanner scanner = new FastScanner(path);
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
