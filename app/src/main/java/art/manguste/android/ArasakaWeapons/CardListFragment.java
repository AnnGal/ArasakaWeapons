package art.manguste.android.ArasakaWeapons;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import art.manguste.android.ArasakaWeapons.Util.CatalogType;
import art.manguste.android.ArasakaWeapons.data.Order;
import art.manguste.android.ArasakaWeapons.data.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardListFragment extends Fragment
        implements CardAdapter.ListItemClickListener {

    private static final String CATALOG_TYPE = "catalog_type";

    private CatalogType catalogType;
    private Context mContext;

    private ViewGroup mViewGroup;

    public CardListFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of fragment
     *
     * @param catalogType sets fragment type.
     * @return a the new instance of fragment StoreListFragment.
     */
    public static CardListFragment newInstance(CatalogType catalogType){
        CardListFragment fragment = new CardListFragment();
        // put params
        Bundle args = new Bundle();
        args.putString(CATALOG_TYPE, String.valueOf(catalogType));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get params
        if (getArguments() != null) {
            catalogType = CatalogType.valueOf(getArguments().getString(CATALOG_TYPE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);

        mViewGroup = container;
        mContext = getContext();

        // add adapter
        CardAdapter adapter = new CardAdapter(catalogType, this);
        recyclerView.setAdapter(adapter);

        // connect data and view
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);

        return recyclerView;
    }

    /**
     * After click on whole ViewCard from RecyclerView
     **/
    @Override
    public void onListItemClick(int position, Product product) {
        Intent intent = new Intent(mContext, CardDetailActivity.class);
        intent.putExtra(Product.class.getSimpleName(), product);
        startActivity(intent);
    }

    /**
     * After click on particular view on viewCard
     **/
    @Override
    public void onViewClick(View v, int position, MaterialCardView item, Product product) {
        if (v.getId() == R.id.ib_add_position_in_cart || v.getId() == R.id.ll_add_position_in_cart){
            // add item in cart

            // Change visibility
            //item.findViewById(R.id.ib_add_position_in_cart).setVisibility(View.GONE);
            //item.findViewById(R.id.ll_add_position_in_cart).setVisibility(View.GONE);
            //item.findViewById(R.id.tv_move_to_cart_from_card).setVisibility(View.VISIBLE);

            // add item and refresh cart icon
            Order.getCurrentOrder().placeOrderToCart(product, 1);
            ((MainActivity) mContext).CheckCartImage();

            // Snackbar interaction with user
            String snackMessage = getString(R.string.snack_message_added_to_cart, ((TextView) item.findViewById(R.id.product_name)).getText());
            Snackbar snackbar = Snackbar
                    .make(mViewGroup, snackMessage, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.go_to_cart), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), OrderActivity.class));
                        }
                    });
            //change snackbar colors
            snackbar.setActionTextColor(getResources().getColor(R.color.colorArasakaBackground));
            snackbar.setBackgroundTint(getResources().getColor(R.color.colorDarkBackground));
            //snackbar message appearance
            TextView tvSnackbar = ((TextView) snackbar.getView().findViewById(R.id.snackbar_text));
            tvSnackbar.setTextColor(getResources().getColor(R.color.colorArasakaRed));
            tvSnackbar.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            snackbar.show();

        } else if (v.getId() == R.id.tv_move_to_cart_from_card){
            // move to cart from card view. Not active right now.
            Intent intent = new Intent(mContext, OrderActivity.class);
            startActivity(intent);
        }
    }

}