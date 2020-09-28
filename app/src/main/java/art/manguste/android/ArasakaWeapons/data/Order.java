package art.manguste.android.ArasakaWeapons.data;

import android.util.Log;
import java.util.ArrayList;

import static art.manguste.android.ArasakaWeapons.Util.GetOrderParamsKt.*;


/***
 * Singleton.
 * Imitating the cart where customer stores items before buy it.
 * Can add and remove products. Also, it's a main data source for OrderActivity.
 * Stores not Product directly, but ProductInOrder which contains extra data.
 */
public class Order {
    private String number;
    private String droneId;
    //private Integer droneAccessNumber;

    private static final int MAX_NUM_PER_PRODUCT = 99;
    private static final int MIN_NUM_PER_PRODUCT = 1;

    private static final String TAG = Order.class.getSimpleName();

    private ArrayList<ProductInOrder> productList;

    private Order() {
        setNewOrderParams();
    }

    private static class SingletonHolder {
        public static final Order instance = new Order();
    }

    /**
     * Use it to get access to order from any activity
     * @return Order with goods
     */
    public static Order getCurrentOrder()  {
        return SingletonHolder.instance;
    }

    /**
     * sets order number and book drone for delivery
     */
    private void setNewOrderParams() {
        number = getOrderNum();
        droneId = getAssignedDroneId();
        productList = new ArrayList<>();
    }

    public ArrayList<ProductInOrder> getProductList() {
        return productList;
    }

    public String getNumber() {
        return number;
    }

    public String getDroneId() {
        return droneId;
    }

    /**
     * Add new product in cart or add to existing product in cart
     * @param product - product added in cart
     * @param count - how many items
     */
    public void placeOrderToCart(Product product, Integer count) {
        Log.d(TAG, "placeOrderToCart: adding id=" + product.getId());

        boolean hasMatch = false;

        for (ProductInOrder productInOrder : productList) {
            //Log.d(TAG, "placeOrderToCart: "+product.getId() + " compare to id=" + productInOrder.getProduct().getId());
            if (productInOrder.getProduct().getId().intValue() == product.getId().intValue()){
                hasMatch = true;
                //Log.d(TAG, "placeOrderToCart: got match for the id=" + product.getId());
                // add total count
                productInOrder.changeItemsInOrder(count);
                break;
            }
        }

        if (!hasMatch){
            productList.add(new ProductInOrder(product, count));
        }
    }

    public static int getMaxNumPerProduct() {
        return MAX_NUM_PER_PRODUCT;
    }

    public static int getMinNumPerProduct() {
        return MIN_NUM_PER_PRODUCT;
    }

    public Integer getOrderSize() {
        return productList.size();
    }

    /**
     * Check if no items yet
     */
    public boolean isAnyProductInCart(){
        return !productList.isEmpty();
    }

    public String getItemsCount(){
        String orderSize;
        if (productList.size() > 99){
            orderSize = "*";
        } else {
            orderSize = String.valueOf(productList.size());
        }
        return orderSize;
    }

    public void removeProduct(ProductInOrder product) {
        productList.remove(product);
    }

    /**
     * Order placed
     */
    public boolean placeOrderForExecution(){
        // if it was a real order ->
        // go to DB and confirm an order
        // find deliver drone - put deliver task + get it number
        // send drone
        return true;
    }

    public void resetOrder(){
        setNewOrderParams();
    }

    /**
     * Total price for items in cart
     */
    public Double getTotalPrice() {
        double totalPrice = 0d;
        for (ProductInOrder productInOrder : productList) {
            totalPrice += productInOrder.getProduct().getPrice() * productInOrder.getItemsInOrder();
        }
        return totalPrice;
    }
}
