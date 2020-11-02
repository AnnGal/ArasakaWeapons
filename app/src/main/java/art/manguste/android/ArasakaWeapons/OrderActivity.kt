package art.manguste.android.ArasakaWeapons

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import art.manguste.android.ArasakaWeapons.OrderAdapter.OrderClickListener
import art.manguste.android.ArasakaWeapons.data.Order
import art.manguste.android.ArasakaWeapons.data.ProductInOrder
import com.google.android.material.card.MaterialCardView
import java.text.DecimalFormat

class OrderActivity : AppCompatActivity(), OrderClickListener {
    //private static final String TAG = OrderActivity.class.getSimpleName();
    var mAdapter: OrderAdapter? = null
    var mTotalPriceTextView: TextView? = null
    var mLayoutFullCart: View? = null
    var mLayoutEmptyCart: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "Cart"
        mLayoutFullCart = findViewById(R.id.layout_full_cart)
        mLayoutEmptyCart = findViewById(R.id.layout_empty_cart)
        mTotalPriceTextView = findViewById(R.id.tv_total_price)
        mAdapter = OrderAdapter(this)

        //set recycler view stuff
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // add adapter
        recyclerView.adapter = mAdapter
        // connect data and view
        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager
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
            mLayoutFullCart!!.visibility = View.VISIBLE
            mLayoutEmptyCart!!.visibility = View.GONE
            refreshTotalPrice()
        } else {
            mLayoutFullCart!!.visibility = View.GONE
            mLayoutEmptyCart!!.visibility = View.VISIBLE
        }
    }

    override fun onViewClick(v: View, position: Int, item: MaterialCardView, productInOrder: ProductInOrder?) {
        val viewId = v.id
        if (viewId == R.id.action_delete_from_cart || viewId == R.id.ll_action_delete_from_cart) {
            // remove product from order
            if (productInOrder != null) ConfirmationAndDelete(productInOrder, position)
        } else if (viewId == R.id.actionIncreaseCount || viewId == R.id.actionDecreaseCount) {
            val diff = if (viewId == R.id.actionIncreaseCount) 1 else -1

            // change items count
            productInOrder!!.changeItemsInOrder(diff)
            // get actual info
            val itemsCount = productInOrder.itemsInOrder
            val priceString = productInOrder.product.priceString
            val totalPriceString = productInOrder.product.getTotalPriceString(itemsCount)
            // set actual information
            (item.findViewById<View>(R.id.count) as TextView).text = itemsCount.toString()
            (item.findViewById<View>(R.id.tv_price_card) as TextView).text = priceString
            (item.findViewById<View>(R.id.tv_price_card_total) as TextView).text = totalPriceString
            // change order total cost
            refreshTotalPrice()
        }
    }

    fun onPlaceOrder(view: View?) {
        if (Order.placeOrderForExecution()) {
            val showOrderInfo = Intent(this, PlacedOrderActivity::class.java)
            val orderNum: String = java.lang.String.valueOf(Order.number)

            // send orders data, because it will be erased later
            showOrderInfo.putExtra(PlacedOrderActivity.Companion.EXTRA_ORDER_NUM, orderNum)
            showOrderInfo.putExtra(PlacedOrderActivity.Companion.EXTRA_DRONE_ID, java.lang.String.valueOf(Order.droneId))

            // reset order params
            Order.resetOrder()
            startActivity(showOrderInfo)
        }
    }

    /**
     * Dialog which confirm and del product from cart
     */
    private fun ConfirmationAndDelete(productInOrder: ProductInOrder, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.dlg_q_delete_from_order)
        // on delete from order
        builder.setPositiveButton(R.string.dlg_yes_delete) { dialog, id ->
            Order.removeProduct(productInOrder)
            mAdapter!!.notifyItemRemoved(position)
            refreshTotalPrice()
            checkLayoutsVisibility()
        }
        // on chancel
        builder.setNegativeButton(R.string.dlg_no_chancel) { dialog, id -> dialog?.dismiss() }
        builder.create().show()
    }

    /**
     * Refresh actual order cost
     */
    private fun refreshTotalPrice() {
        val priceString: String = DecimalFormat("##.##").format(Order.totalPrice).toString()
        mTotalPriceTextView!!.text = priceString
    }
}