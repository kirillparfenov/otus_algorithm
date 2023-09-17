package dev.manrihter.lesson_9_линейная_сортировка;

import dev.manrihter.lesson_8_qsort_msort.MergeSort;
import dev.manrihter.lesson_8_qsort_msort.QuickSort;

import java.util.Random;
import java.util.function.Consumer;

public class TimeMetrics {
    public static void main(String[] args) {
        int n = 100;
        while (n <= 100_000_000) {
            var array = randomPositiveArray(n);
            timeTracker(BucketSort::bucketSort, array, "BucketSort");

            array = randomPositiveArray(n);
            timeTracker(RadixSort::radixSort, array, "RadixSort");

            n *= 10;
            System.out.println();
        }
    }

    private static int[] randomPositiveArray(int n) {
        var random = new Random(43231);
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(1000);
        return array;
    }

    private static void timeTracker(Consumer<int[]> sort, int[] array, String sortName) {
        var start = System.currentTimeMillis();
        sort.accept(array);
        System.out.println(String.format(sortName + " for %s elements: %s ms", array.length, System.currentTimeMillis() - start));
    }
}
