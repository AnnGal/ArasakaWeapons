package art.manguste.android.ArasakaWeapons.data

import art.manguste.android.ArasakaWeapons.data.Order

/**
 * Class for the product in order. Contains extra data, e.g. items count.
 */
class ProductInOrder(val product: Product, itemsCount: Int) {
    var itemsInOrder : Int = if (itemsCount > 0) itemsCount else 1// how many items of this product in order


   /* fun getItemsInOrder(): Int {
        return itemsInOrder
    }

    fun setItemsInOrder(itemsInOrder: Int) {
        this.itemsInOrder = itemsInOrder
        checkNumInRange()
    }
    */
    fun changeItemsInOrder(itemsInOrder: Int) {
        this.itemsInOrder += itemsInOrder
        checkNumInRange()
    }

    /**
     * Control items count
     */
    private fun checkNumInRange() {

        if (itemsInOrder > Order.maxNumPerProduct) {
            itemsInOrder = Order.maxNumPerProduct
        } else if (itemsInOrder < Order.minNumPerProduct) {
            itemsInOrder = Order.minNumPerProduct
        }
    }

}