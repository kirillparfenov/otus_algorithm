package dev.parfenov.lesson_5_битовая_арифметика;

public class King {
    public static void main(String[] args) {
        long K = 1L << 39;
        long Ka = 0xfefefefefefefefeL & K;
        long Kh = 0x7f7f7f7f7f7f7f7fL & K;

        long result = (Ka << 7) | (K << 8) | (Kh << 9)
                | (Ka >> 1)           | (Kh << 1)
                | (Ka >> 9)| (K >> 8) | (Kh >> 7);
    }
}
