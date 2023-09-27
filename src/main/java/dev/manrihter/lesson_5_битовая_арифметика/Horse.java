package dev.manrihter.lesson_5_битовая_арифметика;

public class Horse {
    public static void main(String[] args) {
        long position = (1L << 3);
        long posL = 0x7f7f7f7f7f7f7f7fL & position;
        long posR = 0xfefefefefefefefeL & position;

        long result = (posL << 17) | (posR << 15) | (posL << 10)  | (posR << 6)
                |(posL >> 6) |(posR >> 10) | (posL >> 15) | (posR >> 17);
    }
}
