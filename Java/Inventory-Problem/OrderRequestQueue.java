import java.util.concurrent.LinkedBlockingQueue;


public class OrderRequestQueue {
    private LinkedBlockingQueue<OrderRequest> queue = new LinkedBlockingQueue<OrderRequest>();

    public void addRequest (OrderRequest req) throws InterruptedException {
        queue.put(req);
    }

    public OrderRequest fetchRequest () throws InterruptedException {
        return queue.take();
    }
}