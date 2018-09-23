package suffix.tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SuffixTree {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() throws FileNotFoundException {
            in = new BufferedReader(new FileReader(SuffixTree.class.getClassLoader().getResource("suffix_tree/sample2").getFile()));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
    }

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding 
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> tokens = allSubstrings(text, new ArrayList<>());
        List<Map<Character, Integer>> trie = Trie.buildTrie(tokens);
        return buildNode(trie);
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }


    public List<String> buildNode(List<Map<Character, Integer>> graph) {
        List<String> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < graph.size(); i++) {
            Map<Character, Integer> root = graph.get(i);
            for (Map.Entry<Character, Integer> child : root.entrySet()) {
                result.addAll(buildNode(graph, set, child, ""));
            }
        }

        return result;
    }

    public List<String> buildNode(List<Map<Character, Integer>> graph, Set<Integer> passed, Map.Entry<Character, Integer> entry, String string) {
        List<String> result = new ArrayList<>();
        if (passed.contains(entry.getValue())) {
            return result;
        }

        passed.add(entry.getValue());
        string = string + entry.getKey();
        if (entry.getKey().equals('$')) {
            result.add(string);
            return result;
        }

        Map<Character, Integer> root = graph.get(entry.getValue());
        for (Map.Entry<Character, Integer> child : root.entrySet()) {
            result.addAll(buildNode(graph, passed, child, string));
        }

        return result;
    }


    public static List<String> allSubstrings(String str, List<String> result) {
        if (str.length() == 0) {
            return result;
        }
        result.add(str);
        return allSubstrings(str.substring(1), result);
    }



    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }
}
