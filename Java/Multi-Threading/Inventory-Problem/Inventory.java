import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    ConcurrentHashMap<Integer,InventoryItem> inventory = new ConcurrentHashMap<Integer,InventoryItem>();

    public void addItem(InventoryItem item) {
        this.inventory.put(item.getId(), item);
    }

    public void reduceItemQuantity(int id, int count) {
        InventoryItem item = this.inventory.get(id);
        item.decrementQuantity(count);
    }

    public void increaseItemQuantity(int id, int count) {
        InventoryItem item = this.inventory.get(id);
        item.incrementQuantity(count);
    }

    public int getItemQuantity(int id) {
        InventoryItem item = this.inventory.get(id);
        return item.getQuantity();
    }

    public boolean checkAndReduceItemQuantity(int id, int originalQuantity, int count) {
        InventoryItem item = this.inventory.get(id);
        return item.compareAndUpdateQuantity(originalQuantity, originalQuantity - count);
    }

    public boolean checkAndIncrementemQuantity(int id, int originalQuantity, int count) {
        InventoryItem item = this.inventory.get(id);
        return item.compareAndUpdateQuantity(originalQuantity, originalQuantity + count);
    }
}