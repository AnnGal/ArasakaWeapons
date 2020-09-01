package art.manguste.android.ArasakaWeapons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import art.manguste.android.ArasakaWeapons.data.CatalogType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceListFragment extends Fragment {
    // TODO: make service fragment

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ServiceListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiceFragment.
     */
    public static ServiceListFragment newInstance(String param1, String param2) {
        ServiceListFragment fragment = new ServiceListFragment();
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
            StoreCardAdapter adapter = new StoreCardAdapter(CatalogType.SERVICE);
            recyclerView.setAdapter(adapter);
            // connect data and view
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
            recyclerView.setLayoutManager(layoutManager);

            //
/*        adapter.setListener(new CaptionedImageAdapter.Listener() {
            @Override
            public void onClick(int position) {
                //Intent intent = new Intent(getActivity(), DetailActivity.class);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ID, position);
                getActivity().startActivity(intent);
            }
        });*/

            return recyclerView;
    }
}