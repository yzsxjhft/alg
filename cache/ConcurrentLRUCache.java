import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;


public class ConcurrentLRUCache<K,V> {

    private int capacity;
    private volatile LinkedList<Node> queue;
    private volatile HashMap<K, Node> dict;

    public ConcurrentLRUCache(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList();
        this.dict = new HashMap<>(capacity);
    }

    public synchronized V get(K key) {
        Node node = dict.get(key);
        if (node == null) return null;
        queue.remove(node);
        queue.addFirst(node);
        return node.value;

    }

    public synchronized void  put(K key, V value) {
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
        int capacity = 5;
        ConcurrentLRUCache<Integer, Integer> lruCache = new ConcurrentLRUCache<>(capacity);

        CountDownLatch latch = new CountDownLatch(100);
        Random keyRandom = new Random();
        Random valueRandom = new Random();
        for (int i=0; i<100; i++) {
            new Thread(){
                @Override
                public void run() {
                    lruCache.put(keyRandom.nextInt(10), valueRandom.nextInt(1000));
                    lruCache.get(keyRandom.nextInt(10));
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
        System.out.println("test");
    }

}
