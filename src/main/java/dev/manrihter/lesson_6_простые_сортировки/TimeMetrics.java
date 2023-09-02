package dev.manrihter.lesson_6_простые_сортировки;

import java.util.Random;
import java.util.function.Consumer;

public class TimeMetrics {
    public static void main(String[] args) {
        int n = 100;
        while (n <= 100_000) {
            var array = randomArray(n);

            timeTracker(BubbleSort::bubbleSort, array, "BubbleSort");
            timeTracker(InsertionSort::insertionSort, array, "InsertionSort");
            timeTracker(ShellSort::shellSort, array, "ShellSort");

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
