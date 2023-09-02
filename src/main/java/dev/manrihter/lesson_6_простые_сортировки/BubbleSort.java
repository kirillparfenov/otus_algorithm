package dev.manrihter.lesson_6_простые_сортировки;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {2,5,1,0,6,3,2,7};
        bubbleSort(array);
    }

    public static void bubbleSort(int[] array) {
        int startIndex = array.length - 1;
        for (int i = startIndex; i > 0; i--)
            for (int k = 0; k < i; k++)
                swap(k, array);
    }

    private static void swap(int k, int[] array) {
        if (array[k] > array[k + 1]) {
            //3 операции присваивания
            int temp = array[k];
            array[k] = array[k + 1];
            array[k + 1] = temp;
        }
    }
}
