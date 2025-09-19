import java.util.*;

public class SharedQueue<T> {
    Queue<T> q = new LinkedList<T>();
    boolean running = false;

    public SharedQueue() {
        this.running = true;
    }

    public synchronized T getItem() {
        while (this.q.isEmpty()) {
            try {
                if(!this.running) {
                    return null;
                }
                wait(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        return q.poll();
    }

    public synchronized void addItem(T i) {
        this.q.offer(i);
        notifyAll();
    }

    public synchronized void stopQueue() {
        this.running = false;
    }
}