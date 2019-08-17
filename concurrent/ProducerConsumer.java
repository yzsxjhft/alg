package concurrent;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    public static void main(String[] args) {
        Factory factory = new Factory(10);
        for (int i=0; i< 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    new Producer(factory, new Random().nextInt()).product();
                }
            }.start();
        }

        for (int i=0; i<10; i++) {
            new Thread() {
                @Override
                public void run() {
                    new Consumer(factory).consume();
                }
            }.start();
        }
    }

}

class Factory {
    public LinkedList<Integer> queue;
    public int capacity;
    public ReentrantLock lock;
    public Condition unFull;
    public Condition unEmpty;

    public Factory(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.unFull = lock.newCondition();
        this.unEmpty = lock.newCondition();
    }
}

class Producer {

    private Factory factory;
    private int num;

    public Producer(Factory factory, int num) {
        this.factory = factory;
        this.num = num;
    }

    public void product() {
        factory.lock.lock();
        while (factory.queue.size() == factory.capacity) {
            try {
                factory.unFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        factory.queue.add(num);
        factory.unEmpty.signal();
        factory.lock.unlock();
    }
}

class Consumer {
    private Factory factory;

    public Consumer(Factory factory) {
        this.factory = factory;
    }

    public void consume() {
        factory.lock.lock();
        while (factory.queue.size() == 0) {
            try {
                factory.unEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int num = factory.queue.remove();
        System.out.println(num);
        factory.unFull.signal();
        factory.lock.unlock();
    }
}
