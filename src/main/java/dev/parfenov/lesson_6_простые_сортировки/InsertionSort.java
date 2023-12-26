package dev.parfenov.lesson_6_простые_сортировки;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {4, 6, 341, 0, 7, 8, 22, 1, 4, 3, 54};
        insertionSort(array);
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++)
            for (int k = i - 1; k >= 0 && array[k] > array[k + 1]; k--)
                swap(k, array);
    }

    private static void swap(int i, int[] array) {
        int temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
    }
}
