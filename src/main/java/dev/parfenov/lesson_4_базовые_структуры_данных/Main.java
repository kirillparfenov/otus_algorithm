package dev.parfenov.lesson_4_базовые_структуры_данных;

import dev.parfenov.lesson_4_базовые_структуры_данных.models.BaseArray;
import dev.parfenov.lesson_4_базовые_структуры_данных.models.FactorArray;
import dev.parfenov.lesson_4_базовые_структуры_данных.models.SingleArray;
import dev.parfenov.lesson_4_базовые_структуры_данных.models.VectorArray;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.print("Single array 10_000:\n");
        BaseArray<String, String> singleArray = new SingleArray<>();
        addAndDelete(singleArray, 10_000);

        System.out.print("Vector array 10_000:\n");
        BaseArray<String, String> vectorArray = new VectorArray<>();
        addAndDelete(vectorArray, 10_000);

        System.out.print("Factor array 10_000:\n");
        BaseArray<String, String> factorArray = new FactorArray<>();
        addAndDelete(factorArray, 10_000);

        System.out.print("ArrayList 10_000:\n");
        List<String> list = new ArrayList<>();
        addAndDeleteArrayList(list, 10_000);

        System.out.println();
    }

    private static void addAndDelete(BaseArray<String, String> array, int total) {
        //add
        var startMillis = System.currentTimeMillis();
        for (int i = 0; i < total; i++) array.add("" + i, "" + i);
        System.out.println(
                String.format("Total time for add: %s ms", System.currentTimeMillis() - startMillis)
        );

        //delete -  первый элемент в массиве
        startMillis = System.currentTimeMillis();
        for (int i = 0; i < total; i++) array.deleteByIndex(0);
        System.out.println(
                String.format("Total time for delete: %s ms\n", System.currentTimeMillis() - startMillis)
        );
    }

    private static void addAndDeleteArrayList(List<String> list, int total) {
        //add
        var startMillis = System.currentTimeMillis();
        for (int i = 0; i < total; i++) list.add("" + i);
        System.out.println(
                String.format("Total time for add: %s ms", System.currentTimeMillis() - startMillis)
        );

        //delete -  первый элемент в массиве
        startMillis = System.currentTimeMillis();
        for (int i = 0; i < total; i++) list.remove("" + i);
        System.out.println(
                String.format("Total time for delete: %s ms\n", System.currentTimeMillis() - startMillis)
        );
    }
}
