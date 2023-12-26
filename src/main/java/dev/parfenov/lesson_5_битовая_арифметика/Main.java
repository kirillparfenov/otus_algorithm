package dev.parfenov.lesson_5_битовая_арифметика;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        //System.out.println(10 << 3); // 10 * 2^3

        long pos = 1 << 63;
        BigInteger position = BigInteger.valueOf(pos).compareTo(BigInteger.ZERO) < 0 ?
                BigInteger.valueOf(pos).add(BigInteger.ONE.shiftLeft(64)) :
                BigInteger.valueOf(pos);

        var mask = kingMask(position);
        System.out.println(mask);
//        System.out.println(numberOfMoves(mask));
    }

    private static BigInteger kingMask(BigInteger position) {
        var positionL = position.and(BigInteger.valueOf(0xfefefefefefefefeL));
        var positionR = position.and(BigInteger.valueOf(0x7f7f7f7f7f7f7f7fL));
        return positionL.shiftLeft(7)                   .add(position.shiftLeft(8))         .add(positionR.shiftLeft(9))
                        .add(positionL.shiftRight(1))                                          .add(positionR.shiftLeft(1))
                        .add(positionL.shiftRight(9))   .add(position.shiftRight(8))        .add(positionR.shiftRight(7));
    }

    private static int numberOfMoves(long mask) {
        int count = 0;
        while (mask > 0) {
            count += (int) (mask & 1);
            mask >>= 1;
        }
        return count;
    }
}
