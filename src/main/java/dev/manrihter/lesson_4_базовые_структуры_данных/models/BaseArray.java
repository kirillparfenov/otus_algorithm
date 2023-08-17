package dev.manrihter.lesson_4_базовые_структуры_данных.models;

public interface BaseArray<K, V> {
    int size();

    V add(K key, V value);

    V get(K key);

    V deleteByIndex(int index);
}
