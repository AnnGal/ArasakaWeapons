package art.manguste.android.ArasakaWeapons;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import art.manguste.android.ArasakaWeapons.data.CatalogType;

public class StoreCardAdapter extends RecyclerView.Adapter<StoreCardAdapter.CardViewHolder> {


    private static final String TAG = StoreCardAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;
    private CatalogType mCatalogType;

    // TODO: add ArrayList with weapon data
    // TODO: add ArrayList with service data

    public StoreCardAdapter(CatalogType catalogType) {
        mCatalogType = catalogType;
        mOnClickListener = null;
    }

    interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView cv = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store, parent, false);
        return new CardViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        MaterialCardView cardView = holder.item;

        // set data
        TextView productName = cardView.findViewById(R.id.product_name);
        productName.setText(mCatalogType.toString());


/*        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });*/
        // TODO: bind in ViewHolder
        // TODO: make Listener alive
    }

    @Override
    public int getItemCount() {
        //TODO: replace with ArrayLength
        if (mCatalogType.equals(CatalogType.WEAPON)){
            return 8;
        } else  if (mCatalogType.equals(CatalogType.SERVICE)){
            return 5;
        }
        return 0;
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MaterialCardView item;

        public CardViewHolder(@NonNull MaterialCardView itemView) {
            super(itemView);
            item = itemView;
        }

        @Override
        public void onClick(View v) {

        }

    }
}
