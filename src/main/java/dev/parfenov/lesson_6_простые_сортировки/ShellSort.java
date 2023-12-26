package dev.parfenov.lesson_6_простые_сортировки;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = {3, 6, 1, 33, 5, 0, 23, 10, 12, 2, 8, 66, 5};
        shellSort(array);
    }

    public static void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 4)
            for (int j = gap; j < array.length; j++)
                for (int k = j; k >= gap && array[k - gap] > array[k]; k -= gap) {
                    int temp = array[k - gap];
                    array[k - gap] = array[k];
                    array[k] = temp;
                }
    }
}
