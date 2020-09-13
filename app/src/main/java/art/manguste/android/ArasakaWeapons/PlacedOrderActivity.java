package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlacedOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
    }

    public void OnReturnClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}