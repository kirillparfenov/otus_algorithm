package dev.parfenov.lesson_9_линейная_сортировка;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {42, 64, 123, 786, 435, 243, 647, 98, 562, 456, 8, 6, 504, 29, 85347, 534, 41, 123, 35, 758, 543, 23, 345, 860, 1};
        radixSort(array);
    }

    public static void radixSort(int[] array) {
        //найти максимальное число
        int maxValue = Integer.MIN_VALUE;
        for (int v : array)
            maxValue = Math.max(v, maxValue);

        //посчитать разрядность
        int maxNumberLength = (int)Math.log10(maxValue) + 1;

        //подсчет для каждой цифры
        int[] numbers = new int[10];
        int[] result = new int[array.length];

        int divider = 1, constant = 10;

        //двигаться по разрядам справа налево
        while (maxNumberLength-- > 0) {
            //подсчет цифр
            for (int i = array.length - 1; i >= 0; i--)
                numbers[(array[i] / divider) % constant]++;

            //суммируем
            for (int i = 1; i < numbers.length; i++)
                numbers[i] += numbers[i - 1];

            //сортировка по цифре
            for (int i = array.length - 1; i >= 0; i--) {
                int position = --numbers[(array[i] / divider) % constant];
                result[position] = array[i];
            }

            //копируем result в изначальный array
            for (int i = 0; i < array.length; i++)
                array[i] = result[i];

            //обнуление массива numbers
            Arrays.fill(numbers, 0);

            //Увеличение делителя для получения следующей цифры в числе
            divider *= 10;
        }
    }
}
