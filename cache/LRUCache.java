package cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;


public class LRUCache<K,V> {

    private int capacity;
    private LinkedList<Node> queue;
    private HashMap<K, Node> dict;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList();
        this.dict = new HashMap<>(capacity);
    }

    public V get(K key) {
        Node node = dict.get(key);
        if (node == null) return null;
        queue.remove(node);
        queue.addFirst(node);
        return node.value;

    }

    public void  put(K key, V value) {
        Node node = dict.get(key);
        if (node == null) {
            node = new Node(key, value);
            if (dict.size() == capacity) {
                Node last = queue.removeLast();
                dict.remove(last.key);
            }
            dict.put(key, node);
        }
        else {
            queue.remove(node);
            node.value = value;
        }
        queue.addFirst(node);
    }

    public class Node {
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int capacity = 3;
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(capacity);
        Random keyRandom = new Random();
        Random valueRandom = new Random();

        for (int i=0; i<10; i++) {
            lruCache.put(keyRandom.nextInt(10), valueRandom.nextInt(1000));
            lruCache.get(keyRandom.nextInt(10));
        }

        System.out.println("test");
    }

}
