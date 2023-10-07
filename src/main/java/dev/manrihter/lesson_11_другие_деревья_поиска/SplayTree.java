package dev.manrihter.lesson_11_другие_деревья_поиска;

public class SplayTree {
    Node root;

    // добавляем ноду и перемещаем ее в root -- O(log n)
    public void insertNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            Node localRoot = root;
            //итеративно идем вглубь дерева, чтобы добавить элемент
            while (true) {
                // идем в левое поддерево
                if (node.value < localRoot.value) {
                    //проваливаемся до тех пор, пока не упремся в null-лист
                    if (localRoot.left != null)
                        localRoot = localRoot.left;
                    else {
                        localRoot.left = node;
                        splayNode(localRoot.left);
                        break;
                    }
                }
                // идем в правое поддерево
                else {
                    if (localRoot.right != null)
                        localRoot = localRoot.right;
                    else {
                        localRoot.right = node;
                        splayNode(localRoot.right);
                        break;
                    }
                }
            }
        }
    }

    //zig(правый) - поворот O(1)
    private Node rotateRight(Node parent) {
        var leftChild = parent.left;
        var rightChildChild = leftChild.right;

        leftChild.right = parent;
        parent.left = rightChildChild;

        return leftChild;
    }

    //zag(левый) - поворот O(1)
    private Node rotateLeft(Node parent) {
        var rightChild = parent.right;
        var leftChildChild = rightChild.left;

        rightChild.left = parent;
        parent.right = leftChildChild;

        return rightChild;
    }

    // splay - операция, которая поднимает node в root -- O(log n)
    public void splayNode(Node node) {
        root = splayNode(root, node);
    }

    private Node splayNode(Node root, Node nodeToSplay) {
        // если ссылки совпали - значит мы достигли root
        if (root == nodeToSplay)
            return root;

        if (root.value > nodeToSplay.value) {
            //если отсутствует левый дочерний элемент, то splay не выполняется
            if (root.left == null)
                return root;

            //иначе
            if (root.left.value > nodeToSplay.value) {
                // zigzig поворот
                root.left.left = splayNode(root.left.left, nodeToSplay);
                root = rotateRight(root);
            } else if (root.left.value < nodeToSplay.value) {
                // zigzag поворот
                root.left.right = splayNode(root.left.right, nodeToSplay);

                if (root.left.right != null)
                    // сделаем zag поворот на root.left
                    root.left = rotateLeft(root.left);
            }
            // если у поддерева нет левой ноды, то повернем дерево направо
            return (root.left == null) ? root : rotateRight(root);
        } else {
            if (root.right == null)
                return root;

            if (root.right.value > nodeToSplay.value) {
                root.right.left = splayNode(root.right.left, nodeToSplay);
                if (root.right.left != null)
                    root.right = rotateRight(root.right);
            } else if (root.right.value < nodeToSplay.value) {
                root.right.right = splayNode(root.right.right, nodeToSplay);
                root = rotateLeft(root);
            }
            return (root.right == null) ? root : rotateLeft(root);
        }
    }

    public static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        var splayTree = new SplayTree();

        splayTree.insertNode(new Node(1));
        splayTree.insertNode(new Node(2));
        splayTree.insertNode(new Node(5));
        splayTree.insertNode(new Node(6));
        splayTree.insertNode(new Node(26));
        splayTree.insertNode(new Node(79));
        splayTree.insertNode(new Node(4));
        splayTree.insertNode(new Node(8));
    }
}
