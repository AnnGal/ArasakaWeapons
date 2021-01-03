package art.manguste.android.arasakaWeapons.productslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import art.manguste.android.arasakaWeapons.R
import art.manguste.android.arasakaWeapons.productslist.ProductAdapter.CardViewHolder
import art.manguste.android.arasakaWeapons.core.CatalogType
import art.manguste.android.arasakaWeapons.core.Product
import art.manguste.android.arasakaWeapons.core.WeaponType
import art.manguste.android.arasakaWeapons.databinding.ProductCardViewBinding
import com.google.android.material.card.MaterialCardView
import java.text.DecimalFormat

/**
 * Adapter for main RecyclerView with product cards
 */
class ProductAdapter(private val catalogType: CatalogType,
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
        private val binding = ProductCardViewBinding.bind(item)

        fun bind(product: Product) {

            this.product = product
            (item.findViewById<View>(R.id.productName) as TextView).text = product.title
            if (product.weaponType == WeaponType.NONE) {
                item.findViewById<View>(R.id.productType).visibility = View.GONE
            } else {
                (item.findViewById<View>(R.id.productType) as TextView).text = product.weaponType.toString()
            }

            binding.description.text = product.shortDescription
            binding.imageProduct.setImageResource(product.iconResourceId)
            binding.price.text = DecimalFormat("##.##").format(product.price).toString()

            // on cart icon click
            binding.addCartButton.setOnClickListener { v -> mOnClickListener.onViewClick(v, adapterPosition, item, product) }
            binding.addPositionInCartLayout.setOnClickListener { v -> mOnClickListener.onViewClick(v, adapterPosition, item, product) }
            itemView.setOnClickListener { mOnClickListener.onListItemClick(adapterPosition, product) }
        }
    }
}