public class OrderWorker extends Thread{
    OrderRequestQueue q;
    OrderService s;
    boolean running = false;

    public OrderWorker(OrderRequestQueue q, OrderService s) {
        this.q = q;
        this.s = s;
    }

    public void run() {
        this.running = true;
        while (this.running) {
            try {
                OrderRequest r = this.q.fetchRequest();
                int orderId = s.createOrder(r.getItemId(), r.getQuantity());
                System.out.println("Order " + orderId + " created for item " + r.getItemId());
            } catch (InterruptedException e) {

            }
        }
    }
}
