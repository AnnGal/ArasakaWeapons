package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import art.manguste.android.ArasakaWeapons.data.Order;

public class PlacedOrderActivity extends AppCompatActivity {

    private static final String TAG = PlacedOrderActivity.class.getSimpleName();

    public static final String EXTRA_ORDER_NUM = "order_number";
    public static final String EXTRA_DRONE_ID = "drone_id";

    private String mOrderNum;
    private String mDroneId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        // Retrieving intent data
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ORDER_NUM)){
            mOrderNum = intent.getStringExtra(EXTRA_ORDER_NUM);
        }
        if (intent.hasExtra(EXTRA_DRONE_ID)){
            mDroneId = intent.getStringExtra(EXTRA_DRONE_ID);
        }
        Log.d(TAG, "onCreate: orderNum="+ mOrderNum);

        showOrderInfo();
        Order.getCurrentOrder().placeOrderForExecution();
    }

    private void showOrderInfo() {
        // order info text
        String orderMessage = getString(R.string.info_order_num, mOrderNum);
        ((TextView) findViewById(R.id.tv_order_created_info)).setText(orderMessage);

        // drone info text
        String droneMessage  = getString(R.string.info_drone, mDroneId, mDroneId);
        ((TextView) findViewById(R.id.tv_drone_send_info)).setText(droneMessage);
    }

    public void OnReturnClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}