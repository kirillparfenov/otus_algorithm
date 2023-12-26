package dev.parfenov.lesson_2_счастливые_билеты;

import java.util.Arrays;

public class Tasks_6_7 {
    public static void main(String[] args) {
        System.out.println(luckyTickets(5));

    }

    /**
     * @param halfOfTicket - половина длины билета
     */
    public static int luckyTickets(int halfOfTicket) {
        long[] results = new long[halfOfTicket * 9 + 1];
        Arrays.fill(results, 1);

        long[] previous = new long[halfOfTicket * 9 + 1];
        for (int i = 2; i <= halfOfTicket; i++) {
            int maxS = i * 9;
            int maxT = maxS - 9;

            for (int k = 0; k <= maxT; k++)
                previous[k] = results[k];

            Arrays.fill(results, 0);

            for (int k = 0; k <= 9; k++)
                for (int z = 0; z <= maxT; z++)
                    results[k + z] += previous[z];
        }

        int result = 0;
        for (long element : results)
            result += element * element;

        return result;
    }
}
