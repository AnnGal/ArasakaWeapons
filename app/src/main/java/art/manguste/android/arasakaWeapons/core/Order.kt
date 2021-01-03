package art.manguste.android.arasakaWeapons.core

import android.util.Log
import art.manguste.android.arasakaWeapons.util.getAssignedDroneId
import art.manguste.android.arasakaWeapons.util.getOrderNum
import java.util.*

/***
 * Singleton.
 * Imitating the cart where customer stores items before buy it.
 * Can add and remove products. Also, it's a main data source for OrderActivity.
 * Stores not Product directly, but ProductInOrder which contains extra data.
 */
object Order {
    lateinit var number: String
        private set
    lateinit var droneId: String
        private set
    var productList: ArrayList<ProductInOrder> = ArrayList()
        private set

    const val maxNumPerProduct = 99
    const val minNumPerProduct = 1
    private val TAG = Order::class.java.simpleName

    // sets and reset order number and book drone for delivery
    private fun setNewOrderParams() {
        number = getOrderNum()
        droneId = getAssignedDroneId()
        productList.clear()
    }

    /**
     * Add new product in cart or add to existing product in cart
     * @param product - product added in cart
     * @param count - how many items
     */
    fun placeOrderToCart(product: Product, count: Int = 0) {
        Log.d(TAG, "placeOrderToCart: adding id=" + product.id)
        var hasMatch = false
        for (productInOrder in productList) {
            //Log.d(TAG, "placeOrderToCart: "+product.getId() + " compare to id=" + productInOrder.getProduct().getId());
            if (productInOrder.product.id == product.id) {
                hasMatch = true
                //Log.d(TAG, "placeOrderToCart: got match for the id=" + product.getId());
                // add total count
                productInOrder.itemsInOrder += count//changeItemsInOrder(count)
                break
            }
        }
        if (!hasMatch) {
            productList.add(ProductInOrder(product, count))
        }
    }

    val orderSize: Int
        get() = productList.size

    /**
     * Check if no items yet
     */
    val isAnyProductInCart: Boolean
        get() = productList.isNotEmpty()

    val itemsCount: String
        get() {
            val orderSize: String
            orderSize = if (productList.size > 99) {
                "*"
            } else {
                productList.size.toString()
            }
            return orderSize
        }

    fun removeProduct(product: ProductInOrder) {
        productList.remove(product)
    }

    /**
     * Order placed
     */
    fun placeOrderForExecution(): Boolean {
        // if it was a real order ->
        // go to DB and confirm an order
        // find deliver drone - put deliver task + get it number
        // send drone
        return true
    }

    fun resetOrder() {
        setNewOrderParams()
    }

    /**
     * Total price for items in cart
     */
    val totalPrice: Double
        get() {
            var totalPrice = 0.0
            for (productInOrder in productList) {
                totalPrice += productInOrder.product.price * productInOrder.itemsInOrder
            }
            return totalPrice
        }


    init {
        setNewOrderParams()
    }
}