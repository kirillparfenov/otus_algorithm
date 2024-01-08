package dev.parfenov.lesson_30_dynamic_programming;

//Нужно найти путь из корня до любого листа, сумма чисел на котором будет максимальным.
//Например в данном случае возможно это 1-3-5-8.
//1
//2 3
//4 5 6
//9 8 0 3
public class Елочка {
    public static void main(String[] args) {
        int[][] elo4ka = {
                {1},
                {2, 3},
                {4, 5, 6},
                {9, 8, 0, 3}
        };

        //используется принцип bottom-up
        for (int levelIndex = elo4ka.length - 2; levelIndex >= 0; levelIndex--) //сравниваем текущую строчку с предыдущей
            for (int elementIndex = 0; elementIndex < elo4ka[levelIndex].length; elementIndex++)
                elo4ka[levelIndex][elementIndex] += Math.max(elo4ka[levelIndex + 1][elementIndex], elo4ka[levelIndex + 1][elementIndex + 1]);

        System.out.println("Максимальное значение: " + elo4ka[0][0]);
    }
}
