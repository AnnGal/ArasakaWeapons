package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    }
}