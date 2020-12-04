package art.manguste.android.arasakaWeapons

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import art.manguste.android.arasakaWeapons.util.CatalogType
import art.manguste.android.arasakaWeapons.util.WeaponType
import art.manguste.android.arasakaWeapons.data.Order
import art.manguste.android.arasakaWeapons.data.Product
import art.manguste.android.arasakaWeapons.ui.CardListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFakeDBData()

        //set scroller
        viewPager.adapter = SectionPagerAdapter(supportFragmentManager)
        //set tabLayout
        tabLayout.setupWithViewPager(viewPager)

        // move to cart activity
        cartImage.setOnClickListener { moveToTheCart() }
        countInCart.setOnClickListener { moveToTheCart() }
        layoutToCart.setOnClickListener { moveToTheCart() }

        checkCartImage()
    }

    override fun onResume() {
        checkCartImage()
        super.onResume()
    }

    private fun moveToTheCart(){
        startActivity( Intent(this, OrderActivity::class.java))
    }

    fun checkCartImage() {
        println("isAnyProductInCart = ${Order.isAnyProductInCart}")
        if (Order.isAnyProductInCart) {
            countInCart.text = Order.itemsCount
            countInCart.visibility = View.VISIBLE
        } else {
            countInCart.visibility = View.INVISIBLE
        }
    }

    private inner class SectionPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> CardListFragment.newInstance(CatalogType.WEAPON)
                1 -> CardListFragment.newInstance(CatalogType.SERVICE)
                else -> throw Exception("Unregistered tab")
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return resources.getText(R.string.weapons_tab)
                1 -> return resources.getText(R.string.services_tab)
            }
            return null
        }
    }

    private fun initFakeDBData() {
        Product.weapons = arrayOf(
                Product(10104, getString(R.string.weapon_gorilla_title),
                        getString(R.string.weapon_gorilla_desc),
                        getString(R.string.weapon_gorilla_desc_full),
                        2700.0, CatalogType.WEAPON, R.drawable.gorilla_arms, WeaponType.CYBERWARE, R.drawable.gorilla_arms),
                Product(10101,
                        getString(R.string.weapon_mantis_title),
                        getString(R.string.weapon_mantis_desc),
                        getString(R.string.weapon_mantis_desc_full),
                        3000.0, CatalogType.WEAPON, R.drawable.mantis_full, WeaponType.CYBERWARE, R.drawable.mantis_icon),
                Product(10106,
                        getString(R.string.weapon_launch_title),
                        getString(R.string.weapon_launch_desc),
                        getString(R.string.weapon_launch_desc_full),
                        2100.0, CatalogType.WEAPON, R.drawable.launch_system, WeaponType.CYBERWARE, R.drawable.launch_system),
                Product(10102,
                        getString(R.string.weapon_tki20_title),
                        getString(R.string.weapon_tki20_desc),
                        getString(R.string.weapon_tki20_desc_full),
                        800.0, CatalogType.WEAPON, R.drawable.tki_20_shingen, WeaponType.SUBMACHINE, R.drawable.tki_20_shingen_icon),
                Product(10103,
                        getString(R.string.weapon_katana_title),
                        getString(R.string.weapon_katana_desc),
                        getString(R.string.weapon_katana_desc_full),
                        1200.0, CatalogType.WEAPON, R.drawable.thermal_katana, WeaponType.MELEE, R.drawable.thermal_katana_icon),
                Product(10105,
                        getString(R.string.weapon_m122_title),
                        getString(R.string.weapon_m122_desc),
                        getString(R.string.weapon_m122_desc_full),
                        1700.0, CatalogType.WEAPON, R.drawable.cyberdyn, WeaponType.ASSAULTRIFLE, R.drawable.cyberdyn_icon),
                Product(10107,
                        getString(R.string.weapon_kit_title),
                        getString(R.string.weapon_kit_desc),
                        getString(R.string.weapon_kit_desc_full),
                        500.0, CatalogType.WEAPON, R.drawable.kit_wsa, WeaponType.AUTOPISTOL, R.drawable.kit_wsa_icon),
                Product(10108, getString(R.string.weapon_setsuko_title),
                        getString(R.string.weapon_setsuko_desc),
                        getString(R.string.weapon_setsuko_desc_full),
                        850.0, CatalogType.WEAPON, R.drawable.setsuko, WeaponType.SUBMACHINE, R.drawable.setsuko_icon)
        )
        Product.services = arrayOf(
                Product(20001,
                        getString(R.string.service_implants_title),
                        getString(R.string.service_implants_desc),
                        getString(R.string.service_implants_desc_full),
                        2000.0, CatalogType.SERVICE, R.drawable.calibration_full, iconResourceId=R.drawable.calibration_full),
                Product(20002,
                        getString(R.string.service_guard_title),
                        getString(R.string.service_guard_desc),
                        getString(R.string.service_guard_desc_full),
                        15000.0, CatalogType.SERVICE, R.drawable.guard_full, WeaponType.NONE, R.drawable.guard_icon),
                Product(20003,
                        getString(R.string.service_consult_title),
                        getString(R.string.service_consult_desc),
                        getString(R.string.service_consult_desc_full),
                        300.0, CatalogType.SERVICE, R.drawable.home_full, iconResourceId=R.drawable.home_full),
                Product(20004,
                        getString(R.string.service_install_title),
                        getString(R.string.service_install_desc),
                        getString(R.string.service_install_desc_full),
                        1999.0, CatalogType.SERVICE, R.drawable.install_full, iconResourceId=R.drawable.install_full),
                Product(20005,
                        getString(R.string.service_train_title),
                        getString(R.string.service_train_desc),
                        getString(R.string.service_train_desc_full),
                        14000.0, CatalogType.SERVICE, R.drawable.protect_full, iconResourceId=R.drawable.protect_full),
                Product(20006,
                        getString(R.string.service_control_title),
                        getString(R.string.service_control_desc),
                        getString(R.string.service_control_desc_full),
                        55900.0, CatalogType.SERVICE, R.drawable.area_control_full, iconResourceId=R.drawable.area_control_icon)
        )
    }
}