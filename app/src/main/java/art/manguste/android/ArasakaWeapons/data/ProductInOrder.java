package art.manguste.android.ArasakaWeapons.data;

/**
 * Class for the product in order. Contains extra data, e.g. items count.
 */
public class ProductInOrder{
    private Product product;
    private int itemsInOrder; // how many items of this product in order

    public ProductInOrder(Product product, Integer itemsCount) {
        this.product = product;
        itemsInOrder = itemsCount > 0 ? itemsCount : 1;
    }

    public int getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(int itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
        checkNumInRange();
    }

    public void changeItemsInOrder(int itemsInOrder) {
        this.itemsInOrder += itemsInOrder;
        checkNumInRange();
    }

    /**
     * Control items count
     */
    private void checkNumInRange(){
        if (itemsInOrder > Order.getMaxNumPerProduct()){
            this.itemsInOrder = Order.getMaxNumPerProduct();
        } else if (itemsInOrder < Order.getMinNumPerProduct()){
            this.itemsInOrder = Order.getMinNumPerProduct();
        }
    }

    public Product getProduct() {
        return product;
    }
}
