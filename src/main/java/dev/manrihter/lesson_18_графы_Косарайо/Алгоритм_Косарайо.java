package dev.manrihter.lesson_18_графы_Косарайо;

import java.util.*;

/**
 * Принцип:
 * 1) Делаем инверсию ребер
 * 2) Делаем поиск в глубину и когда выходим из него - выписываем вершины в обратном порядке
 * 3) Инвертирует полученный список вершин
 * 4) Запускаем поиск в глубину на первоначальном графе, но в том порядке вершин, которые получили в пункте 3
 * 5) Как только достигаем тупика в пункте 4 - значит нашли компоненту связности
 */
public class Алгоритм_Косарайо {

    public static void main(String[] args) {
        //строим граф, который представлен на картинке "Компоненты связности.png", в виде списка ребер
        Edge[] graph = graph();

        //1) делаем инверсию ребер, чтобы получился результат, как на картинке "Инвертированные ребра.png"
        Edge[] invertedGraph = invertGraph(graph);

        //2)Делаем поиск в глубину и когда выходим из него - выписываем вершины в обратном порядке
        //3)после чего инвертируем все полученные вершины
        String[] invertedVertexes = invertVertexes(invertedGraph);

        //4) Запускаем поиск в глубину на первоначальном графе, но в том порядке вершин, которые получили в пункте 3
        //5) Как только достигаем тупика в пункте 4 - значит нашли компоненту связности
        dfsComponent(invertedVertexes, graph);
    }

    public static class Edge {
        public String from; //Vertex
        public String to; //Vertex

        public Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }

    //создаем граф
    public static Edge[] graph() {
        var a = "A";
        var b = "B";
        var c = "C";
        var d = "D";
        var e = "E";
        var f = "F";
        var g = "G";
        var h = "H";

        return new Edge[]{
                new Edge(a, b),
                new Edge(b, c),
                new Edge(b, f),
                new Edge(b, e),
                new Edge(c, d),
                new Edge(c, g),
                new Edge(d, c),
                new Edge(d, h),
                new Edge(e, a),
                new Edge(e, f),
                new Edge(f, g),
                new Edge(g, f),
                new Edge(h, d),
                new Edge(h, g)
        };
    }

    //инвертируем направление ребер - O(N)
    public static Edge[] invertGraph(final Edge[] graph) {
        int graphLength = graph.length;
        Edge[] invertedGraph = new Edge[graphLength];

        for (int i = 0; i < graphLength; i++) {
            Edge edge = graph[i];
            String invertedFrom = edge.to;
            String invertedTo = edge.from;
            invertedGraph[i] = new Edge(invertedFrom, invertedTo);
        }

        return invertedGraph;
    }

    static Set<String> visited = new HashSet<>();
    static Stack<String> stack = new Stack<>();
    static Deque<String> deque = new ArrayDeque<>();

    //поиск в глубину - O(n^2)(в полносвязном графе)
    private static void dfs(Edge[] graph, String vertex) {
        if (visited.contains(vertex))
            return;

        visited.add(vertex);
        stack.push(vertex);

        //дальше пробегаемся по массиву - ищем GRAPH.FROM == VERTEX и запускаем рекурсивно dfs для GRAPH.TO
        //чтобы понять - а можно ли провалиться куда-то дальше вглубь
        int length = graph().length;
        for (int i = 0; i < length; i++)
            if (graph[i].from.equals(vertex))
                dfs(graph, graph[i].to);

        //когда закончим - перекладываем все в deque в правильном порядке
        while (!stack.isEmpty())
            deque.addLast(stack.pop());
    }

    //инвертируем вершины - O(n + m) - без учета dfs
    private static String[] invertVertexes(Edge[] invertedGraph) {
        for (Edge edge : invertedGraph)
            dfs(invertedGraph, edge.from);

        //теперь нам нужно инвертировать полученную очередь
        String[] result = new String[deque.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = deque.pollLast();

        return result;
    }

    //ищем и печатаем компоненты связности - O(n) - без учета dfs
    private static void dfsComponent(String[] invertedVertexes, Edge[] graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("Компоненты связности:").append("\n");

        //обнуляем посещенные вершины
        visited = new HashSet<>();
        for (String vertex : invertedVertexes) {
            dfs(graph, vertex);
            while (!deque.isEmpty()) {
                sb.append(deque.poll());
                sb.append(deque.isEmpty() ? "\n" : "");
            }
        }
        //печатаем результат
        System.out.println(sb);
    }
}
