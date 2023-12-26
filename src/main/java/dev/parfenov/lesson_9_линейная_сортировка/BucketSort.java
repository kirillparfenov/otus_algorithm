package dev.parfenov.lesson_9_линейная_сортировка;

public class BucketSort {
    public static void main(String[] args) {
        int[] array = {42, 64, 123, 786, 435, 243, 647, 98, 562, 1, 456, 8, 6, 554, 29, 87, 534, 41, 123, 35, 758, 543, 23, 345, 860};
        bucketSort(array);
    }

    public static void bucketSort(int[] array) {
        //найти максимум
        int max = Integer.MIN_VALUE;
        for (int v : array)
            max = Math.max(v, max);

        Bucket[] buckets = new Bucket[array.length];
        //По формуле распределить в бакеты:
        // value * number_of_elements / (max + 1)
        for (int v : array) {
            int index = (v * array.length) / (max + 1);
            buckets[index] = new Bucket(v, buckets[index]);

            //внутри бакетов отсортировать элементы по возрастанию
            Bucket current = buckets[index];
            while (current.next != null) {
                if (current.value < current.next.value) break;
                int temp = current.value;
                current.value = current.next.value;
                current.next.value = temp;
                current = current.next;
            }
        }

        //выпишем из buckets значения в первоначальный array
        int counter = 0;
        for (Bucket b : buckets) {
            Bucket current = b;
            while (current != null) {
                array[counter++] = current.value;
                current = current.next;
            }
        }
    }

    //Бакет, который хранит свое значение и ссылку на следующую корзину
    public static class Bucket {
        int value;
        Bucket next;

        public Bucket(int value, Bucket next) {
            this.value = value;
            this.next = next;
        }
    }
}
