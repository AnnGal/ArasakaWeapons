package art.manguste.android.arasakaWeapons.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import art.manguste.android.arasakaWeapons.R
import art.manguste.android.arasakaWeapons.order.OrderAdapter.OrderViewHolder
import art.manguste.android.arasakaWeapons.core.Order
import art.manguste.android.arasakaWeapons.core.ProductInOrder
import com.google.android.material.card.MaterialCardView

/**
 * Adapter for order activity. Shows products in the cart.
 */
class OrderAdapter(private val mOnClickListener: OrderClickListener) : RecyclerView.Adapter<OrderViewHolder>() {

    interface OrderClickListener {
        fun onViewClick(v: View, position: Int, item: MaterialCardView, product: ProductInOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val cv = LayoutInflater.from(parent.context).inflate(R.layout.order_card_view, parent, false)
                as MaterialCardView
        return OrderViewHolder(cv)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        Order.productList[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return Order.orderSize
    }

    /**
     * Holder for a card
     */
    inner class OrderViewHolder(private val item: MaterialCardView) : RecyclerView.ViewHolder(item) {

        private lateinit var productInOrder: ProductInOrder

        /**
         * place product info on a view card
         */
        fun bind(productInOrder: ProductInOrder) {
            // todo fix viewbinding
           /* this.productInOrder = productInOrder

            // get data
            val itemsCount = productInOrder.itemsInOrder
            val priceString = productInOrder.product.priceString
            val totalPriceString = productInOrder.product.getTotalPriceString(itemsCount)

            //set data
            item.imageInCart.setImageResource(productInOrder.product.imageResourceId)
            item.productName.text = productInOrder.product.title
            item.count.text = itemsCount.toString()
            item.cardPrice.text = priceString
            item.cardPriceTotal.text = totalPriceString

            // action - decrease items count
            item.actionDecreaseCount.setOnClickListener {
                v -> mOnClickListener.onViewClick(v, adapterPosition, item, productInOrder)
            }

            // action - increase items count
            item.actionIncreaseCount.setOnClickListener {
                v -> mOnClickListener.onViewClick(v, adapterPosition, item, productInOrder)
            }

            // action - remove order from cart (linear layout which contains cart icon)
            item.deleteFromCartLL.setOnClickListener {
                v -> mOnClickListener.onViewClick(v, adapterPosition, item, productInOrder)
            }

            // action - remove order from cart
            item.deleteFromCart.setOnClickListener {
                v -> mOnClickListener.onViewClick(v, adapterPosition, item, productInOrder)
            }*/
        }

    }
}