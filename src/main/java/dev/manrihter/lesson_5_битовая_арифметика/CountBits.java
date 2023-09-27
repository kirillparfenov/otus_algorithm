package dev.manrihter.lesson_5_битовая_арифметика;

public class CountBits {
    public static void main(String[] args) {

        long mask = 275956895760L;
        long start = System.nanoTime();
        long bits = countBits1(mask);
        System.out.printf("%s bits : %sns \n", bits, (System.nanoTime() - start));

        start = System.nanoTime();
        bits = countBits2(mask);
        System.out.printf("%s bits : %sns \n", bits, (System.nanoTime() - start));

        initBits();
        start = System.nanoTime();
        bits = countBits3(mask);
        System.out.printf("%s bits : %sns \n", bits, (System.nanoTime() - start));

    }

    static long countBits1(long mask) {
        long result = 0;
        while (mask > 0) {
            result += mask & 1;
            mask >>= 1;
        }
        return result;
    }

    static long countBits2(long mask) {
        long result = 0;
        while (mask > 0) {
            result++;
            mask &= mask - 1;
        }
        return result;
    }

    static long countBits3(long mask) {
        long result = 0;

        while (mask > 0) {
            result += bits[(int) (mask & 255)];
            mask >>= 8;
        }
        return result;
    }

    static byte[] bits;

    static void initBits() {
        bits = new byte[256];
        for (int i = 0; i < 256; i++) {
            bits[i] = (byte) countBits2(i);
        }
    }
}
