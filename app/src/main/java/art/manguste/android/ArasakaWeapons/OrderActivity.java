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

import java.text.DecimalFormat;
import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.ProductInOrder;

public class OrderActivity extends AppCompatActivity
            implements OrderAdapter.OrderClickListener{

    //private static final String TAG = OrderActivity.class.getSimpleName();

    OrderAdapter mAdapter;
    TextView mTotalPriceTextView;
    View mLayoutFullCart;
    View mLayoutEmptyCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cart");

        mLayoutFullCart = findViewById(R.id.layout_full_cart);
        mLayoutEmptyCart = findViewById(R.id.layout_empty_cart);
        mTotalPriceTextView = findViewById(R.id.tv_total_price);
        mAdapter = new OrderAdapter(this);

        //set recycler view stuff
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        // add adapter
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
    protected void onResume() {
        checkLayoutsVisibility();
        super.onResume();
    }

    /**
     * if no products in the cart shows special layout
     * */
    private void checkLayoutsVisibility() {
        if (Order.getCurrentOrder().isAnyProductInCart()){
            mLayoutFullCart.setVisibility(View.VISIBLE);
            mLayoutEmptyCart.setVisibility(View.GONE);
            refreshTotalPrice();
        } else {
            mLayoutFullCart.setVisibility(View.GONE);
            mLayoutEmptyCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewClick(View v, int position, MaterialCardView item, ProductInOrder productInOrder) {
        int viewId = v.getId();

        if (viewId == R.id.action_delete_from_cart || viewId == R.id.ll_action_delete_from_cart) {
            // remove product from order
            ConfirmationAndDelete(productInOrder, position);

        } else if (viewId == R.id.action_increase_count || viewId == R.id.action_decrease_count){
            int diff = (viewId == R.id.action_increase_count)? 1 : -1;

            // change items count
            productInOrder.changeItemsInOrder(diff);
            // get actual info
            int itemsCount = productInOrder.getItemsInOrder();
            String priceString = productInOrder.getProduct().getPriceString();
            String totalPriceString = productInOrder.getProduct().getTotalPriceString(itemsCount);
            // set actual information
            ((TextView) item.findViewById(R.id.tv_items_count)).setText(String.valueOf(itemsCount));
            ((TextView) item.findViewById(R.id.tv_price_card)).setText(priceString);
            ((TextView) item.findViewById(R.id.tv_price_card_total)).setText(totalPriceString);
            // change order total cost
            refreshTotalPrice();
        }
    }

    public void onPlaceOrder(View view) {
        if (Order.getCurrentOrder().placeOrderForExecution()){
            Intent showOrderInfo = new Intent(this, PlacedOrderActivity.class);
            String orderNum = String.valueOf(Order.getCurrentOrder().getNumber());

            // send orders data, because it will be erased later
            showOrderInfo.putExtra(PlacedOrderActivity.EXTRA_ORDER_NUM, orderNum);
            showOrderInfo.putExtra(PlacedOrderActivity.EXTRA_DRONE_ID, String.valueOf(Order.getCurrentOrder().getDroneId()));

            // reset order params
            Order.getCurrentOrder().resetOrder();
            startActivity(showOrderInfo);
        }
    }

    /**
     * Dialog which confirm and del product from the cart
     */
    private void ConfirmationAndDelete(final ProductInOrder productInOrder, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dlg_q_delete_from_order);
        // on delete from order
        builder.setPositiveButton(R.string.dlg_yes_delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Order.getCurrentOrder().removeProduct(productInOrder);
                mAdapter.notifyItemRemoved(position);
                refreshTotalPrice();
                checkLayoutsVisibility();
            }
        });
        // on chancel
        builder.setNegativeButton(R.string.dlg_no_chancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        builder.create().show();
    }

    /**
     * Refresh actual order cost  
     */
    private void refreshTotalPrice() {
        String priceString = String.valueOf(new DecimalFormat("##.##").format(Order.getCurrentOrder().getTotalPrice()));
        mTotalPriceTextView.setText(priceString);
    }

/*
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/


}