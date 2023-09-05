package dev.manrihter.lesson_7_сортировка_выбором_heap_sort;

/*
* левый потомок: 2x + 1
* правый потомок: 2x + 2
* родитель потомка: (x - 1)/2
*
* строить кучу начинаем с индекса последнего родителя:
*   -- последний индекс в массиве arr.length - 1
*   -- получение родителя: (x - 1)/2 -> (arr.length - 1 - 1) / 2 -> arr.length/2 - 1
*

        1
       /  \
      4    2
    /   \
   6     5
  / \
 8   1
Шаги:
* 1) Берем 3 (root) (int L = root)
* 2) Сравниваем if (arr[L](8) > arr[X](6)) -> X = L
* 3) Сравниваем if (arr[R](1) > arr[X](8)) -> X не меняется
* 4) Меняем значения arr[X] и arr[root]
* 5) Рекурсивно вызываем этот метод, в качестве root передаем x
* */
public class HeapSort {

    private static int[] array;

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        heapSort(array);
    }

    public static void heapSort(int[] arr) {
        array = arr;
        // шаг 1: построить кучу, чтобы в нулевой позиции всегда был max число
        int lastParent = (array.length - 1 - 1) / 2;
        for (int i = lastParent; i >= 0; i--)
            heapify(i, array.length);

        //шаг 2: используя сортировку выбором кладем нулевой элемент в конец и перестраиваем кучу
        for (int i = array.length - 1; i > 0; i--) {
            swap(0, i);
            heapify(0, i);
        }
    }

    private static void heapify(int root, int sizeLimit) {
        int x = root;
        int L = 2 * root + 1;
        int R = 2 * root + 2;
        if (L < sizeLimit && array[L] > array[x]) x = L;
        if (R < sizeLimit && array[R] > array[x]) x = R;
        if (x == root) return;
        swap(x, root);
        heapify(x, sizeLimit);
    }

    private static void swap(int x, int root) {
        int temp = array[root];
        array[root] = array[x];
        array[x] = temp;
    }
}
