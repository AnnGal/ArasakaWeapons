package art.manguste.android.arasakaWeapons

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import art.manguste.android.arasakaWeapons.core.Order

class PlacedOrderActivity : AppCompatActivity() {
    private var orderNum: String? = null
    private var droneId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placed)

        // Retrieving intent data
        val intent = intent
        if (intent.hasExtra(EXTRA_ORDER_NUM)) {
            orderNum = intent.getStringExtra(EXTRA_ORDER_NUM)
        }
        if (intent.hasExtra(EXTRA_DRONE_ID)) {
            droneId = intent.getStringExtra(EXTRA_DRONE_ID)
        }
        Log.d(TAG, "onCreate: orderNum=$orderNum")
        showOrderInfo()
        Order.placeOrderForExecution()
    }

    private fun showOrderInfo() {
        // order info text
        val orderMessage = getString(R.string.info_order_num, orderNum)
        (findViewById<View>(R.id.tv_order_created_info) as TextView).text = orderMessage

        // drone info text
        val droneMessage = getString(R.string.info_drone, droneId, droneId)
        (findViewById<View>(R.id.tv_drone_send_info) as TextView).text = droneMessage
    }

    fun onReturnClick(view: View?) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = PlacedOrderActivity::class.java.simpleName
        const val EXTRA_ORDER_NUM = "order_number"
        const val EXTRA_DRONE_ID = "drone_id"
    }
}