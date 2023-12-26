package dev.parfenov.lesson_20_графы_Минимальный_скелет;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class Graph {
        int V; // Количество вершин
        List<Edge> edges;

        public Graph(int V) {
            this.V = V;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int src, int dest, int weight) {
            Edge edge = new Edge(src, dest, weight);
            edges.add(edge);
        }

        public List<Edge> kruskalMST() {
            List<Edge> result = new ArrayList<>();

            // Сортируем рёбра по весу по возрастанию
            Collections.sort(edges);

            // Создаем подмножества для каждой вершины
            Subset[] subsets = new Subset[V];
            for (int v = 0; v < V; v++) {
                subsets[v] = new Subset();
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            int i = 0;
            int e = 0;

            while (e < V - 1) {
                Edge nextEdge = edges.get(i++);
                int x = find(subsets, nextEdge.src);
                int y = find(subsets, nextEdge.dest);

                if (x != y) {
                    result.add(nextEdge);
                    union(subsets, x, y);
                    e++;
                }
            }

            return result;
        }

        private int find(Subset[] subsets, int i) {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);
            return subsets[i].parent;
        }

        //Объединение двух подмножеств
        private void union(Subset[] subsets, int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;
            else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        /**
         * Subset - это структура данных, которая используется для хранения информации о подмножествах вершин.
         * Определяет какие вершины принадлежат одному и тому же подмножеству и какие вершины уже объединены.
         * */
        static class Subset {
            int parent; //указывает на "родительскую" вершину в подмножестве.
            // В начале каждая вершина рассматривается как отдельное подмножество, и каждая вершина является собственным "родительским" элементом

            int rank; //представляет оценку глубины поддерева, связанного с данной вершиной.
            // При объединении двух подмножеств (деревьев) сравнивается их ранг, и подмножество с меньшим рангом объединяется с подмножеством с большим рангом.
            // Если ранги равны, одно из подмножеств становится "родительским" для другого, и его ранг увеличивается.
        }
    }

    public static void main(String[] args) {
        int V = 5; // Количество вершин
        Graph graph = new Graph(V);

        // Добавляем рёбра в граф
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 9);
        graph.addEdge(1, 3, 15);
        graph.addEdge(1, 4, 2);
        graph.addEdge(4, 2, 1);
        graph.addEdge(2, 3, 4);

        List<Edge> result = graph.kruskalMST();
        System.out.println("Минимальное остовное дерево по алгоритму Крускала:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " с весом " + edge.weight);
        }
    }
}
