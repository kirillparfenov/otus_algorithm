package dev.parfenov.lesson_30_dynamic_programming;

/**
 * Найти дробь, которая покажет какую часть от общего количества гороха набрали суслик и хома
 */
public class РазДваГорох {
    public static void main(String[] args) {
        //допустим суслик набрал 2/100, а хома 3/100 - вместе они набрали 5/100 или 1/20
        var suslik = 2;
        var homa = 3;
        var base = 100;
        System.out.println(goroh(suslik, homa, base));
    }

    /**
     * задача решается поиском НОД (наибольший общий делитель) у двух дробей
     */
    private static String goroh(int suslik, int homa, int base) {
        //находим НОД
        var nod = NOD(suslik + homa, base);
        //затем делим на него числитель и знаменатель
        var chislitel = (suslik + homa) / nod;
        var znamenatel = base / nod;

        return chislitel + "/" + znamenatel;
    }

    private static int NOD(int a, int b) {
        if (a == b)
            return a;

        if (a == 0)
            return b;

        if (b == 0)
            return a;

        if (isEven(a) && isEven(b))
            return NOD(a >> 1, b >> 1) << 1;

        if (isEven(a) && isOdd(b))
            return NOD(a >> 1, b);

        if (isOdd(a) && isEven(b))
            return NOD(a, b >> 1);

        if (a > b)
            return NOD((a - b) >> 1, b);

        return NOD(a, (b - a) >> 1);
    }

    private static boolean isEven(int x) {
        return x % 2 == 0;
    }

    private static boolean isOdd(int x) {
        return x % 2 == 1;
    }
}
