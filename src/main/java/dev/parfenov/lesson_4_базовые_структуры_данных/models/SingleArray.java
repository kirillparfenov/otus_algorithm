package dev.parfenov.lesson_4_базовые_структуры_данных.models;

public class SingleArray<K, V> implements BaseArray<K, V> {

    private K[] keys;
    private V[] values;

    public SingleArray() {
        this.keys = (K[]) new Object[0];
        this.values = (V[]) new Object[0];
    }


    @Override
    public int size() {
        return keys.length;
    }

    @Override
    public V add(K key, V value) {
        resize();
        keys[size() - 1]    = key;
        values[size() - 1]  = value;
        return value;
    }

    private void resize() {
        var tempKeys = (K[]) new Object[size() + 1];
        var tempValues = (V[]) new Object[size() + 1];
        for (int i = 0; i < size(); i++) {
            tempKeys[i]     = keys[i];
            tempValues[i]   = values[i];
        }
        keys = tempKeys;
        values = tempValues;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size(); i++)
            if (keys[i].equals(key))
                return values[i];

        return null;
    }

    @Override
    public V deleteByIndex(int index) {
        if (index >= size())
            return null;

        var value = values[index];
        removeByIndex(index);
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
