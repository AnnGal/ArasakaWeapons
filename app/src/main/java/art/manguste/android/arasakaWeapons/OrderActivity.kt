package art.manguste.android.arasakaWeapons

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import art.manguste.android.arasakaWeapons.ui.OrderAdapter.OrderClickListener
import art.manguste.android.arasakaWeapons.data.Order
import art.manguste.android.arasakaWeapons.data.ProductInOrder
import art.manguste.android.arasakaWeapons.ui.OrderAdapter
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.order_card_view.view.*
import java.text.DecimalFormat

class OrderActivity : AppCompatActivity(), OrderClickListener {

    //private static final String TAG = OrderActivity.class.getSimpleName();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "Cart"

        // add an adapter
        recycler.adapter = OrderAdapter(this)
        // connect data and view
        recycler.layoutManager = GridLayoutManager(this, 1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        checkLayoutsVisibility()
        super.onResume()
    }

    /**
     * if no products in the cart shows special layout
     */
    private fun checkLayoutsVisibility() {
        if (Order.isAnyProductInCart) {
            layoutFullCart.visibility = View.VISIBLE
            layoutEmptyCart.visibility = View.GONE
            refreshTotalPrice()
        } else {
            layoutFullCart.visibility = View.GONE
            layoutEmptyCart.visibility = View.VISIBLE
        }
    }

    override fun onViewClick(v: View, position: Int, item: MaterialCardView, productInOrder: ProductInOrder) {
        val viewId = v.id

        if (viewId == R.id.deleteFromCart || viewId == R.id.deleteFromCartLL) {
            // remove product from order
            confirmationAndDelete(productInOrder, position)

        } else if (viewId == R.id.actionIncreaseCount || viewId == R.id.actionDecreaseCount) {
            val diff = if (viewId == R.id.actionIncreaseCount) 1 else -1

            // change items count
            productInOrder.itemsInOrder += diff

            // set actual information
            item.count.text = productInOrder.itemsInOrder.toString()
            item.cardPrice.text = productInOrder.product.priceString
            item.cardPriceTotal.text =
                    productInOrder.product.getTotalPriceString(productInOrder.itemsInOrder)

            // change order total cost
            refreshTotalPrice()
        }
    }

    fun onPlaceOrder(view: View?) {
        if (Order.placeOrderForExecution()) {
            val showOrderInfo = Intent(this, PlacedOrderActivity::class.java)
            val orderNum: String = java.lang.String.valueOf(Order.number)

            // send orders data, because it will be erased later
            showOrderInfo.putExtra(PlacedOrderActivity.EXTRA_ORDER_NUM, orderNum)
            showOrderInfo.putExtra(PlacedOrderActivity.EXTRA_DRONE_ID, Order.droneId)

            // reset order params
            Order.resetOrder()
            startActivity(showOrderInfo)
        }
    }

    /**
     * Dialog which confirm and del product from cart
     */
    private fun confirmationAndDelete(productInOrder: ProductInOrder, position: Int) {
        val builder = AlertDialog.Builder(this)

        builder.apply {
            // asking
            setMessage(R.string.dlg_q_delete_from_order)
            // on ok - delete from order
            setPositiveButton(R.string.dlg_yes_delete) { _, _ ->
                Order.removeProduct(productInOrder)
                (recycler.adapter as OrderAdapter).notifyItemRemoved(position)
                refreshTotalPrice()
                checkLayoutsVisibility()
            }
            // on chancel
            setNegativeButton(R.string.dlg_no_chancel) { dialog, _ -> dialog?.dismiss() }
            //
            builder.create().show()
        }
    }

    /**
     * Refresh actual order cost
     */
    private fun refreshTotalPrice() {
        val priceString: String = DecimalFormat("##.##").format(Order.totalPrice).toString()
        totalPrice.text = priceString
    }
}