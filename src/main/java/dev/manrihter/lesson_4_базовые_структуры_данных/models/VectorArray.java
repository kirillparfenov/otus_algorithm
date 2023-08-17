package dev.manrihter.lesson_4_базовые_структуры_данных.models;

public class VectorArray<K, V> implements BaseArray<K, V> {

    private int vector;
    private K[] keys;
    private V[] values;
    int currentIndex;

    public VectorArray(int vector) {
        this.vector = vector;
        this.keys   = (K[]) new Object[vector];
        this.values = (V[]) new Object[vector];
        currentIndex = 0;
    }

    public VectorArray() {
        this(10);
    }

    @Override
    public int size() {
        return keys.length;
    }

    @Override
    public V add(K key, V value) {
        resize();

        keys[currentIndex]   = key;
        values[currentIndex] = value;
        currentIndex++;

        return value;
    }

    private void resize() {
        if (size() - 1 != currentIndex) return;

        var tempKeys = (K[]) new Object[size() + vector];
        var tempValues = (V[]) new Object[size() + vector];

        for (int i = 0; i < size(); i++) {
            tempKeys[i]     = keys[i];
            tempValues[i]   = values[i];
        }

        keys    = tempKeys;
        values  = tempValues;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size(); i++)
            if (key.equals(keys[i]))
                return values[i];

        return null;
    }

    @Override
    public V deleteByIndex(int index) {
        if (index >= size()) return null;

        var value = values[index];
        removeByIndex(index);
        currentIndex--;
        return value;
    }

    private void removeByIndex(int index) {
        var tempKeys = (K[]) new Object[size() - 1];
        var tempValues = (V[]) new Object[size() - 1];

        for (int i = 0; i < index; i++) {
            tempKeys[i]     = keys[i];
            tempValues[i]   = values[i];
        }

        int startPosition = index + 1;
        for (int i = startPosition; i < size(); i++) {
            tempKeys[i - 1]     = keys[i];
            tempValues[i - 1]   = values[i];
        }

        keys    = tempKeys;
        values  = tempValues;
    }
}
