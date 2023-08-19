package dev.manrihter.lesson_3_алгебраические_алгоритмы;

import java.util.Arrays;

public class Task_11_15_16 {
    public static void main(String[] args) {
        // Реализовать алгоритм возведения в степень через домножение O(N/2+LogN) = O(N)
        double number = 2;
        long power = 6;
        System.out.println(powerRecursive(number, power));
        System.out.println(powerIterate(number, power));

        // Реализовать алгоритм поиска простых чисел с оптимизациями поиска и делением только на простые числа, O(N * Sqrt(N) / Ln (N))
        int n = 1000;
        System.out.println(primeSqrt(n));

        // Реализовать алгоритм "Решето Эратосфена" для быстрого поиска простых чисел O(N Log Log N)
        System.out.println(eratosphene(n));
    }

    // Реализовать алгоритм возведения в степень через домножение O(N/2+LogN) = O(N)
    private static double powerRecursive(double number, long power) {
        if (power == 0) return 1.0;
        if (power == 1) return number;

        if (power % 2 == 0) {
            var p = powerRecursive(number, power / 2);
            System.out.println(p);
            return p * p;
        } else {
            var p = powerRecursive(number, power - 1);
            System.out.println(p);
            return number * p;
        }
    }

    // Итеративный алгоритм:
    // https://habr.com/ru/articles/584662/#:~:text=%D0%BD%D0%B0%D0%B8%D0%B1%D0%BE%D0%BB%D1%8C%D1%88%D0%B5%D0%B9%20%D1%82%D0%BE%D1%87%D0%BD%D0%BE%D1%81%D1%82%D0%B8%20%D0%B2%D1%8B%D1%87%D0%B8%D1%81%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9.-,%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%3A%20%D0%91%D0%B8%D0%BD%D0%B0%D1%80%D0%BD%D0%BE%D0%B5%20%D0%B2%D0%BE%D0%B7%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%20%D1%81%D1%82%D0%B5%D0%BF%D0%B5%D0%BD%D1%8C,-%D0%A3%D0%B2%D0%B5%D0%BB%D0%B8%D1%87%D0%B5%D0%BD%D0%B8%D0%B5%20%D1%81%D0%BA%D0%BE%D1%80%D0%BE%D1%81%D1%82%D0%B8%3A%20%D0%B2
    private static double powerIterate(double number, long power) {
        double v = 1d;
        while(power > 0) {
            // (power & 1) != 0  => последний бит != 0
            if((power & 1) != 0) {
                v *= number;
            }
            number *= number;
            power >>= 1;
        }
        return v;
    }

    // Реализовать алгоритм поиска простых чисел с оптимизациями поиска и делением только на простые числа, O(N * Sqrt(N) / Ln (N))
    private static long primeSqrt(int n) {
        int count = 0;

        for (int i = 2; i <= n; i++){
            if(isPrime(i))
                count++;
        }

        return count;
    }

    private static boolean isPrime(int n) {
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int sqrt = (int) Math.sqrt(n);

        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    // Реализовать алгоритм "Решето Эратосфена" для быстрого поиска простых чисел O(N Log Log N)
    private static long eratosphene(int n) {
        boolean[] marked = new boolean[n + 1];
        Arrays.fill(marked, false);

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!marked[i]) {
                count++;
                for (int m = i * i; m < n; m += i) {
                    marked[m] = true;
                }
            }
        }

        return count;
    }
}
