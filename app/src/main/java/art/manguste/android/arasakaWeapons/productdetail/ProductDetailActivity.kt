package art.manguste.android.arasakaWeapons.productdetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import art.manguste.android.arasakaWeapons.order.OrderActivity
import art.manguste.android.arasakaWeapons.R
import art.manguste.android.arasakaWeapons.core.Order
import art.manguste.android.arasakaWeapons.core.Product
import art.manguste.android.arasakaWeapons.core.WeaponType
import art.manguste.android.arasakaWeapons.databinding.ActivityMainBinding
import art.manguste.android.arasakaWeapons.databinding.ActivityProductDetailBinding
import art.manguste.android.arasakaWeapons.databinding.FragmentListBinding
import java.text.DecimalFormat

/**
 * Activity for detail info about specific product
 */
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    private lateinit var product: Product
    private var itemsCount = 1
    private var priceItem = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        //val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""

        // Get parcelable object
        val arguments = intent.extras
        if (arguments != null) {
            product = arguments.getParcelable(Product::class.java.simpleName)!!
        }
        setCardData()

        // load items count after rotation
        if (savedInstanceState != null) {
            binding.count.text = savedInstanceState.getString(Companion.SAVE_KEY_ITEM_COUNT)
        }

        // Buttons Listeners -->
        // increase items count action
        binding.actionIncreaseCount.setOnClickListener { // if we still can increase - do it
            if (Order.maxNumPerProduct > itemsCount) {
                itemsCount++
                updateItemsAndPrice()
            } else {
                Toast.makeText(baseContext, R.string.warning_max_items, Toast.LENGTH_SHORT).show()
            }
        }

        // decrease items count action
        binding.actionDecreaseCount.setOnClickListener{ // if we still can decrease - do it
            if (Order.minNumPerProduct < itemsCount) {
                itemsCount--
                updateItemsAndPrice()
            }
        }

        // add to cart and refresh cart icon action
        binding.addToCart.setOnClickListener {
            Order.placeOrderToCart(product, itemsCount)
            val message = getString(R.string.snack_message_added_to_cart, product.title)
            Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
            checkCartImage()
        }

        // move to cart activity action
        binding.cartImage.setOnClickListener { moveToOrderActivity() }
        binding.layoutToCart.setOnClickListener { moveToOrderActivity() }
        binding.countInCart.setOnClickListener { moveToOrderActivity() }
        checkCartImage()
    }

    override fun onResume() {
        checkCartImage()
        super.onResume()
    }

    /**
     * to Cart
     */
    private fun moveToOrderActivity() {
        startActivity(Intent(baseContext, OrderActivity::class.java))
    }

    /**
     * loads data from Product object, which we just received from "parcel"
     */
    private fun setCardData() {
        binding.productName.text = product.title
        if (product.weaponType == WeaponType.NONE) {
            binding.productType.visibility = View.GONE
        } else {
            binding.productType.text = product.weaponType.toString()
        }
        binding.description.text = product.fullDescription
        binding.image.setImageResource(product.imageResourceId)
        priceItem = product.price // for UI test
        updateItemsAndPrice()
    }

    /**
     * update TextView with items count and price
     */
    private fun updateItemsAndPrice() {
        binding.count.text = itemsCount.toString()
        binding.price.text = DecimalFormat("##.##").format(itemsCount * priceItem)
    }

    /**
     * Refresh cart icon after order changes
     */
    private fun checkCartImage() {
        if (Order.isAnyProductInCart) {
            binding.countInCart.text = Order.itemsCount
            binding.countInCart.visibility = View.VISIBLE
        } else {
            binding.countInCart.visibility = View.INVISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // saves how many items were selected

        outState.putString(SAVE_KEY_ITEM_COUNT, binding.count.text.toString())
    }

    companion object {
        private const val SAVE_KEY_ITEM_COUNT = "item_count"
    }

}