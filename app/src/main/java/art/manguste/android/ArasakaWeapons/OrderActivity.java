package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

import art.manguste.android.ArasakaWeapons.data.OrderAdapter;
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
    public void onViewClick(View v, int position, MaterialCardView item, Product product) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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