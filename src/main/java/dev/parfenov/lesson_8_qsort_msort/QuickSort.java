package dev.parfenov.lesson_8_qsort_msort;

public class QuickSort {

    private static int[] arr;

    public static void main(String[] args) {
        int[] array = {5, 3, 7, 9, 1, 4, 6};
        quickSort(array);
    }

    public static void quickSort(int[] array) {
        arr = array;
        //указываем начальные границы между которыми будет сортировка
        recursiveQSort(0, arr.length - 1);
    }

    private static void recursiveQSort(int from, int to) {
        //выход из рекурсии
        if (from >= to)
            return;

        //найти середину между левой и правой частью
        //слева - значения меньше pivot. справа - больше pivot
        int middle = getMiddle(from, to);
        recursiveQSort(from, middle - 1);
        recursiveQSort(middle + 1, to);
    }

    private static int getMiddle(int from, int to) {
        //нужно взять pivot, относительно которого будут ДЕЛИТЬСЯ элементы
        int pivot = arr[to];
        //middle - индекс последнего элемента в отсортированной части
        int middle = from - 1;

        //raw - индекс, справа от которого находится НЕОБРАБОТАННАЯ часть
        //в самом начале он указывает на начало
        for (int raw = from; raw <= to; raw++)
            if (arr[raw] <= pivot) {
                //поменять middle с raw
                int temp = arr[++middle];
                arr[middle] = arr[raw];
                arr[raw] = temp;
            }
        return middle;
    }
}
