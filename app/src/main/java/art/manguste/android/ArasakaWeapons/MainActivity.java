package art.manguste.android.ArasakaWeapons;

import androidx.annotation.NonNull;
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
import art.manguste.android.ArasakaWeapons.Util.CatalogType;
import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.Product;
import art.manguste.android.ArasakaWeapons.Util.WeaponType;

public class MainActivity extends AppCompatActivity {

    ImageButton mCartImage;
    TextView mItemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFakeDBData();

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

        if (order.isAnyProductInCart()){
            mItemImage.setText(order.getItemsCount());
            mItemImage.setVisibility(View.VISIBLE);
        } else {
            mItemImage.setVisibility(View.INVISIBLE);
        }
    }

    private class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        @NonNull
        public Fragment getItem(int position) {
            if (position == 0) {
                return CardListFragment.newInstance(CatalogType.WEAPON);  // pos 1
            }
            return CardListFragment.newInstance(CatalogType.SERVICE); // pos 0
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

    private void initFakeDBData() {
        Product.weapons = new Product[]{
                new Product(10104, getString(R.string.weapon_gorilla_title),
                        getString(R.string.weapon_gorilla_desc),
                        getString(R.string.weapon_gorilla_desc_full),
                        2700d, CatalogType.WEAPON, R.drawable.gorilla_arms, WeaponType.CYBERWARE),
                new Product(10101,
                        getString(R.string.weapon_mantis_title),
                        getString(R.string.weapon_mantis_desc),
                        getString(R.string.weapon_mantis_desc_full),
                        3000d, CatalogType.WEAPON, R.drawable.mantis_full, WeaponType.CYBERWARE, R.drawable.mantis_icon),
                new Product(10106,
                        getString(R.string.weapon_launch_title),
                        getString(R.string.weapon_launch_desc),
                        getString(R.string.weapon_launch_desc_full),
                        2100d, CatalogType.WEAPON, R.drawable.launch_system, WeaponType.CYBERWARE),
                new Product(10102,
                        getString(R.string.weapon_tki20_title),
                        getString(R.string.weapon_tki20_desc),
                        getString(R.string.weapon_tki20_desc_full),
                        800d, CatalogType.WEAPON, R.drawable.tki_20_shingen, WeaponType.SUBMACHINE, R.drawable.tki_20_shingen_icon),
                new Product(10103,
                        getString(R.string.weapon_katana_title),
                        getString(R.string.weapon_katana_desc),
                        getString(R.string.weapon_katana_desc_full),
                        1200d, CatalogType.WEAPON, R.drawable.thermal_katana, WeaponType.MELEE, R.drawable.thermal_katana_icon),
                new Product(10105,
                        getString(R.string.weapon_m122_title),
                        getString(R.string.weapon_m122_desc),
                        getString(R.string.weapon_m122_desc_full),
                        1700d, CatalogType.WEAPON, R.drawable.cyberdyn, WeaponType.ASSAULTRIFLE, R.drawable.cyberdyn_icon),
                new Product(10107,
                        getString(R.string.weapon_kit_title),
                        getString(R.string.weapon_kit_desc),
                        getString(R.string.weapon_kit_desc_full),
                        500d, CatalogType.WEAPON, R.drawable.kit_wsa, WeaponType.AUTOPISTOL, R.drawable.kit_wsa_icon),
                new Product(10108,getString(R.string.weapon_setsuko_title),
                        getString(R.string.weapon_setsuko_desc),
                        getString(R.string.weapon_setsuko_desc_full),
                        850d, CatalogType.WEAPON, R.drawable.setsuko, WeaponType.SUBMACHINE, R.drawable.setsuko_icon)
        };

        Product.services = new Product[]{
                new Product(20001,
                        getString(R.string.service_implants_title),
                        getString(R.string.service_implants_desc),
                        getString(R.string.service_implants_desc_full),
                        2000d, CatalogType.SERVICE, R.drawable.calibration_full),
                new Product(20002,
                        getString(R.string.service_guard_title),
                        getString(R.string.service_guard_desc),
                        getString(R.string.service_guard_desc_full),
                        15000d, CatalogType.SERVICE, R.drawable.guard_full, WeaponType.NONE, R.drawable.guard_icon),
                new Product(20003,
                        getString(R.string.service_consult_title),
                        getString(R.string.service_consult_desc),
                        getString(R.string.service_consult_desc_full),
                        300d, CatalogType.SERVICE, R.drawable.home_full),
                new Product(20004,
                        getString(R.string.service_install_title),
                        getString(R.string.service_install_desc),
                        getString(R.string.service_install_desc_full),
                        1999d, CatalogType.SERVICE, R.drawable.install_full),
                new Product(20005,
                        getString(R.string.service_train_title),
                        getString(R.string.service_train_desc),
                        getString(R.string.service_train_desc_full),
                        14000d, CatalogType.SERVICE, R.drawable.protect_full),
                new Product(20006,
                        getString(R.string.service_control_title),
                        getString(R.string.service_control_desc),
                        getString(R.string.service_control_desc_full),
                        55900d, CatalogType.SERVICE, R.drawable.area_control_full, WeaponType.NONE, R.drawable.area_control_icon)
        };

    }

}