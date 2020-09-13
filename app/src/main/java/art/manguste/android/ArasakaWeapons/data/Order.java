package art.manguste.android.ArasakaWeapons.data;

import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private Integer number;
    private Integer droneId;
    private Integer droneAccessNumber;

    private static final int MAX_NUM_PER_ITEM = 7;
    private static final int MIN_NUM_PER_ITEM = 0;

    private static final String TAG = Order.class.getSimpleName();

    //LinkedHashMap<Product, Integer> ordersMap = new LinkedHashMap<Product, Integer>();
    ArrayList<ProductInOrder> productList = new ArrayList<>();

    public ArrayList<ProductInOrder> getProductList() {
        return productList;
    }

    private Order() {}

    private static class SingletonHolder {
        public static final Order instance = new Order();
    }

    public static Order getCurrentOrder()  {
        return SingletonHolder.instance;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getDroneId() {
        return droneId;
    }

    public Integer getDroneAccessNumber() {
        return droneAccessNumber;
    }

    /*public LinkedHashMap<Product, Integer> getOrdersMap() {
        return ordersMap;
    }*/

    public void placeOrderToCart(Product product, Integer count) {
        // should be more than 0 and should be added to prev value
/*        Integer countProduct = (count > 0) ? count : 1;
        if (ordersMap.containsKey(product)){
            countProduct += ordersMap.get(product);
        }

        ordersMap.put(product, countProduct);*/
        Log.d(TAG, "placeOrderToCart: adding id=" + product.getId());
        // ProductInOrder
        boolean hasMatch = false;
        for (ProductInOrder productInOrder : productList) {
            Log.d(TAG, "placeOrderToCart: "+product.getId() + " compare to id=" + productInOrder.getProduct().getId());
            if (productInOrder.getProduct().getId().intValue() == product.getId().intValue()){
                hasMatch = true;
                Log.d(TAG, "placeOrderToCart: got match for id=" + product.getId());
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


}
