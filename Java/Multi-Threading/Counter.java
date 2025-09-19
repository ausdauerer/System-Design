import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();


    public void sleepRandom() throws InterruptedException {
        Thread.sleep((long)(Math.random() * 50));
    }

    public Counter() {
        this.count = 0;
    }

    public void increment() {
        int val = this.count;

        try {
            sleepRandom();
            this.count = val + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void syncIncrement() {
        int val = this.count;

        try {
            sleepRandom();
            this.count = val + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void mutexIncrement() {
        this.lock.lock();

        int val = this.count;

        try {
            sleepRandom();
            this.count = val + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            this.lock.unlock();
        }
    }

    // This will not solve this issue in our case
    public void readWriteLockIncrement() {
        this.rwlock.readLock().lock();

        int val = this.count;

        this.rwlock.readLock().unlock();

        try {
            sleepRandom();
            this.rwlock.writeLock().lock();
            this.count = val + 1;
            this.rwlock.writeLock().unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public int get() {
        return this.count;
    }

    public void decrement() {
        this.count--;
    }

    public void set(int val) {
        this.count = val;
    }
}