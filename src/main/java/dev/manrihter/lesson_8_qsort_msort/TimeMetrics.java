package dev.manrihter.lesson_8_qsort_msort;

import dev.manrihter.lesson_7_сортировка_выбором_heap_sort.HeapSort;
import dev.manrihter.lesson_7_сортировка_выбором_heap_sort.SelectionSort;

import java.util.Random;
import java.util.function.Consumer;

public class TimeMetrics {
    public static void main(String[] args) {
        int n = 100;
        while (n <= 10_000_000) {
            var array = randomArray(n);

            timeTracker(QuickSort::quickSort, array, "QuickSort");
            timeTracker(MergeSort::mSort, array, "MergeSort");

            n *= 10;
            System.out.println();
        }
    }

    private static int[] randomArray(int n) {
        var random = new Random(43231);
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt();
        return array;
    }

    private static void timeTracker(Consumer<int[]> sort, int[] array, String sortName) {
        var start = System.currentTimeMillis();
        sort.accept(array);
        System.out.println(String.format(sortName + " for %s elements: %s ms", array.length, System.currentTimeMillis() - start));
    }
}
