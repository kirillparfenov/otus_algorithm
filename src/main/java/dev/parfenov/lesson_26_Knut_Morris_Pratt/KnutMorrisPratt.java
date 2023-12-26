package dev.parfenov.lesson_26_Knut_Morris_Pratt;

public class KnutMorrisPratt {
    private static final String PATTERN = "AABAABAAABA";

    public static void main(String[] args) {
        var piSlow = buildPiSlow();
        var piFast = buildPiFast();
    }

    //строим массив состояний.
    //максимальная длина суффикса == максимальной длине префикса
    private static int[] buildPiSlow() {
        var pi = new int[PATTERN.length() + 1];
        for (int state = 0; state <= PATTERN.length(); state++) {
            var line = left(PATTERN, state); //откусываем от паттерна строку слева
            for (int k = 0; k < state; k++)  //и сравниваем префикс с суффикс
                if (left(line, k).equals(right(line, k)))
                    pi[state] = k; //в текущее состояние шаблона будет записан переход автомата в другое состояние "k"
        }
        return pi;
    }

    private static String left(String text, int end) {
        return text.substring(0, end);
    }

    private static String right(String text, int end) {
        return text.substring(text.length() - end);
    }

    private static int[] buildPiFast() {
        var pi = new int[PATTERN.length() + 1];
        int q = 1; //q - состояние автомата (по сути длина текущей строчки)
        pi[q] = 0; //pi[q] - максимальная длина префика и суффикса, равные друг другу
        for (; q < PATTERN.length(); q++) {
            int len = pi[q];

            while (len > 0 && PATTERN.charAt(len) != PATTERN.charAt(q))
                len = pi[len]; //по-сути это движение по суффиксным ссылкам наверх

            if (PATTERN.charAt(len) == PATTERN.charAt(q))
                len++;

            pi[q + 1] = len; //прибавляем к следующему состоянию максимальную длину равных префикса/суффикса
        }
        return pi;
    }
}
