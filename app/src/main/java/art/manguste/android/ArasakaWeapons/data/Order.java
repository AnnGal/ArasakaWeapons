package art.manguste.android.ArasakaWeapons.data;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Integer number;
    private Integer droneId;
    private Integer droneAccessNumber;

    private static final int MAX_NUM_PER_ITEM = 7;
    private static final int MIN_NUM_PER_ITEM = 0;

    Map<Product, Integer> ordersList = new HashMap();

    private Order() {}

    private static class SingletonHolder {
        public static final Order instance = new Order();
    }

    public static Order getCurrentOrder()  {
        return SingletonHolder.instance;
    }

    public boolean isAnyProductInCart(){
        return (ordersList.size() > 0);
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

    public Map<Product, Integer> getOrdersList() {
        return ordersList;
    }

    public void placeOrderToCart(Product product, Integer count) {
        // should be more than 0 and should be added to prev value
        Integer countProduct = (count > 0) ? count : 1;
        if (ordersList.containsKey(product)){
            countProduct += ordersList.get(product);
        }

        ordersList.put(product, countProduct);
    }

    public static int getMaxNumPerItem() {
        return MAX_NUM_PER_ITEM;
    }

    public static int getMinNumPerItem() {
        return MIN_NUM_PER_ITEM;
    }
}
