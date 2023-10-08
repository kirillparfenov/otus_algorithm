package dev.manrihter.lesson_14_разрешение_коллизий;

public class OpenAddressing {
    public static void main(String[] args) {
        var addressingHashTable = new HashTable<Integer, String>();

        addressingHashTable.put(22, "22");
        addressingHashTable.put(42, "42");
        addressingHashTable.put(52, "52");

        //сначала "ленивое удаление" - элемент будет помечен как удаленный
        addressingHashTable.remove(42);
        //затем произойдет вставка в этот удаленный элемент
        addressingHashTable.put(42, "42 after delete");

        addressingHashTable.put(37, "37");
        addressingHashTable.put(37, "37-new");

        addressingHashTable.put(17, "17");
        addressingHashTable.put(87, "87");
        addressingHashTable.put(97, "97");

        System.out.println(addressingHashTable.contains(52));
        System.out.println(addressingHashTable.contains(53));
        System.out.println(addressingHashTable.contains(37));
    }

    /**
     * Хэш-таблица с открытой адресацией не содержит односвязного списка.
     * <p>P.S. в данном примере опущены проверки на null
     * */
    @SuppressWarnings("all")
    public static class HashTable<K, V> {
        //capacity - начальная емкость хэш-таблицы
        private static final int DEFAULT_CAPACITY = 10;
        private static final float DEFAULT_LOAD_FACTOR = 0.5f;

        //threshold = capacity * loadFactor
        private int threshold;
        private int capacity;
        private float loadFactor;
        private int size;

        BucketElement<K, V> buckets[];

        public HashTable() {
            this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
        }

        private HashTable(int capacity, float loadFactor) {
            this.buckets = (BucketElement<K, V>[]) new BucketElement[capacity];
            this.capacity = capacity;
            this.loadFactor = loadFactor;
            this.threshold = (int) (capacity * loadFactor);
        }

        public boolean contains(K key) {
            int i = 0;
            int idx = hash(key, i);
            BucketElement<K, V> e = buckets[idx];
            while (e != null) {
                if (key.equals(e.key))
                    return true;
                idx = hash(key, ++i);
                e = buckets[idx];
            }
            return false;
        }

        public V remove(K key) {
            int i = 0;
            int idx = hash(key, i);
            BucketElement<K, V> e = buckets[idx];
            while (e != null) {
                if (key.equals(e.key)) {
                    V temp = e.value;
                    e.removed = true;
                    e.key = null;
                    e.value = null;
                    return temp;
                }
                idx = hash(key, ++i);
                e = buckets[idx];
            }
            return null;
        }

        public V put(K key, V value) {
            if (size > threshold)
                rehash();

            int i = 0;
            int idx = hash(key, i);
            BucketElement<K, V> e = buckets[idx];
            int removedIndex = -1;

            while (e != null) {
                if (key.equals(e.key)) {
                    buckets[idx].value = value;
                    return value;
                } else if (e.removed == true) {
                    removedIndex = idx;
                }
                idx = hash(key, ++i);
                e = buckets[idx];
            }

            //если на пути вставки встретился удаленный элемент -
            //вставим туда наши ключ-значение
            //size при этом останется каким и был
            if (removedIndex != -1) {
                BucketElement<K, V> removed = buckets[removedIndex];
                removed.removed = false;
                removed.key = key;
                removed.value = value;

                return removed.value;
            }

            e = new BucketElement<>(key, value);
            buckets[idx] = e;
            size++;

            return e.value;
        }

        private int hash(K key, int i) {
            int keyHash = (key.hashCode() * 31) % this.capacity;
            keyHash = keyHash > 0 ? keyHash : -keyHash;
            return (keyHash % capacity + i) % capacity;
        }

        private void rehash() {
            BucketElement<K, V>[] oldBuckets = buckets;

            capacity = (capacity * 2) + 1;
            threshold = (int) (capacity * loadFactor);
            buckets = (BucketElement<K, V>[]) new BucketElement[capacity];
            size = 0;

            int end = oldBuckets.length;
            for (int i = 0; i < end; i++) {
                BucketElement<K, V> oldElement = oldBuckets[i];
                if (oldElement == null || oldElement.removed) continue;

                int k = 0;
                int idx = hash(oldElement.key, k);
                BucketElement<K, V> newElement = buckets[idx];
                while (newElement != null) {
                    idx = hash(oldElement.key, ++k);
                    newElement = buckets[idx];
                }
                buckets[idx] = new BucketElement<>(oldElement.key, oldElement.value);
                size++;
            }
        }
    }

    /**
     * С открытой адресацией отсутствуют цепочки внутри корзины
     */
    public static class BucketElement<K, V> {
        //не удаляем объект, а помечаем его удаленным
        private boolean removed;
        private K key;
        private V value;

        public BucketElement(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
