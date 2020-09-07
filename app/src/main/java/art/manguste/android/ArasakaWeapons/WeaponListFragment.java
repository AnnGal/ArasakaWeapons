package art.manguste.android.ArasakaWeapons;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import art.manguste.android.ArasakaWeapons.data.CatalogType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeaponListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeaponListFragment extends Fragment
        implements CardAdapter.ListItemClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Toast mToast;

    public WeaponListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeaponFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeaponListFragment newInstance(String param1, String param2) {
        WeaponListFragment fragment = new WeaponListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        // add adapter
        CardAdapter adapter = new CardAdapter(CatalogType.WEAPON, this);
        recyclerView.setAdapter(adapter);
        // connect data and view
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);

        return recyclerView;
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(getContext(), CardDetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, CatalogType.WEAPON+"_#"+String.valueOf(position));
        startActivity(intent);
    }

    @Override
    public void onViewClick(View v, int position) {
        if(v.getId() == R.id.ib_add_position_from_card){
            // Do your stuff here
            Intent intent = new Intent(getContext(), CartActivity.class);
            startActivity(intent);
        }
    }

}