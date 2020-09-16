package art.manguste.android.ArasakaWeapons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import art.manguste.android.ArasakaWeapons.data.CatalogType;
import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.Product;
import art.manguste.android.ArasakaWeapons.data.WeaponType;

public class MainActivity extends AppCompatActivity {

    ImageButton mCartImage;
    TextView mItemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTestData();

        //set scroller
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.view_pager_shop);
        pager.setAdapter(pagerAdapter);

        //set tabLayout
        TabLayout tabLayout = findViewById(R.id.tabs_shop);
        tabLayout.setupWithViewPager(pager);

        // move to cart activity
        (findViewById(R.id.tv_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });
        (findViewById(R.id.layout_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });
        (findViewById(R.id.tv_number_items_in_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });



        mCartImage = findViewById(R.id.tv_to_cart);
        mItemImage = findViewById(R.id.tv_number_items_in_cart);
        CheckCartImage();
    }


    @Override
    protected void onResume() {
        CheckCartImage();
        super.onResume();
    }

    public void CheckCartImage() {
        Order order = Order.getCurrentOrder();
        //TODO think what to do for elevation and API < level 21
        if (order.isAnyProductInCart()){
            mItemImage.setText(order.getItemsCount());
            mItemImage.setVisibility(View.VISIBLE);
        } else {
            mItemImage.setVisibility(View.INVISIBLE);
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

    private void initTestData() {
        Product.weapons = new Product[]{
                new Product(10104, getString(R.string.weapon_gorilla_title),
                        getString(R.string.weapon_gorilla_desc),
                        getString(R.string.weapon_gorilla_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.gorilla_arms, WeaponType.CYBERWARE),
                new Product(10105,
                        getString(R.string.weapon_m122_title),
                        getString(R.string.weapon_m122_desc),
                        getString(R.string.weapon_m122_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.cyberdyn, WeaponType.ASSAULTRIFLE),
                new Product(10106,
                        getString(R.string.weapon_launch_title),
                        getString(R.string.weapon_launch_desc),
                        getString(R.string.weapon_launch_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.launch_system, WeaponType.CYBERWARE),
                new Product(10107,
                        getString(R.string.weapon_kit_title),
                        getString(R.string.weapon_kit_desc),
                        getString(R.string.weapon_kit_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.wsa_pistol, WeaponType.AUTOPISTOL),
                new Product(10103,
                        getString(R.string.weapon_katana_title),
                        getString(R.string.weapon_katana_desc),
                        getString(R.string.weapon_katana_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.thermal_katana, WeaponType.MELEE),
                new Product(10101,
                        getString(R.string.weapon_mantis_title),
                        getString(R.string.weapon_mantis_desc),
                        getString(R.string.weapon_mantis_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.mantisblades, WeaponType.CYBERWARE),
                new Product(10102,
                        getString(R.string.weapon_tki20_title),
                        getString(R.string.weapon_tki20_desc),
                        getString(R.string.weapon_tki20_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.tki_20_shingen, WeaponType.SUBMACHINE),
                new Product(10108,getString(R.string.weapon_setsuko_title),
                        getString(R.string.weapon_setsuko_desc),
                        getString(R.string.weapon_setsuko_desc_full),
                        5500d, CatalogType.WEAPON, R.drawable.pms, WeaponType.SUBMACHINE)
        };

        // TODO add pics
        Product.services = new Product[]{
                new Product(20001,
                        getString(R.string.service_implants_title),
                        getString(R.string.service_implants_desc),
                        getString(R.string.service_implants_desc_full),
                        75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
                new Product(20001,
                        getString(R.string.service_guard_title),
                        getString(R.string.service_guard_desc),
                        getString(R.string.service_guard_desc_full),
                        75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
                new Product(20001,
                        getString(R.string.service_consult_title),
                        getString(R.string.service_consult_desc),
                        getString(R.string.service_consult_desc_full),
                        75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
                new Product(20001,
                        getString(R.string.service_install_title),
                        getString(R.string.service_install_desc),
                        getString(R.string.service_install_desc_full),
                        75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
                new Product(20001,
                        getString(R.string.service_train_title),
                        getString(R.string.service_train_desc),
                        getString(R.string.service_train_desc_full),
                        75500d, CatalogType.SERVICE, R.drawable.arasaka_logo),
                new Product(20001,
                        getString(R.string.service_control_title),
                        getString(R.string.service_control_desc),
                        getString(R.string.service_control_desc_full),
                        75500d, CatalogType.SERVICE, R.drawable.arasaka_logo)
        };

    }

}