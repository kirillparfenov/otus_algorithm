package dev.parfenov.lesson_10_двоичные_деревья_поиска_АВЛ;

/**
 * <p>В дереве AVL коэффициент баланса узла может принимать только одно из значений 1, 0 или -1.
 * <p>Малый поворот направо работает в том случае, если левый дочерний элемент больше либо равен правого дочернего элемента.
 * <p>Большой поворот направо делается в два подхода: сначала малый поворот налево, затем малый поворот направо.
 */
public class AVL {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.add(50);
        tree.add(10);
        tree.add(70);
        tree.add(60);
        tree.add(80);
        tree.add(65);
        tree.add(55);
        tree.add(100);
        tree.add(200);
        tree.add(300);
        tree.add(400);
        tree.add(500);

        AVLNode node = tree.search(100);
    }

    public static class AVLTree {
        AVLNode root;

        public void add(int value) {
            root = add(root, value);
        }

        private AVLNode add(AVLNode node, int value) {
            if (node == null)
                return new AVLNode(value);
            else if (value > node.value)
                node.right = add(node.right, value);
            else if (value < node.value)
                node.left = add(node.left, value);

            return rebalanced(node);
        }

        public AVLNode search(int value) {
            return search(root, value);
        }

        private AVLNode search(AVLNode node, int value) {
            if (node == null) return null;
            if (node.value == value) return node;
            return value > node.value ?
                    search(node.right, value) :
                    search(node.left, value);
        }

        /**
         * Высота узла
         */
        public int height(AVLNode node) {
            return node == null ? -1 : node.height;
        }

        /**
         * Высота узла считается по правилу:
         * <p> Взять MAX значение высоты его дочерних элементов и выполнить + 1
         */
        public void updateHeight(AVLNode node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        /**
         * Баланс - это разница между правым и левым дочерними узлами.
         */
        public int getBalance(AVLNode node) {
            return node == null ? 0 : height(node.right) - height(node.left);
        }

        /**
         * Направо поворачиваем только если левый потомок >= правого потомка.
         * <p> то есть getBalance(node) < -1
         * <p> в этом случае мы поднимем левого потомка наверх и его высота уменьшится.
         */
        public AVLNode rightRotate(AVLNode parent) {
            //для малого поворота направо берем левого потомка
            AVLNode child = parent.left;
            AVLNode childChild = child.right;

            //у потомка берем правого потомка
            child.right = parent;
            parent.left = childChild;

            //пересчет высоты после поворота
            updateHeight(parent);
            updateHeight(child);

            //левый потомок встал выше
            return child;
        }

        /**
         * Малый поворот направо целесообразен когда правый потомок >= левого потомка.
         * <p> То есть getBalance(node) > 1
         * <p> в этом случае правый потомок поднимется наверх и его высота уменьшится.
         */
        public AVLNode leftRotate(AVLNode parent) {
            AVLNode child = parent.right;
            AVLNode childChild = child.left;

            child.left = parent;
            parent.right = childChild;

            updateHeight(parent);
            updateHeight(child);

            return child;
        }

        public AVLNode rebalanced(AVLNode node) {
            //сначала обновим значение высоты узла
            updateHeight(node);
            //и узнаем баланс этого узла
            int balance = getBalance(node);

            //если положительный дисбаланс - поворот налево, чтобы поднять правого потомка наверх
            if (balance > 1) {
                //если у потомка правый потомок больше левого потомка, то делаем обычный поворот налево
                if (height(node.right.right) > height(node.right.left)) {
                    node = leftRotate(node);
                    //иначе нужно сделать большой поворот налево
                } else {
                    node.right = rightRotate(node.right);
                    node = leftRotate(node);
                }
                //иначе поворот направо, чтобы поднять левого потомка наверх
            } else if (balance < -1) {
                //если левый потомок левого потомка > правого потомка, то делаем обычный поворот направо
                if (height(node.left.left) > height(node.left.right)) {
                    node = rightRotate(node);
                    // иначе нужен большой поворот направо
                } else {
                    node.left = leftRotate(node.left);
                    node = rightRotate(node);
                }
            }
            return node;
        }
    }

    public static class AVLNode {
        int height;
        int value;
        AVLNode left, right;

        public AVLNode(int value) {
            this.value = value;
        }
    }
}
