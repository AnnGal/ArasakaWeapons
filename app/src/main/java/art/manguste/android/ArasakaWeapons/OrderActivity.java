package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.ProductInOrder;

public class OrderActivity extends AppCompatActivity
            implements OrderAdapter.OrderClickListener{
    RecyclerView mRecyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cart");

        RecyclerView recyclerView =  findViewById(R.id.recyclerView);

        // add adapter
        mAdapter = new OrderAdapter(this);
        recyclerView.setAdapter(mAdapter);

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
    public void onViewClick(View v, int position, MaterialCardView item, ProductInOrder productInOrder) {
        int viewId = v.getId();

        if (viewId == R.id.action_delete_from_cart || viewId == R.id.ll_action_delete_from_cart) {
            // set order off
            showDeleteConfirmationDialog(productInOrder, position);
            // TODO recalculate price & order
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

    /**
     * Prompt the user to confirm that they want to delete this pet.
     */
    private void showDeleteConfirmationDialog(final ProductInOrder productInOrder, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dlg_q_delete_from_order);
        builder.setPositiveButton(R.string.dlg_yes_delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Order.getCurrentOrder().removeProduct(productInOrder);
                mAdapter.notifyItemRemoved(position);
            }
        });
        builder.setNegativeButton(R.string.dlg_no_chancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

/*
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/


}