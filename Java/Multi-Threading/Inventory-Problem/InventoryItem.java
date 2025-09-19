import java.util.concurrent.atomic.AtomicInteger;

public class InventoryItem {
    private int id;
    private AtomicInteger quantity = new AtomicInteger(0);

    public InventoryItem(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity.get();
    }

    public int incrementQuantity(int count) {
        return this.quantity.addAndGet(count);
    }

    public int decrementQuantity(int count) {
        return this.quantity.addAndGet(-count);
    }

    public boolean compareAndUpdateQuantity(int originalQuantity, int newQuantity) {
        return this.quantity.compareAndSet(originalQuantity, newQuantity);
    }
}