package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import art.manguste.android.ArasakaWeapons.data.Product;

public class OrderActivity extends AppCompatActivity
            implements OrderAdapter.OrderClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cart");

        RecyclerView recyclerView =  findViewById(R.id.recyclerView);

        // add adapter
        OrderAdapter adapter = new OrderAdapter(this);
        recyclerView.setAdapter(adapter);

        // connect data and view
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onViewClick(View v, int position, MaterialCardView item, Product product) {
        int viewId = v.getId();

        if (viewId == R.id.action_delete_from_cart || viewId == R.id.ll_action_delete_from_cart) {
            // set order off
            item.setVisibility(View.GONE);
            // TODO recalculate price & order
            // TODO think how make resize recycle view
            // TODO add the dialog "are you sure"
        } else if (viewId == R.id.action_increase_count){
            TextView tvCount = item.findViewById(R.id.tv_items_count);

            Integer count = Integer.valueOf((String) tvCount.getText());
            count ++;
            tvCount.setText(String.valueOf(count));
            // TODO recalculate price, order and total price
        }  else if (viewId == R.id.action_decrease_count){
            TextView tvCount = item.findViewById(R.id.tv_items_count);

            Integer count = Integer.valueOf((String) tvCount.getText());
            count --;
            tvCount.setText(String.valueOf(count));
            // TODO recalculate price, order and total price
        }
    }

    public void onPlaceOrder(View view) {

        startActivity(new Intent(this, PlacedOrderActivity.class));
    }
/*
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/


}