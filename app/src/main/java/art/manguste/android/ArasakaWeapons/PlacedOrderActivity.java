package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import art.manguste.android.ArasakaWeapons.data.Order;

public class PlacedOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        showOrderInfo();
        Order.getCurrentOrder().resetOrder();
    }

    private void showOrderInfo() {
        // order info text
        String orderMessage = getString(R.string.info_order_num, Order.getCurrentOrder().getNumber());
        ((TextView) findViewById(R.id.tv_order_created_info)).setText(orderMessage);

        // drone info text
        String droneMessage  = getString(R.string.info_drone, Order.getCurrentOrder().getDroneId());
        ((TextView) findViewById(R.id.tv_drone_send_info)).setText(droneMessage);
    }

    public void OnReturnClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}