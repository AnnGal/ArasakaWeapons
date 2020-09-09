package art.manguste.android.ArasakaWeapons.data;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Integer number;
    private Integer droneId;
    private Integer droneAccessNumber;

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
        //TODO reload count each time, not erase it
        ordersList.put(product, count);
    }
}
