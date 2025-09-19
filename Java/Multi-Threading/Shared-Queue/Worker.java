public class Worker <T> extends Thread {
    String name;
    SharedQueue<T> queue;
    boolean running;

    public Worker(String name, SharedQueue<T> q) {
        this.name = name;
        this.queue = q;
    }

    public void run() {
        this.running = true;
        while (this.running) {
            T item = this.queue.getItem();
            System.out.println("Worker " + this.name + " consumed item : ");
        }
    }

    public void stopWorker() {
        this.running = false;
    }
}