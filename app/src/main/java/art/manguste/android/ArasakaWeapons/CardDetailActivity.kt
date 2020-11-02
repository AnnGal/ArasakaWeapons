package art.manguste.android.ArasakaWeapons

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import art.manguste.android.ArasakaWeapons.Util.WeaponType
import art.manguste.android.ArasakaWeapons.data.Order
import art.manguste.android.ArasakaWeapons.data.Product
import kotlinx.android.synthetic.main.activity_card_detail.*
import java.text.DecimalFormat

/**
 * Activity for detail info about specific product
 */
class CardDetailActivity : AppCompatActivity() {

    private val SAVE_KEY_ITEM_COUNT = "item_count"
    private lateinit var product: Product
    private var itemsCount = 1
    private var priceItem = 0.0
/*    var mCartImage: ImageButton? = null
    var mItemsCount: TextView? = null
    var mProductName: TextView? = null
    var mProductType: TextView? = null
    var mProductDescription: TextView? = null
    var mImage: ImageView? = null
    var mPrice: TextView? = null
    var mItemImage: TextView? = null
    var mAddToCart: TextView? = null
    var mIncCount: ImageButton? = null
    var mDecCount: ImageButton? = null

*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        // toolbar and return button
        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = ""

        // UI
/*        mCartImage = findViewById(R.id.cartImage)
        mItemsCount = findViewById(R.id.itemsCount)
        mProductName = findViewById(R.id.productName)
        mProductType = findViewById(R.id.productType)
        mProductDescription = findViewById(R.id.description)
        mPrice = findViewById(R.id.price)
        mImage = findViewById(R.id.image)
        mItemImage = findViewById(R.id.countInCart)
        mAddToCart = findViewById(R.id.addToCart)
        mIncCount = findViewById(R.id.actionIncreaseCount)
        mDecCount = findViewById(R.id.actionDecreaseCount)*/

        // Get parcelable object
        val arguments = intent.extras
        if (arguments != null) {
            product = arguments.getParcelable(Product::class.java.simpleName)!!
        }
        setCardData()

        // load items count after rotation
        if (savedInstanceState != null) {
            count.text = savedInstanceState.getString(SAVE_KEY_ITEM_COUNT)
        }

        // Buttons Listeners -->
        // increase items count action
        actionIncreaseCount.setOnClickListener { // if we still can increase - do it
            if (Order.maxNumPerProduct > itemsCount) {
                itemsCount++
                updateItemsAndPrice()
            } else {
                Toast.makeText(baseContext, R.string.warning_max_items, Toast.LENGTH_SHORT).show()
            }
        }

        // decrease items count action
        actionDecreaseCount.setOnClickListener{ // if we still can decrease - do it
            if (Order.minNumPerProduct < itemsCount) {
                itemsCount--
                updateItemsAndPrice()
            }
        }

        // add to cart and refresh cart icon action
        addToCart.setOnClickListener {
            Order.placeOrderToCart(product, itemsCount)
            val message = getString(R.string.snack_message_added_to_cart, product.title)
            Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
            checkCartImage()
        }

        // move to cart activity action
        cartImage.setOnClickListener { moveToOrderActivity() }
        layoutToCart.setOnClickListener { moveToOrderActivity() }
        countInCart.setOnClickListener { moveToOrderActivity() }
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
        productName.text = product.title
        if (product.weaponType == WeaponType.NONE) {
            productType.visibility = View.GONE
        } else {
            productType.text = product.weaponType.toString()
        }
        description.text = product.fullDescription
        image.setImageResource(product.imageResourceId)
        priceItem = product.price // for UI test
        updateItemsAndPrice()
    }

    /**
     * updates TextView with items count and price
     */
    private fun updateItemsAndPrice() {
        count.text = itemsCount.toString()
        price.text = DecimalFormat("##.##").format(itemsCount * priceItem)
    }

    /**
     * Refresh cart icon after order changes
     */
    private fun checkCartImage() {

        if (Order.isAnyProductInCart) {
            countInCart.text = Order.itemsCount
            countInCart.visibility = View.VISIBLE
        } else {
            countInCart.visibility = View.INVISIBLE
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
        // saves how many items selected
        outState.putString(SAVE_KEY_ITEM_COUNT, count.text.toString())
    }

    companion object {

    }
}