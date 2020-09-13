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

import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.Product;
import art.manguste.android.ArasakaWeapons.data.ProductInOrder;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    final private OrderClickListener mOnClickListener;

    public OrderAdapter(OrderClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public interface OrderClickListener{
        void onViewClick(View v, int position, MaterialCardView item, ProductInOrder product);
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
        holder.bind(Order.getCurrentOrder().getProductList().get(position));
/*        LinkedHashMap<Product, Integer> order = Order.getCurrentOrder().getOrdersMap();

        int i = Order.getCurrentOrder().getOrderSize() -1;
        for (Map.Entry<Product, Integer> pair : order.entrySet()) {
            if (i == position) {
                holder.bind(pair.getKey(), pair.getValue());
                break;
            }
            i--;
        }*/
    }



    @Override
    public int getItemCount() {
        return Order.getCurrentOrder().getOrderSize();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView item;
        private ProductInOrder productInOrder;


        public OrderViewHolder(@NonNull MaterialCardView itemView) {
            super(itemView);
            item = itemView;

            ImageButton addButton = itemView.findViewById(R.id.action_decrease_count);
            ImageButton removeButton = itemView.findViewById(R.id.action_increase_count);

            LinearLayout removeProductLayout =  itemView.findViewById(R.id.ll_action_delete_from_cart);
            ImageButton removeProductButton = itemView.findViewById(R.id.action_delete_from_cart);

            // action - decrease items count
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, productInOrder);
                }
            });

            // action - increase items count
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, productInOrder);
                }
            });

            // on the linear layout which contains cart icon click
            removeProductLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, productInOrder);
                }
            });

            // action - remove order from cart
            removeProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnClickListener != null)
                        mOnClickListener.onViewClick(v, getAdapterPosition(), item, productInOrder);
                }
            });

        }

        void bind(ProductInOrder productInOrder){
            this.productInOrder = productInOrder;

            ((TextView) item.findViewById(R.id.product_name)).setText(productInOrder.getProduct().getTitle());
            ((TextView) item.findViewById(R.id.tv_items_count)).setText(String.valueOf(productInOrder.getItemsInOrder()));
        }


    }
}
