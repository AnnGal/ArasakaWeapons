package art.manguste.android.ArasakaWeapons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import art.manguste.android.ArasakaWeapons.data.CatalogType;
import art.manguste.android.ArasakaWeapons.data.Product;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {


    private static final String TAG = CardAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;
    private CatalogType mCatalogType;

    // TODO: add ArrayList with weapon data
    // TODO: add ArrayList with service data

    public CardAdapter(CatalogType catalogType) {
        mCatalogType = catalogType;
        mOnClickListener = null;
    }

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
        MaterialCardView cv = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
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

        holder.bind(position, product);
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
            TextView goCartButton = itemView.findViewById(R.id.tv_move_to_cart_from_card);

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

            // on go to tv click
            goCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, product);
                }
            });
        }

        void bind(int listIndex, Product product) {
            this.product = product;

            if (product != null) {
                ((TextView) item.findViewById(R.id.product_name)).setText(product.getTitle());
            }
            




        }

    }
}