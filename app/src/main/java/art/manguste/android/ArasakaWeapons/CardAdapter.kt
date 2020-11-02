package art.manguste.android.ArasakaWeapons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import art.manguste.android.ArasakaWeapons.CardAdapter.CardViewHolder
import art.manguste.android.ArasakaWeapons.Util.CatalogType
import art.manguste.android.ArasakaWeapons.Util.WeaponType
import art.manguste.android.ArasakaWeapons.data.Product
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.product_card_view.view.*
import java.text.DecimalFormat

/**
 * Adapter for main RecyclerView with product cards
 */
class CardAdapter(private val catalogType: CatalogType,
                  private val mOnClickListener: ListItemClickListener) : RecyclerView.Adapter<CardViewHolder>() {


    interface ListItemClickListener {
        fun onListItemClick(position: Int, product: Product)
        fun onViewClick(v: View, position: Int, item: MaterialCardView, product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val cv = LayoutInflater.from(parent.context).inflate(R.layout.product_card_view, parent, false) as MaterialCardView
        return CardViewHolder(cv)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        // depends of catalogs type choose product
        holder.bind(getProduct(catalogType, position))
    }

    private fun getProduct(catalogType: CatalogType, position: Int): Product {
        return when (catalogType) {
            CatalogType.SERVICE -> Product.services[position]
            CatalogType.WEAPON -> Product.weapons[position]
        }
    }

    override fun getItemCount(): Int {
        return when (catalogType) {
            CatalogType.WEAPON -> Product.weapons.size
            CatalogType.SERVICE -> Product.services.size
        }
    }

    inner class CardViewHolder(private val item: MaterialCardView) : RecyclerView.ViewHolder(item) {

        private var product: Product? = null

        fun bind(product: Product) {
            this.product = product
            (item.findViewById<View>(R.id.productName) as TextView).text = product.title
            if (product.weaponType == WeaponType.NONE) {
                item.findViewById<View>(R.id.productType).visibility = View.GONE
            } else {
                (item.findViewById<View>(R.id.productType) as TextView).text = product.weaponType.toString()
            }

            item.description.text = product.shortDescription
            item.imageProduct.setImageResource(product.iconResourceId)
            item.price.text = DecimalFormat("##.##").format(product.price).toString()

            // on cart icon click
            item.addCartButton.setOnClickListener { v -> mOnClickListener.onViewClick(v, adapterPosition, item, product) }
            item.ll_add_position_in_cart.setOnClickListener { v -> mOnClickListener.onViewClick(v, adapterPosition, item, product) }
            itemView.setOnClickListener { mOnClickListener.onListItemClick(adapterPosition, product) }
        }
    }
}