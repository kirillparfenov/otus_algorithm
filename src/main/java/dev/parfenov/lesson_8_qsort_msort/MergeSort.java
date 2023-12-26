package dev.parfenov.lesson_8_qsort_msort;

public class MergeSort {
    private static int[] arr;

    public static void main(String[] args) {
        int[] array = {534, 43, 768, 31, 0, 43, 1, 7, 9, 5, 34, 875, 54, 234, 7, 478, 522, 6};
        mSort(array);
    }

    public static void mSort(int[] array) {
        arr = array;
        recursiveMergeSort(0, arr.length - 1);
    }

    private static void recursiveMergeSort(int left, int right) {
        if (left >= right)
            return;
        int middle = (left + right) / 2;
        recursiveMergeSort(left, middle);
        recursiveMergeSort(middle + 1, right);
        merge(left, middle, right);
    }

    //left - указатель на начало левой отсортированной части
    //middle - указатель на конец левой отсортированной части
    //right - указатель на начало правой отсортированной части
    private static void merge(int left, int middle, int right) {
        int[] tempArray = new int[right - left + 1];
        int a = left, b = middle + 1, temp = 0;
        while (a <= middle && b <= right)
            if (arr[a] > arr[b])
                tempArray[temp++] = arr[b++];
            else
                tempArray[temp++] = arr[a++];

        //если что-то осталось в левой части - переписываем в массив
        while (a <= middle)
            tempArray[temp++] = arr[a++];

        //если что-то осталось в правой части - переписываем в массив
        while (b <= right)
            tempArray[temp++] = arr[b++];

        for (int i = left; i <= right; i++)
            arr[i] = tempArray[i - left];
    }
}
