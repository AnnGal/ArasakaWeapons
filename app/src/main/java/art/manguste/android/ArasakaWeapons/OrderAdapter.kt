package art.manguste.android.ArasakaWeapons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import art.manguste.android.ArasakaWeapons.OrderAdapter.OrderViewHolder
import art.manguste.android.ArasakaWeapons.data.Order
import art.manguste.android.ArasakaWeapons.data.ProductInOrder
import com.google.android.material.card.MaterialCardView

/**
 * Adapter for order activity. Shows products in the cart.
 */
class OrderAdapter(private val mOnClickListener: OrderClickListener?) : RecyclerView.Adapter<OrderViewHolder>() {
    interface OrderClickListener {
        fun onViewClick(v: View, position: Int, item: MaterialCardView, product: ProductInOrder?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val cv = LayoutInflater.from(parent.context).inflate(R.layout.order_card_view, parent, false) as MaterialCardView
        return OrderViewHolder(cv)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        Order.productList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return Order.orderSize
    }

    /**
     * Holder for a card
     */
    inner class OrderViewHolder(private val item: MaterialCardView) : RecyclerView.ViewHolder(item) {
        private var productInOrder: ProductInOrder? = null

        /**
         * place product info on a view card
         */
        fun bind(productInOrder: ProductInOrder) {
            this.productInOrder = productInOrder
            // get data
            val itemsCount = productInOrder.itemsInOrder
            val priceString = productInOrder.product.priceString
            val totalPriceString = productInOrder.product.getTotalPriceString(itemsCount)

            //set data
            (item.findViewById<View>(R.id.id_image_in_cart) as ImageView).setImageResource(productInOrder.product.imageResourceId)
            (item.findViewById<View>(R.id.productName) as TextView).text = productInOrder.product.title
            (item.findViewById<View>(R.id.count) as TextView).text = itemsCount.toString()
            (item.findViewById<View>(R.id.tv_price_card) as TextView).text = priceString
            (item.findViewById<View>(R.id.tv_price_card_total) as TextView).text = totalPriceString
        }

        /**
         * set data params and listeners
         */
        init {
            val addButton = itemView.findViewById<ImageButton>(R.id.actionDecreaseCount)
            val removeButton = itemView.findViewById<ImageButton>(R.id.actionIncreaseCount)
            val removeProductLayout = itemView.findViewById<LinearLayout>(R.id.ll_action_delete_from_cart)
            val removeProductButton = itemView.findViewById<ImageButton>(R.id.action_delete_from_cart)

            // action - decrease items count
            addButton.setOnClickListener { v -> mOnClickListener?.onViewClick(v, adapterPosition, item, productInOrder) }

            // action - increase items count
            removeButton.setOnClickListener { v -> mOnClickListener?.onViewClick(v, adapterPosition, item, productInOrder) }

            // action - remove order from cart (linear layout which contains cart icon)
            removeProductLayout.setOnClickListener { v -> mOnClickListener?.onViewClick(v, adapterPosition, item, productInOrder) }

            // action - remove order from cart
            removeProductButton.setOnClickListener { v -> mOnClickListener?.onViewClick(v, adapterPosition, item, productInOrder) }
        }
    }
}