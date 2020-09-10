package art.manguste.android.ArasakaWeapons.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import art.manguste.android.ArasakaWeapons.CardAdapter;
import art.manguste.android.ArasakaWeapons.R;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    final private OrderClickListener mOnClickListener;

    public OrderAdapter(OrderClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public interface OrderClickListener{
        void onViewClick(View v, int position, MaterialCardView item, Product product);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView cv = (MaterialCardView)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_view, parent, false);

        return new OrderViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        //holder.bind(Order.getCurrentOrder().ordersList[position]);
        LinkedHashMap<Product, Integer> order = Order.getCurrentOrder().getOrdersMap();

        int i = Order.getCurrentOrder().getOrderSize() -1;
        for (Map.Entry<Product, Integer> pair : order.entrySet()) {
            if (i == position) {
                holder.bind(pair.getKey(), pair.getValue());
                break;
            }
            i--;
        }
    }

    @Override
    public int getItemCount() {
        return Order.getCurrentOrder().getOrderSize();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView item;
        private Product product;
        private Integer count;

        public OrderViewHolder(@NonNull MaterialCardView itemView) {
            super(itemView);
            item = itemView;


        }

        void bind(Product product, Integer count){
            this.product = product;
            this.count = count;

            ((TextView) item.findViewById(R.id.product_name)).setText(product.getTitle());
            ((TextView) item.findViewById(R.id.tv_items_count)).setText(String.valueOf(count));
        }


    }
}
