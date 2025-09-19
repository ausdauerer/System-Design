public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        OrderRequestQueue queue = new OrderRequestQueue();
        OrderService service = new OrderService(inventory);

        InventoryItem item1 = new InventoryItem(0);
        InventoryItem item2 = new InventoryItem(1);
        InventoryItem item3 = new InventoryItem(2);

        inventory.addItem((item1));
        inventory.addItem((item2));
        inventory.addItem((item3));

        inventory.increaseItemQuantity(0, 5);
        inventory.increaseItemQuantity(1, 5);
        inventory.increaseItemQuantity(2, 5);

        OrderRequest r1 = new OrderRequest(0,3);
        OrderRequest r2 = new OrderRequest(0,2);
        OrderRequest r3 = new OrderRequest(1,4);
        OrderRequest r4 = new OrderRequest(2,2);

        OrderWorker w1 = new OrderWorker(queue, service);
        OrderWorker w2 = new OrderWorker(queue, service);

        w1.start();
        w2.start();

        try {
            queue.addRequest(r1);
            queue.addRequest(r2);
            queue.addRequest(r3);
            queue.addRequest(r4);
        } catch (InterruptedException e) {
            
        }
    }
}