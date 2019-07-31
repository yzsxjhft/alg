import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CasStack<T> {
    AtomicInteger state = new AtomicInteger();
    volatile int s = 0;
    LinkedList<T> stack = new LinkedList<>();

    public void push(T e) {
        while(true) {
            if (state.compareAndSet(0, 1)) {
                stack.push(e);
                state.set(0);
                return;
            }
        }
    }

    public void pop() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                stack.removeLast();
                state.set(0);
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CasStack<Integer> stack = new CasStack<>();
        CountDownLatch latch = new CountDownLatch(10);
        for (int i=0; i<10; i++) {
            new MyThread(i, stack, latch).start();
        }
        latch.await();
        System.out.println(stack.stack);
    }


}

class MyThread extends Thread{
    int index;
    CasStack<Integer> stack;
    CountDownLatch latch;

    public MyThread(int index, CasStack stack, CountDownLatch latch) {
        this.index = index;
        this.stack = stack;
        this.latch = latch;
    }

    @Override
    public void run() {
        stack.push(index);
        latch.countDown();
    }
}