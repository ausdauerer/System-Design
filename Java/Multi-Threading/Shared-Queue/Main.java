public class Main {
    public static void main(String[] args) {
        SharedQueue<Item> q = new SharedQueue<Item>();

        Worker<Item> worker1 = new Worker<Item>("Worker 1", q);
        Worker<Item> worker2 = new Worker<Item>("Worker 2", q);

        worker1.start();
        worker2.start();

        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        Item item3 = new Item("Item 3");
        Item item4 = new Item("Item 4");

        q.addItem(item1);
        q.addItem(item2);
        q.addItem(item3);
        q.addItem(item4);

        try {
            Thread.sleep(2000);  // main thread sleeps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker1.stopWorker();
        worker2.stopWorker();

        q.stopQueue();

        try {
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}