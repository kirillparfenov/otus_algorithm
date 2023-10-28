package dev.manrihter.lesson_19_графы_Демукрон;

import java.util.Arrays;

public class Демукрон {
    public static void main(String[] args) {

        int[][] array = new int[][]{
                {
                        //  A  B  C  D  E  F  G  H  X  Y
                        0, 1, 0, 0, 0, 0, 0, 0, 0, 0 //A
                },
                {
                        0, 0, 0, 0, 1, 0, 0, 0, 0, 0 //B
                },
                {
                        0, 0, 0, 1, 0, 0, 0, 0, 0, 0 //C
                },
                {
                        1, 1, 0, 0, 1, 1, 0, 0, 0, 0 //D
                },
                {
                        0, 0, 0, 0, 0, 0, 1, 0, 0, 0 //E
                },
                {
                        0, 0, 0, 0, 1, 0, 0, 1, 0, 0 //F
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 1, 0, 0 //G
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //H
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 1 //X
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //Y
                }
        };

        int rows = array.length, columns = array[0].length;

        //первоначальная полустепень захода (сколько дуг заходит в вершину)
        int[] sum = new int[columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum[j] += array[i][j];
            }
        }

        //ищем какие вершины можем обработать (те, в которых заходят 0 дуг)
        //сложность O(n^2)
        for (int k = 0; k < sum.length; k++) {
            for (int i = 0; i < sum.length; i++) {
                if (sum[i] != 0) continue;
                //когда нашли -> берем строку (row) и из sum вычитаем эту строку
                int[] row = array[i];
                //для вершины с 0 степенью - ставим -1, чтобы не обрабатывать их
                sum[i] = -1;
                for (int j = 0; j < sum.length; j++) {
                    sum[j] -= row[j];
                }
            }
        }

        //все вершины должны быть обработаны (у всех -1)
        System.out.println(Arrays.toString(sum));
    }
}
