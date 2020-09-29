package art.manguste.android.ArasakaWeapons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import java.text.DecimalFormat;
import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.Product;
import art.manguste.android.ArasakaWeapons.Util.WeaponType;


public class CardDetailActivity extends AppCompatActivity {
    private static final String SAVE_KEY_ITEM_COUNT = "item_count";

    ImageButton mCartImage;
    TextView mItemsCount;
    TextView mProductName;
    TextView mProductType;
    TextView mProductDescription;
    ImageView mImage;
    TextView mPrice;
    TextView mItemImage;
    TextView mAddToCart;
    ImageButton mIncCount;
    ImageButton mDecCount;

    private Integer itemsCount = 1;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        // toolbar and return button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        // UI
        mCartImage = findViewById(R.id.tv_to_cart);
        mItemsCount = findViewById(R.id.tv_items_count);
        mProductName = findViewById(R.id.product_name);
        mProductType = findViewById(R.id.product_type);
        mProductDescription = findViewById(R.id.product_description);
        mPrice = findViewById(R.id.price);
        mImage = findViewById(R.id.image);
        mItemImage = findViewById(R.id.tv_number_items_in_cart);
        mAddToCart = findViewById(R.id.tv_add_to_cart);
        mIncCount = findViewById(R.id.action_increase_count);
        mDecCount = findViewById(R.id.action_decrease_count);

        // Get parcelable object
        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
            product = arguments.getParcelable(Product.class.getSimpleName());
        }


        setCardData();

        // load items count after rotation
        if (savedInstanceState != null) {
            mItemsCount.setText(savedInstanceState.getString(SAVE_KEY_ITEM_COUNT));
        }

        // Buttons Listeners -->
        // increase items count action
        mIncCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if we still can increase - do it
                if (Order.getMaxNumPerProduct() > itemsCount){
                    itemsCount++;
                    updateItemsAndPrice();
                } else {
                    Toast.makeText(getBaseContext(), R.string.warning_max_items, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // decrease items count action
        mDecCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if we still can decrease - do it
                if (Order.getMinNumPerProduct() < itemsCount){
                    itemsCount--;
                    updateItemsAndPrice();
                }
            }
        });

        // add to cart and refresh cart icon action
        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.getCurrentOrder().placeOrderToCart(product, itemsCount);
                String message = getString(R.string.snack_message_added_to_cart, product.getTitle());
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                CheckCartImage();
            }
        });

        // move to cart activity action
        (findViewById(R.id.tv_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToOrderActivity();
            }
        });
        (findViewById(R.id.layout_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToOrderActivity();
            }
        });
        (findViewById(R.id.tv_number_items_in_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToOrderActivity();
            }
        });

        CheckCartImage();
    }

    protected void onResume() {
        CheckCartImage();
        super.onResume();
    }

    private void moveToOrderActivity(){
        startActivity(new Intent(getBaseContext(), OrderActivity.class));
    }

    private void setCardData() {
        if (product != null) {
            mProductName.setText(product.getTitle());
            if (product.getWeaponType().equals(WeaponType.NONE)) {
                mProductType.setVisibility(View.GONE);
            } else {
                mProductType.setText(product.getWeaponType().toString());
            }
            mProductDescription.setText(product.getFullDescription());
            mImage.setImageResource(product.getImageResourceId());
            updateItemsAndPrice();
        }
    }

    private void updateItemsAndPrice() {
        mItemsCount.setText(String.valueOf(itemsCount));
        mPrice.setText(new DecimalFormat("##.##").format(itemsCount * product.getPrice()));
    }

    /**
     * Refresh cart icon after order changes
     * */
    public void CheckCartImage() {
        Order order = Order.getCurrentOrder();
        if (order.isAnyProductInCart()){
            mItemImage.setText(order.getItemsCount());
            mItemImage.setVisibility(View.VISIBLE);
        } else {
            mItemImage.setVisibility(View.INVISIBLE);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_KEY_ITEM_COUNT, String.valueOf(mItemsCount.getText()));
    }
}