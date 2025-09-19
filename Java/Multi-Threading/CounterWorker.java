import java.util.concurrent.locks.ReentrantLock;

public class CounterWorker extends Thread {
    private Counter counter;
    private final ReentrantLock lock = new ReentrantLock();

    public CounterWorker(Counter counter) {
        this.counter = counter;
    }

    public void sleepRandom() throws InterruptedException {
        Thread.sleep((long)(Math.random() * 50));
    }

    public void run() {
        try {
            for (int i = 0; i <= 9; i++) {
                sleepRandom();
                this.counter.readWriteLockIncrement();
                sleepRandom();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}