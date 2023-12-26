package dev.parfenov.lesson_3_алгебраические_алгоритмы;


public class Task_01_02_03 {
    public static void main(String[] args) {
        // Реализовать итеративный O(N) алгоритм возведения числа в степень.
        double number = 1.00001;
        long power = 100000;
        System.out.println(powerIterate(number, power));

        // Реализовать рекурсивный O(2^N) и итеративный O(N) алгоритмы поиска чисел Фибоначчи.
        int n = 30;
        System.out.println(fiboRecursive(n));
        System.out.println(fiboIterative(n));

        // Реализовать алгоритм поиска количества простых чисел через перебор делителей, O(N^2).
        System.out.println(primes(1));
    }

    // Реализовать итеративный O(N) алгоритм возведения числа в степень.
    private static double powerIterate(double number, long power) {
        if (power == 0) return 1.0;
        if (power == 1) return number;

        double result = 1;
        while (power > 0) {
            result *= number;
            power--;
        }
        return result;
    }

    // Реализовать рекурсивный O(2^N) алгоритм поиска чисел Фибоначчи.
    private static long fiboRecursive(int n) {
        if (n <= 1) return n;
        var f1 = fiboRecursive(n - 1);
//        System.out.println("f1:" + f1);
        var f2 = fiboRecursive(n - 2);
//        System.out.println("f2:" + f2);
        var result = f1 + f2;
//        System.out.println("result:" + result);
        return result;
    }

    // Реализовать итеративный O(N) алгоритм поиска чисел Фибоначчи
    private static long fiboIterative(int n) {
        if (n <= 1) return n;

        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // Реализовать алгоритм поиска количества простых чисел через перебор делителей, O(N^2).
    private static long primes(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long prime = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) prime++;
        }
        return prime;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
