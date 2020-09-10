package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import art.manguste.android.ArasakaWeapons.data.Order;


public class CardDetailActivity extends AppCompatActivity {
    TextView mCartImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        // toolbar and return button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        mCartImage = findViewById(R.id.tv_to_cart_from_detail);

        // TODO del aft test
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)){
            ((TextView) findViewById(R.id.product_name)).setText( intent.getStringExtra(Intent.EXTRA_TEXT));
        }

        // TODO replace this with normal interaction
/*        ((ImageButton) findViewById(R.id.ib_add_position_in_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
                CheckCartImage();
            }
        });

        */

        CheckCartImage();
    }

    public void CheckCartImage() {
        Order order = Order.getCurrentOrder();
        if (order.isAnyProductInCart()){
            mCartImage.setBackground(getResources().getDrawable(R.drawable.ic_cart));
        } else {
            mCartImage.setBackground(getResources().getDrawable(R.drawable.ic_empty_cart));
        }
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