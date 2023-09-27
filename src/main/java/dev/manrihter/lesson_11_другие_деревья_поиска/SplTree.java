package dev.manrihter.lesson_11_другие_деревья_поиска;

/**
 * При добавлении нового элемента:
 * <p> 1. Сначала он добавляется в конец на свое место
 * <p> 2. Затем он поднимается наверх при помощи малых поворотов (либо zig-zag/zag-zag и пр.)
 * <p>
 * */
public class SplTree {
    public static void main(String[] args) {

    }

    public static class SplayNode {
        int value;
        SplayNode left, right;

        public SplayNode(int value) {
            this.value = value;
        }
    }

    public static class SplayTree {
        SplayNode root;

        public void add(int value) {
            root = add(root, value);
            //todo сделать поворот
        }

        private SplayNode add(SplayNode node, int value) {
            if(node == null)
                return new SplayNode(value);
            if (value > node.value)
                node.right = add(node.right, value);
            if (value < node.value)
                node.left = add(node.left, value);
            return node;
        }

        private SplayNode rightRotation(SplayNode parent) {
            var leftChild = parent.left;
            var rightChildChild = leftChild.right;

            leftChild.right = parent;
            parent.left = rightChildChild;

            return leftChild;
        }

        private SplayNode leftRotation(SplayNode parent) {
            var rightChild = parent.right;
            var leftChildChild = rightChild.left;

            rightChild.left = parent;
            parent.right = leftChildChild;

            return rightChild;
        }
    }
}
