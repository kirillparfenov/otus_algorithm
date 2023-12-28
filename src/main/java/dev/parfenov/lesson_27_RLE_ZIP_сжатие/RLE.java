package dev.parfenov.lesson_27_RLE_ZIP_сжатие;

public class RLE {
    public static void main(String[] args) {

        var str = "AAAABBBCCDEFGGGTYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY";

        //стандартный RLE
        System.out.println("Base string:" + "\n" + str);
        var simpleRLE = simpleRLE(str);
        System.out.println("\n" + "RLE zip:" + "\n" + simpleRLE);
        var unzipSimpleRLE = unzipSimpleRLE(simpleRLE);
        System.out.println("\n" + "unzip RLE:" + "\n" + unzipSimpleRLE);

        System.out.println("----------------------");

        //улучшенный RLE
        var s = "ABCCDEEFFGHIJJJJJJYU";
        var betterRLE = betterRLE(s);
        var unzipBetterRLE = unzipBetterRLE(s);
        System.out.println("Better RLE");
        System.out.println("\nBase string:\n" + s);
        System.out.println("\nRLE zip:\n" + betterRLE);
        System.out.println("\nRLE unzip\n" + unzipBetterRLE);
    }

    private static String simpleRLE(String str) {
        var rle = new StringBuilder();
        var counter = 0;
        var previous = str.charAt(0);

        for (var current : str.toCharArray()) {
            if (current != previous) {
                rle.append(counter).append(previous);
                previous = current;
                counter = 0;
            }
            counter++;
        }

        //последний подсчет тоже нужно указать, чтобы не потерялся
        return rle.append(counter).append(previous).toString();
    }

    private static String unzipSimpleRLE(String zipSimpleRLE) {
        var unzipped = new StringBuilder();
        var startDigit = 0;

        for (int i = 0; i < zipSimpleRLE.length(); i++) {
            var endDigit = startDigit;

            //сканируем до тех пор, пока встречаются цифры
            while (Character.isDigit(zipSimpleRLE.charAt(i)))
                endDigit = ++i;

            //печатаем последовательность символов
            int repeats = parseNumber(zipSimpleRLE, startDigit, endDigit);
            while (repeats-- > 0)
                unzipped.append(zipSimpleRLE.charAt(i));

            //следующая цифра начнется через символ
            startDigit = endDigit + 1;
        }

        return unzipped.toString();
    }

    private static Integer parseNumber(String str, int from, int to) {
        return Integer.parseInt(str.substring(from, to));
    }

    /**
     * улучшенный RLE - цифры будут стоять только перед теми символами, которые повторяются более 1 раза<p>
     * в остальных случаях просто последовательность символов
     */
    public static String betterRLE(String str) {
        var rle = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            var current = str.charAt(i);
            var count = 1;

            while (i + 1 < str.length() && str.charAt(i + 1) == current) {
                i++;
                count++;
            }

            if (count == 1)
                rle.append(current);
            else
                rle.append(count).append(current);
        }

        return rle.toString();
    }

    /**
     * формат входной строки: AB2CD2E2FGHI3J
     */
    public static String unzipBetterRLE(String str) {
        var unzip = new StringBuilder();
        var number = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            var current = str.charAt(i);


            while (i < str.length() - 1 && Character.isDigit(current)){
                number.append(current);
                i++;
            }

            if (number.length() > 0) {
                var n = Integer.parseInt(number.toString());
                while (n-- > 0)
                    unzip.append(current);
                number.setLength(0);
            } else {
                unzip.append(current);
            }
        }
        return unzip.toString();
    }
}
