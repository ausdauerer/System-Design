public class OrderRequest {
    private int itemId;
    private int quantity;

    public OrderRequest(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return this.itemId;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
