package dev.parfenov.lesson_7_сортировка_выбором_heap_sort;

public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {3211231, 4, 6, 341, 0, 7, 8, 22, 1, 4, 3, 54};
        selectionSort(array);
    }

    public static void selectionSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            var indexOfMax = i;
            for (int k = 0; k < i; k++)
                if (array[k] > array[indexOfMax])
                    indexOfMax = k;

            swap(array, i, indexOfMax);
        }
    }

    private static void swap(int[] array, int i, int indexOfMax) {
        var temp = array[indexOfMax];
        array[indexOfMax] = array[i];
        array[i] = temp;
    }
}
