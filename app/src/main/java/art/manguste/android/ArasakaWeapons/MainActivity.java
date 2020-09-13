package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import art.manguste.android.ArasakaWeapons.data.CatalogType;
import art.manguste.android.ArasakaWeapons.data.Order;

public class MainActivity extends AppCompatActivity {

    TextView mCartImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set scroller
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.view_pager_shop);
        pager.setAdapter(pagerAdapter);

        //set tabLayout
        TabLayout tabLayout = findViewById(R.id.tabs_shop);
        tabLayout.setupWithViewPager(pager);

        // move to cart activity
        (findViewById(R.id.tv_to_cart_from_main)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });

        mCartImage = findViewById(R.id.tv_to_cart_from_main);
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

    private class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return CardListFragment.newInstance(CatalogType.WEAPON);
                case 1: return CardListFragment.newInstance(CatalogType.SERVICE);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getResources().getText(R.string.weapons_tab);
                case 1: return getResources().getText(R.string.services_tab);
            }
            return null;
        }
    }


}