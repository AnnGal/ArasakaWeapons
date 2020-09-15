package art.manguste.android.ArasakaWeapons.data;

public class ProductInOrder{
    private Product product;
    private int itemsInOrder;    // not best decision, better make another class


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

    private void checkNumInRange(){
        if (itemsInOrder > Order.getMaxNumPerItem()){
            this.itemsInOrder = Order.getMaxNumPerItem();
        } else if (itemsInOrder < Order.getMinNumPerItem()){
            this.itemsInOrder = Order.getMinNumPerItem();
        }
    }

    public Product getProduct() {
        return product;
    }
}
