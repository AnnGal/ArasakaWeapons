package art.manguste.android.ArasakaWeapons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.text.DecimalFormat;
import art.manguste.android.ArasakaWeapons.Util.CatalogType;
import art.manguste.android.ArasakaWeapons.data.Product;
import art.manguste.android.ArasakaWeapons.Util.WeaponType;

/**
 * Adapter for main RecyclerView with product cards
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    //private static final String TAG = CardAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;
    private CatalogType mCatalogType;

    public CardAdapter(CatalogType catalogType, ListItemClickListener listener) {
        mCatalogType = catalogType;
        mOnClickListener = listener;
    }

    interface ListItemClickListener{
        void onListItemClick(int position, Product product);
        void onViewClick(View v, int position, MaterialCardView item, Product product);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView cv = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view, parent, false);
        return new CardViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        Product product = null;
        if (mCatalogType.equals(CatalogType.SERVICE)) {
            product = Product.services[position];
        } else if  (mCatalogType.equals(CatalogType.WEAPON)) {
            product = Product.weapons[position];
        }

        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        if (mCatalogType.equals(CatalogType.WEAPON)){
            return Product.weapons.length;
        } else if (mCatalogType.equals(CatalogType.SERVICE)){
            return Product.services.length;
        }

        return 0;
    }

    class CardViewHolder extends RecyclerView.ViewHolder  {
        private MaterialCardView item;
        private Product product;

        public CardViewHolder(@NonNull MaterialCardView itemView) {
            super(itemView);
            item = itemView;

            ImageButton addCartButton = itemView.findViewById(R.id.ib_add_position_in_cart);
            LinearLayout addCartLinearLayout =  itemView.findViewById(R.id.ll_add_position_in_cart);

            // on whole card click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onListItemClick(getAdapterPosition(), product);
                }
            });

            // on cart icon click
            addCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, product);
                }
            });
            // on the linear layout which contains cart icon click
            addCartLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, product);
                }
            });

        }

        void bind(Product product) {
            this.product = product;

            if (product != null) {
                ((TextView) item.findViewById(R.id.product_name)).setText(product.getTitle());
                if (product.getWeaponType().equals(WeaponType.NONE)) {
                    (item.findViewById(R.id.product_type)).setVisibility(View.GONE);
                } else {
                    ((TextView) item.findViewById(R.id.product_type)).setText(product.getWeaponType().toString());
                }
                ((TextView) item.findViewById(R.id.product_description)).setText(product.getShortDescription());
                ((ImageView) item.findViewById(R.id.product_image)).setImageResource(product.getIconResourceId());
                ((TextView) item.findViewById(R.id.price)).setText(String.valueOf(new DecimalFormat("##.##").format(product.getPrice())));
            }
        }

    }
}