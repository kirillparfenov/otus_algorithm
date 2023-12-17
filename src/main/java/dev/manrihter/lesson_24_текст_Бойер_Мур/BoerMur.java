package dev.manrihter.lesson_24_текст_Бойер_Мур;

import java.util.Arrays;

public class BoerMur {
    public static void main(String[] args) {
//        String text = "STRIGSTRING", pattern = "RING";
        String text = "ABADADDABCTABBCDCBAUTAABADACDDAAA", pattern = "ABADACD";
        System.out.println(brutForce(text, pattern));
        System.out.println(shiftSearch(text, pattern));
    }

    //Написать алгоритм поиска подстроки полным перебором
    private static int brutForce(String text, String pattern) {

        for (int t = 0; t < text.length() - pattern.length() + 1; t++)
            for (int p = 0; p < pattern.length() && pattern.charAt(p) == text.charAt(t + p); p++)
                if (p == pattern.length() - 1)
                    return t;

        return -1;
    }

    //Оптимизировать алгоритм, используя сдвиги по суффиксу текста
    private static int shiftSearch(String text, String pattern) {
        int[] shift = createShift(pattern);
        int t = 0;
        while (t <= text.length() - pattern.length()) {

            int m = pattern.length() - 1;
            while (m >= 0 && pattern.charAt(m) == text.charAt(m + t))
                m--;

            if (m < 0)
                return t;

            //берем сдвиг, который идет до последнего совпадения в text:
            // text:         ABCTA
            // text shift:   14351
            // pattern:      BCDAA
            // patt shift:   4321-
            t += shift[text.charAt(m + t)];
        }
        return -1;
    }

    private static int[] createShift(String pattern) {
        //создаем массив на всю ascii
        int[] shift = new int[256];
        //если символ не совпал - делаем сдвиг на всю длину паттерна
        Arrays.fill(shift, pattern.length());

        //заполняем сдвиги
        int index = pattern.length() - 1;
        for (int i = 0; i < pattern.length() - 1; i++) //последний символ в паттерне не берем
            shift[pattern.charAt(i)] = index--;

        return shift;
    }
}
