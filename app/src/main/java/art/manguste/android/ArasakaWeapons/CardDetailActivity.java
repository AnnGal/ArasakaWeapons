package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CardDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)){
            ((TextView) findViewById(R.id.tv_detail)).setText( intent.getStringExtra(Intent.EXTRA_TEXT));
        }

        ((TextView) findViewById(R.id.btn_to_cart_from_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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