package dev.parfenov.lesson_16_префиксное_дерево;

public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        var v1 = trie.search("apple");   // return True
        var v2 = trie.search("app");     // return False
        var v3 = trie.startsWith("app"); // return True
        trie.insert("app");
        var v4 = trie.search("app");     // return True
        System.out.println();
    }

    private static final int SIZE = 128;
    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        var node = root;
        //с каждой последующей буквой мы проваливаемся глубже в префиксном дереве
        for (char c : word.toCharArray())
            node = node.next(c);

        //как только закончили перебирать наше слово - присваиваем последней ноде iEnd = true
        node.isEnd = true;
    }

    public boolean search(String word) {
        var node = find(word);
        if (node == null)
            return false;

        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    public Node find(String word) {
        var node = root;
        for (char c : word.toCharArray())
            if(node.child[c] == null)
                return null;
            else
                node = node.next(c);
        return node;
    }

    private static class Node {
        Node[] child;
        boolean isEnd;

        public Node() {
            child = new Node[SIZE];
        }

        /**
         * Создаем элемент в массиве, если его еще нет и возвращаем его
         * */
        public Node next(char c) {
            if (child[c] == null)
                child[c] = new Node();
            return child[c];
        }
    }
}
