package suffix.tree;

import java.io.*;
import java.util.*;

public class TrieTask {
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
        Trie trie = new Trie();
        return trie.buildTrie(Arrays.asList(patterns));
    }

    static public void main(String[] args) throws IOException {
        String path = TrieTask.class.getClassLoader().getResource("trie/sample3").getFile();
        new TrieTask().run(path);
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
