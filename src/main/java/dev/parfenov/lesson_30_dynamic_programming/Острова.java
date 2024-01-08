package dev.parfenov.lesson_30_dynamic_programming;

/**
 * острова образованы единицами (1) по горизонтали или вертикали - по диагонали не считается
 * <p>
 * найти количество островов в матрице
 */
public class Острова {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 0},
                {1, 0, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 1, 0}
        }; // => 3 острова
        System.out.println(islands(matrix));
    }

    /**
     * решается dfs/bfs от любой единичной ячейки
     * <p>
     * посещенные ячейки перекрашиваем в 0
     */
    private static int islands(int[][] matrix) {
        var length = matrix.length;
        var height = matrix[0].length;

        var islands = 0;

        for (int row = 0; row < length; row++)
            for (int col = 0; col < height; col++)
                if (matrix[row][col] == 1) {
                    dfs(matrix, row, col);
                    islands++;
                }

        return islands;
    }

    private static void dfs(int[][] matrix, int row, int col) {
        if (matrix[row][col] == 0) return;

        matrix[row][col] = 0;

        var length = matrix.length - 1;
        var height = matrix[0].length - 1;

        if (row > 0)
            dfs(matrix, row - 1, col); //проверим ячейку слева

        if (row < length)
            dfs(matrix, row + 1, col); //проверим ячейку слева

        if (col > 0)
            dfs(matrix, row, col - 1); //проверим ячейку сверху

        if (col < height)
            dfs(matrix, row, col + 1); //проверим ячейку снизу
    }
}
