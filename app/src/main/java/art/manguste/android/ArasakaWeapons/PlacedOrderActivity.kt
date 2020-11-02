package art.manguste.android.ArasakaWeapons

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import art.manguste.android.ArasakaWeapons.data.Order

class PlacedOrderActivity : AppCompatActivity() {
    private var mOrderNum: String? = null
    private var mDroneId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placed)

        // Retrieving intent data
        val intent = intent
        if (intent.hasExtra(EXTRA_ORDER_NUM)) {
            mOrderNum = intent.getStringExtra(EXTRA_ORDER_NUM)
        }
        if (intent.hasExtra(EXTRA_DRONE_ID)) {
            mDroneId = intent.getStringExtra(EXTRA_DRONE_ID)
        }
        Log.d(TAG, "onCreate: orderNum=$mOrderNum")
        showOrderInfo()
        Order.placeOrderForExecution()
    }

    private fun showOrderInfo() {
        // order info text
        val orderMessage = getString(R.string.info_order_num, mOrderNum)
        (findViewById<View>(R.id.tv_order_created_info) as TextView).text = orderMessage

        // drone info text
        val droneMessage = getString(R.string.info_drone, mDroneId, mDroneId)
        (findViewById<View>(R.id.tv_drone_send_info) as TextView).text = droneMessage
    }

    fun OnReturnClick(view: View?) {
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