package art.manguste.android.arasakaWeapons.data

/**
 * Class for the product in order. Contains extra data, e.g. items count.
 */
class ProductInOrder(val product: Product, itemsCount: Int) {
    // how many items of this product in order
    var itemsInOrder : Int = if (itemsCount > 0) itemsCount else 1
        set(value) {
            field = when {
                value > Order.maxNumPerProduct -> {
                    Order.maxNumPerProduct
                }
                value < Order.minNumPerProduct -> {
                    Order.minNumPerProduct
                }
                else -> value
            }
        }

}