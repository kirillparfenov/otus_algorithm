package dev.parfenov.lesson_26_Knut_Morris_Pratt;

public class TextPatternSearch_Конечный_автомат {
    private static final String ALPHABET_PATTERN = "ABC";
    private static final String PATTERN = "AABAABAAABA";

    public static void main(String[] args) {
        var text = "AABAABAABAAABA";
        var automate = buildAutomate();
        var patternPosition = search(text, automate);
        System.out.println("Паттерн находится в тексте на позиции " + patternPosition);
    }

    //Составить конечный автомат и прохождение по нему для поиска шаблона в строке
    private static int[][] buildAutomate() {
        int[][] automate = new int[PATTERN.length()][ALPHABET_PATTERN.length()]; //количество строк (количество состояний автомата) == длине паттерна,
                                                                                // столбцы - длина алфавита
        for (int state = 0; state < PATTERN.length(); state++) { //перебираем все символы паттерна для составления конечного автомата
            for (var c : ALPHABET_PATTERN.toCharArray()) {
                var line = left(PATTERN, state) + c; //для каждого символа из алфавита формируем строку и смотрим - соответствует ли она нашему паттерну
                int k = state + 1;                   //если да, то для выбранного символа "c" продвигаемся по автомату дальше

                while (!left(PATTERN, k).equals(right(line, k)))//если левые k-символов паттерна != правым k-символам line-строки
                    k--;                                        //то обрезаем паттерн и line-строку, чтобы найти состояние (k) автомата, в котором они совпадают

                automate[state][c - 'A'] = k; //в конечном итоге для текущего состояния автомата "state" получили состояние автомата "k"
                                            // в которое нужно перейти при символе "c"
            }
        }

        return automate;
    }

    private static String left(String text, int end) {
        return text.substring(0, end);
    }

    private static String right(String text, int end) {
        return text.substring(text.length() - end);
    }

    private static int search(String text, int[][] automate) {
        var state = 0;
        for (int position = 0; position < text.length(); position++) {
            state = automate[state][text.charAt(position) - 'A'];
            if (state == PATTERN.length())
                return position + 1 - PATTERN.length();
        }
        return -1;
    }
}
