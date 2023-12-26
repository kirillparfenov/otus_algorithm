package dev.parfenov.lesson_2_счастливые_билеты;

/**
 * Рекурсивный поиск счастливых билетов
 */
public class Tasks_0_5 {
    private static int count = 0;

    public static void main(String[] args) {
        for (int n = 1; n <= 5; n++) {
            count = 0;
            luckyTickets(n, 0,0);
            System.out.println(count);
        }
    }

    /*
    * Ответы:
    * 0 - 10
    * 1 - 670
    * 2 - 55252
    * 3 - 4816030
    * 4 - 432457640
    * */
    public static void luckyTickets(int n, int sumA, int sumB) {
        if (n == 0) {
            if (sumA == sumB)
                count++;
            return;
        }
        for (int a = 0; a <10; a++)
            for (int b = 0; b < 10; b++)
                luckyTickets(n - 1, sumA + a, sumB + b);
    }


}
