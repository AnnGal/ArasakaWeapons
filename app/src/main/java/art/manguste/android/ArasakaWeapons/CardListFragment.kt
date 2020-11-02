package art.manguste.android.ArasakaWeapons

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import art.manguste.android.ArasakaWeapons.CardAdapter.ListItemClickListener
import art.manguste.android.ArasakaWeapons.Util.CatalogType
import art.manguste.android.ArasakaWeapons.data.Order
import art.manguste.android.ArasakaWeapons.data.Product
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.product_card_view.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CardListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardListFragment : Fragment(), ListItemClickListener {
    private lateinit var catalogType: CatalogType
    private lateinit var mViewGroup: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get params
        if (arguments != null) {
            catalogType = CatalogType.valueOf(arguments!!.getString(CATALOG_TYPE)!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        mViewGroup = container!!

        // add adapter
        val adapter = CardAdapter(catalogType, this)
        recyclerView.adapter = adapter

        // connect data and view
        val layoutManager = GridLayoutManager(activity, 1)
        recyclerView.layoutManager = layoutManager
        return recyclerView
    }

    /**
     * After click on whole ViewCard from RecyclerView
     */
    override fun onListItemClick(position: Int, product: Product) {
        val intent = Intent(context, CardDetailActivity::class.java)
        intent.putExtra(Product::class.java.simpleName, product)
        startActivity(intent)
    }

    /**
     * After click on particular view on viewCard
     */
    override fun onViewClick(v: View, position: Int, item: MaterialCardView, product: Product) {
        // add item into cart
        if (v.id == R.id.addCartButton || v.id == R.id.ll_add_position_in_cart) {
            // add item and refresh cart icon
            Order.placeOrderToCart(product, 1)
            (context as MainActivity).checkCartImage()

            // create snackbar
            val snackMessage = getString(R.string.snack_message_added_to_cart, item.productName.text)
            val snackbar = Snackbar.make(mViewGroup, snackMessage, Snackbar.LENGTH_LONG);

            //change snackbar colors
            snackbar.apply {
                setAction(getString(R.string.go_to_cart)) { startActivity(Intent(context, OrderActivity::class.java)) }
                setActionTextColor(resources.getColor(R.color.colorArasakaBackground))
                setBackgroundTint(resources.getColor(R.color.colorDarkBackground))
            }
            //snackbar message appearance
            val tvSnackbar = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
            tvSnackbar.apply {
                setTextColor(resources.getColor(R.color.colorArasakaRed))
                typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }

            snackbar.show()
        // go to the cart activity
        } else if (v.id == R.id.tv_move_to_cart_from_card) {
            // move to cart from card view. Not active right now.
            startActivity(Intent(context, OrderActivity::class.java))
        }
    }

    companion object {
        private const val CATALOG_TYPE = "catalog_type"

        /**
         * Factory method to create a new instance of fragment
         *
         * @param catalogType sets fragment type.
         * @return a the new instance of fragment StoreListFragment.
         */
        fun newInstance(catalogType: CatalogType): CardListFragment {
            val fragment = CardListFragment()
            // put params
            val args = Bundle()
            args.putString(CATALOG_TYPE, catalogType.toString())
            fragment.arguments = args
            return fragment
        }
    }
}