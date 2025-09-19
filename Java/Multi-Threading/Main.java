public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        CounterWorker worker1 = new CounterWorker(counter);
        CounterWorker worker2 = new CounterWorker(counter);

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

        System.out.println(counter.get());
    }
}