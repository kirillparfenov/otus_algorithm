package dev.parfenov.lesson_21_графы_Дейкстра;

import java.util.Arrays;

/**
 *
 * */
public class Дейкстра {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        //Граф в виде матрицы смежности.
        // На пересечении вершин указан вес между вершинами.
        //0 - значит вершины не соединены
        int[][] graph = {
               //0  1  2  3  4  5
                {0, 2, 4, 0, 0, 0}, //0
                {2, 0, 1, 7, 0, 0}, //1
                {4, 1, 0, 3, 0, 0}, //2
                {0, 7, 3, 0, 1, 5}, //3
                {0, 0, 0, 1, 0, 3}, //4
                {0, 0, 0, 5, 3, 0}  //5
                //строка - это конкретная вершина
                //столбец - вес, между конкретной вершиной и другой вершиной
        };

        //вершина, с которой начинается поиск
        int startVertex = 0;
        dijkstra(graph, startVertex);
    }

    public static void dijkstra(int[][] graph, int startVertex) {
        //количество вершин == длине массива
        int vertices = graph.length;
        //distance - вес до каждой вершины из startVertex
        int[] distance = new int[vertices];
        //уже посещенные вершины
        boolean[] visited = new boolean[vertices];

        Arrays.fill(distance, INF);
        //вес startVertex до самой себя == 0
        distance[startVertex] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int minVertex = findMinDistanceVertex(distance, visited);
            visited[minVertex] = true;

            for (int j = 0; j < vertices; j++) {
                boolean notVisited = !visited[j];
                boolean hasPath = graph[minVertex][j] != 0;
                int newDistance = distance[minVertex] + graph[minVertex][j];
                int oldDistance = distance[j];

                if (notVisited && hasPath && newDistance < oldDistance) {
                    distance[j] = newDistance;
                }
            }
        }

        printSolution(distance);
    }

    /**
     * поиск следующей вершины с минимальным весом
     * */
    public static int findMinDistanceVertex(int[] distance, boolean[] visited) {
        int minDistance = INF;
        int minVertex = -1;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minVertex = i;
            }
        }

        return minVertex;
    }

    public static void printSolution(int[] distance) {
        System.out.println("Кратчайшие расстояния от начальной вершины:");

        for (int i = 0; i < distance.length; i++) {
            System.out.println("Вершина " + i + ": " + distance[i]);
        }
    }
}
