package dev.manrihter.lesson_12_hash_table;

public class HashTableDemo {

    public static void main(String[] args) {
        var hTable = new HashTable<Integer, String>();
        hTable.put(21, "cat");
        hTable.put(11, "dog");
        hTable.put(3, "rat");
        hTable.put(4, "mock");
        hTable.put(5, "box");
        hTable.put(6, "try");
        hTable.put(7, "pig");
        hTable.put(8, "gip");
        hTable.put(9, "gap");
        hTable.put(10, "tac");


    }

    /**
     * Не является потокобезопасной
     */
    @SuppressWarnings("all")
    public static class HashTable<K, V> {

        //capacity - начальная емкость хэш-таблицы
        private static final int DEFAULT_CAPACITY = 10;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        // threshold = capacity * loadFactor;
        // если добавляется элемент и он больше threshold - сделать рехеширование
        private int threshold;
        private int capacity;
        private float loadFactor;

        //корзины, в которых хранится ссылка на key-value
        private HashEntry<K, V>[] buckets;

        //реальный размер хэш-таблицы
        private int size;

        public HashTable() {
            this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
        }

        private HashTable(int capacity, float loadFactor) {
            this.buckets = (HashEntry<K, V>[]) new HashEntry[capacity];
            this.capacity = capacity;
            this.loadFactor = loadFactor;
            this.threshold = (int) (capacity * loadFactor);
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public boolean contains(Object value) {
            if (value == null)
                throw new NullPointerException();

            int end = buckets.length;
            for (int i = 0; i < end; i++) {
                HashEntry<K, V> element = buckets[i];
                //идем по односвязному списку до тех пор,
                //пока не найдем элемент или упремся в null
                while (element != null) {
                    if (element.value.equals(value))
                        return true;
                    element = element.next;
                }
            }
            return false;
        }

        public V put(K key, V value) {
            int idx = hash(key);
            HashEntry<K, V> element = buckets[idx];

            //иначе идем по односвязному списку
            while (element != null) {
                if (element.key.equals(key)) {
                    V old = element.value;
                    element.value = value;
                    return old;
                }
                element = element.next;
            }

            //дошли до конца цепочки
            //проверим нужно ли рехеширование
            if (++size > threshold) {
                rehash();
                idx = hash(key);
            }

            element = new HashEntry<>(key, value);
            //добавляем в начало
            element.next = buckets[idx];
            buckets[idx] = element;

            return element.value;
        }

        private int hash(K key) {
            if (key == null)
                throw new NullPointerException();

            int hash = (key.hashCode() * 31) % this.capacity;
            return hash > 0 ? hash : -hash;
        }

        private void rehash() {
            HashEntry<K, V>[] oldBuckets = buckets;

            capacity = (capacity * 2) + 1;
            threshold = (int) (capacity * loadFactor);
            buckets = (HashEntry<K, V>[]) new HashEntry[capacity];
            size = 0;
            //распределяем корзины по новым ключам
            int end = oldBuckets.length;
            for (int i = 0; i < end; i++) {
                HashEntry<K, V> oldOne = oldBuckets[i];

                while (oldOne != null) {
                    int idx = hash(oldOne.key);
                    HashEntry<K, V> newOne = buckets[idx];

                    while (newOne != null) {
                        if (newOne.key.equals(oldOne.key)) {
                            newOne.value = oldOne.value;
                            size++;
                            break;
                        }
                        newOne = newOne.next;
                    }

                    newOne = new HashEntry<>(oldOne.key, oldOne.value);
                    newOne.next = buckets[idx];
                    buckets[idx] = newOne;
                    size++;

                    oldOne = oldOne.next;
                }
            }
        }
    }

    //корзина, которая хранит в себе key-value
    public static class HashEntry<K, V> {
        private HashEntry<K, V> next;
        private K key;
        private V value;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
