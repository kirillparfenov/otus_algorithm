package dev.parfenov.lesson_35_bloom_filter;

import java.util.BitSet;

/**
 * Bloom Filter может давать ложноположительные результаты
 * (предполагать, что элемент есть в множестве, когда его там нет),
 * но не дает ложноотрицательных результатов.
 *
 * В реальных сценариях использования Bloom Filter обычно применяется для оптимизации поиска в больших наборах данных,
 * когда точное решение было бы слишком дорого.
 * */
public class BloomFilter {
    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(100, 3);

        bloomFilter.add("apple");
        bloomFilter.add("orange");

        System.out.println(bloomFilter.contains("apple"));   // true
        System.out.println(bloomFilter.contains("banana"));  // false
        System.out.println(bloomFilter.contains("orange"));  // true
    }

    private final BitSet bitSet;
    private final int size;
    private final int[] hashFunctions;

    public BloomFilter(int size, int numHashFunctions) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = generateHashFunctions(numHashFunctions);
    }

    public void add(String value) {
        for (int hashFunction : hashFunctions) {
            int index = hash(value, hashFunction);
            bitSet.set(index);
        }
    }

    public boolean contains(String value) {
        for (int hashFunction : hashFunctions) {
            int index = hash(value, hashFunction);
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    private int hash(String value, int hashFunction) {
        return Math.abs((value.hashCode() ^ hashFunction) % size);
    }

    private int[] generateHashFunctions(int numHashFunctions) {
        int[] hashFunctions = new int[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            hashFunctions[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return hashFunctions;
    }
}
