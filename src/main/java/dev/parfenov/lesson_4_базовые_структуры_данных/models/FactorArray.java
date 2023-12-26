package dev.parfenov.lesson_4_базовые_структуры_данных.models;

public class FactorArray<K, V> implements BaseArray<K, V> {

    private final int factor;
    private K[] keys    = (K[]) new Object[0];
    private V[] values  = (V[]) new Object[0];
    private int currentIndex = 0;

    public FactorArray() {
        this(10);
    }

    public FactorArray(int factor) {
        this.factor = factor;
    }

    @Override
    public int size() {
        return keys.length;
    }

    @Override
    public V add(K key, V value) {
        resize();
        keys[currentIndex] = key;
        values[currentIndex] = value;
        currentIndex++;
        return value;
    }

    private void resize() {
        if (size() != currentIndex) return;

        var tempKeys    = (K[]) new Object[size() * factor + 1];
        var tempValues  = (V[]) new Object[size() * factor + 1];

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
        //отрежем справа пустые ячейки
        int rightIndex = size() - 1;
        for (int i = rightIndex; i >= 0; i--) {
            if (keys[i] == null)
                rightIndex = i;
            else
                break;
        }

        var tempKeys    = (K[]) new Object[rightIndex];
        var tempValues  = (V[]) new Object[rightIndex];

        for (int i = 0; i < index && i < rightIndex; i++) {
            tempKeys[i]     = keys[i];
            tempValues[i]   = values[i];
        }

        int startPosition = index + 1;
        for (int i = startPosition; i <rightIndex + 1; i++) {
            tempKeys[i - 1]     = keys[i];
            tempValues[i - 1]   = values[i];
        }

        keys    = tempKeys;
        values  = tempValues;
    }
}
