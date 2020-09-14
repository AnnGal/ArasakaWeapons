package art.manguste.android.ArasakaWeapons.data;

import android.util.Log;
import java.util.ArrayList;

import static art.manguste.android.ArasakaWeapons.Util.GetOrderParamsKt.*;


public class Order {
    private String number;
    private String droneId;
    //private Integer droneAccessNumber;

    private static final int MAX_NUM_PER_ITEM = 7;
    private static final int MIN_NUM_PER_ITEM = 0;

    private static final String TAG = Order.class.getSimpleName();

    ArrayList<ProductInOrder> productList;

    private Order() {
        setNewOrderParams();
    }

    private void setNewOrderParams() {
        number = getOrderNum();
        droneId = getAssignedDroneId();
        productList = new ArrayList<>();
    }

    private static class SingletonHolder {
        public static final Order instance = new Order();
    }

    public static Order getCurrentOrder()  {
        return SingletonHolder.instance;
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

    public void placeOrderToCart(Product product, Integer count) {
        Log.d(TAG, "placeOrderToCart: adding id=" + product.getId());

        boolean hasMatch = false;
        for (ProductInOrder productInOrder : productList) {
            Log.d(TAG, "placeOrderToCart: "+product.getId() + " compare to id=" + productInOrder.getProduct().getId());
            if (productInOrder.getProduct().getId().intValue() == product.getId().intValue()){
                hasMatch = true;
                Log.d(TAG, "placeOrderToCart: got match for the id=" + product.getId());
                // add total count
                productInOrder.addItemsInOrder(count);
                break;
            }
        }

        if (!hasMatch){
            productList.add(new ProductInOrder(product, count));
        }
    }

    public static int getMaxNumPerItem() {
        return MAX_NUM_PER_ITEM;
    }

    public static int getMinNumPerItem() {
        return MIN_NUM_PER_ITEM;
    }

    public Integer getOrderSize() {
        return productList.size();
    }

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

    public void resetOrder(){
        setNewOrderParams();
    }
}
