import java.util.ArrayList;

public class OrderService {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private Inventory inventory;

    public OrderService(Inventory inventory) {
        this.inventory = inventory;
    }

    public int createOrder(int itemId, int quantity) {
        while (true) {
            int itemQuantity = inventory.getItemQuantity(itemId);

            if (itemQuantity < quantity) {
                return -1;
            }

            if (inventory.checkAndReduceItemQuantity(itemId, itemQuantity, quantity)) {
                break;
            }
        }

        synchronized (this.orders) {
            int orderId = this.orders.size();
            Order order = new Order(orderId, itemId, quantity);
            this.orders.add(order);

            return orderId;
        }
    }
}