package art.manguste.android.arasakaWeapons.productslist

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
import art.manguste.android.arasakaWeapons.*
import art.manguste.android.arasakaWeapons.productslist.ProductAdapter.ListItemClickListener
import art.manguste.android.arasakaWeapons.core.CatalogType
import art.manguste.android.arasakaWeapons.core.Order
import art.manguste.android.arasakaWeapons.core.Product
import art.manguste.android.arasakaWeapons.databinding.FragmentListBinding
import art.manguste.android.arasakaWeapons.order.OrderActivity
import art.manguste.android.arasakaWeapons.productdetail.ProductDetailActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsListFragment : Fragment(), ListItemClickListener {

    // ViewBinding
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        //val recyclerView = inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        mViewGroup = container!!

        // add adapter
        val adapter = ProductAdapter(catalogType, this)
        binding.recyclerView.adapter = adapter

        // connect data and view
        val layoutManager = GridLayoutManager(activity, 1)
        binding.recyclerView.layoutManager = layoutManager

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * After click on whole ViewCard from RecyclerView
     */
    override fun onListItemClick(position: Int, product: Product) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra(Product::class.java.simpleName, product)
        startActivity(intent)
    }

    /**
     * After click on particular view on viewCard
     */
    override fun onViewClick(v: View, position: Int, item: MaterialCardView, product: Product) {
        // add item into cart
        if (v.id == R.id.addCartButton || v.id == R.id.addPositionInCartLayout) {
            // add item and refresh cart icon
            Order.placeOrderToCart(product, 1)
            (context as MainActivity).checkCartImage()

            // create snackbar
            val snackMessage = getString(R.string.snack_message_added_to_cart, product.title)
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
        fun newInstance(catalogType: CatalogType): ProductsListFragment {
            val fragment = ProductsListFragment()
            // put params
            val args = Bundle()
            args.putString(CATALOG_TYPE, catalogType.toString())
            fragment.arguments = args
            return fragment
        }
    }
}