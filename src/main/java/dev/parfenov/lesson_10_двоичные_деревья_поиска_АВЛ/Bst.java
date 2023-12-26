package dev.parfenov.lesson_10_двоичные_деревья_поиска_АВЛ;

public class Bst {
    public static void main(String[] args) {
        int[] array = {42, 64, 123, 345, 543, 23, 345, 860, 1};
        int[] result = bTreeSort(array);

        BTree bTree = new BTree();
        for (int i : array)
            bTree.add(i);
        boolean elementNotExists = bTree.search(100);
        boolean elementExists = bTree.search(543);
    }

    //сортировка => рекурсивный обход дерева в глубину слева направо
    public static int[] bTreeSort(int[] array) {
        BTree tree = new BTree();
        for (int i : array)
            tree.add(i);

        //при наличии дублей - результирующий массив уменьшится
        int[] result = new int[tree.elements];
        dfs(tree.root, result);
        return result;
    }

    private static int counter;
    private static void dfs(Node root, int[] result) {
        if (root == null) return;
        dfs(root.left, result);
        result[counter++] = root.value;
        dfs(root.right, result);
    }

    //двоичное дерево
    public static class BTree {
        Node root;
        int elements;

        public void add(int value) {
            root = recursiveAdd(root, value);
        }

        private Node recursiveAdd(Node n, int value) {
            if (n == null) {
                elements++;
                return new Node(value);
            }

            //значение меньше? -> рекурсивно проваливаемся в левую ветку
            if (value < n.value)
                n.left = recursiveAdd(n.left, value);

            //больше? -> рекурсивно идем в правую ветку
            if (value > n.value)
                n.right = recursiveAdd(n.right, value);

            return n;
        }

        public boolean search(int value) {
            return recursiveSearch(root, value);
        }

        //либо найдем искомый элемент, либо упремся в null
        private boolean recursiveSearch(Node node, int value) {
            if (node == null) return false;
            if (node.value == value) return true;
            return value < node.value ?
                    recursiveSearch(node.left, value) :
                    recursiveSearch(node.right, value);
        }
    }

    //узел двоичного дерева
    //может иметь потомков
    public static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }
}
