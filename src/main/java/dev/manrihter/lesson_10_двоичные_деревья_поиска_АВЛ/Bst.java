package dev.manrihter.lesson_10_двоичные_деревья_поиска_АВЛ;

public class Bst {
    public static void main(String[] args) {
        int[] array = {42, 64, 123, 345, 543, 23, 345, 860, 1};
        bTreeSort(array);
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
