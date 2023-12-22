package dev.manrihter.lesson_25_Aho_Corasik;

import java.util.*;

public class AhoCorasik {

    public static void main(String[] args) {
        var patterns = List.of(
                "A",
                "AS",
                "ARAB",
                "BAR",
                "BASS",
                "C",
                "CAR",
                "RA",
                "RAB"
        );
        var aho = new AhoCorasik(patterns);
//        var text = "TCATRABBAR";
        var text = "CARRABBASS";
        var matches = aho.findMatches(text);
    }

    public final TrieNode root;
    public final List<String> patterns;

    /**
     * @param patterns паттерны, по которым строим дерево и в будущем будем проводить поиск
     */
    public AhoCorasik(List<String> patterns) {
        this.root = new TrieNode();
        this.patterns = patterns;
        buildTrieNodes(); //строим дерево по полученным паттернам
        buildSuffixLinks(); //строим префиксные ссылки между вершинами
    }

    private void buildTrieNodes() {
        for (String pattern : patterns) {
            TrieNode node = root;
            var prefix = "";
            for (Character c : pattern.toCharArray()) { //строим дерево для каждого символа из паттерна, начиная с корня
                node.children.putIfAbsent(c, new TrieNode(prefix += c)); //добавляем ребенка, если его еще нет
                node = node.children.get(c); //и идем по дереву вглубь
            }
            node.pattern = pattern; //на последнем символе паттерна записываем само значение паттерна
        }
    }

    private void buildSuffixLinks() {
        //строим суффиксный путь алгоритмом BFS
        Queue<TrieNode> queue = new ArrayDeque<>();

        for (var node : root.children.values()) { //заполняем суффиксные ссылки для первого уровня
            queue.add(node);
            node.suffixLink = root;
        }

        //теперь для остальных
        while (!queue.isEmpty()) {
            var node = queue.poll(); //взяли очередной элемент из очереди
            for (var c : node.children.keySet()) {
                var parentSuffixLink = node.suffixLink;//берем суффиксную ссылку этого элемента
                var child = node.children.get(c);
                queue.add(child);
                while (parentSuffixLink != null && !parentSuffixLink.children.containsKey(c)) //пока суффиксная ссылка != null и в суффиксной ссылке НЕТ искомого элемента -
                    parentSuffixLink = parentSuffixLink.suffixLink; //идем вверх, чтобы найти где встречается искомый элемент

                child.suffixLink = parentSuffixLink == null ? root : parentSuffixLink.children.get(c); //если suffixLink == null -> значит уперлись в root, иначе была найдена вершина, у которой в children есть искомый элемент
                child.finalLink = child.suffixLink.pattern != null //если суффиксная ссылка содержит паттерн
                        ? child.suffixLink //тогда это финальная ссылка
                        : child.suffixLink.finalLink; //иначе возьмем финальную ссылку у суффиксной ссылки
            }
        }
    }

    /**
     * Найдем все паттерны по переданному тексту
     */
    public List<String> findMatches(String text) {
        var matches = new ArrayList<String>();
        var node = root;

        for (var c : text.toCharArray()) { //идем по каждой букве текста
            while (node != null && !node.children.containsKey(c)) //если не нашли элемент в детях вершины
                node = node.suffixLink; //то идем вверх по суффиксной ссылке

            if (node == null) { //уперлись в null?
                node = root; //начинаем с корня
                continue;
            }

            node = node.children.get(c); //иначе у нас есть элемент
            if (node.pattern != null)
                matches.add(node.pattern);

            var finalLink = node.finalLink;
            while (finalLink != null) { //пройдемся по всем финальным ссылкам, чтобы зацепить как можно больше паттернов
                matches.add(finalLink.pattern);
                finalLink = finalLink.finalLink;
            }
        }
        return matches;
    }

    //вершина в дереве
    public static class TrieNode {
        public TrieNode suffixLink; //ссылка на другую вершину
        public TrieNode finalLink; //ссылка на вершину с наличием заполненного pattern
        public Map<Character, TrieNode> children = new HashMap<>(); //все возможные дети для текущей вершины
        public String pattern; //сам паттерн (слово)
        public String prefix;

        public TrieNode(String prefix) {
            this.prefix = prefix;
        }

        public TrieNode() {
            this("");
        }
    }
}
