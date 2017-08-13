package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.SpacesItemDecoration;
import com.skgroup4.android.storekarrier.adpater.PlaceAdapter;
import com.skgroup4.android.storekarrier.item.PlaceItem;
import com.skgroup4.android.storekarrier.item.RepoSpot;

import java.util.ArrayList;

/**
 * Created by Seo on 2017-07-18.
 */

public class PlaceFragment extends Fragment {
    public PlaceFragment(){

    }
    private RecyclerView placeRecyclerView;
    private PlaceAdapter placeAdapter;
    private ArrayList<PlaceItem> placeItemsList;
    private ArrayList<RepoSpot> spotList;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place , container,false);


        placeRecyclerView = (RecyclerView) view.findViewById(R.id.place_recycler);
        placeRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity() , 2);
        placeRecyclerView.setLayoutManager(layoutManager);
        placeRecyclerView.scrollToPosition(0);
        if(spotList!=null){
            placeAdapter = new PlaceAdapter(getActivity() , spotList , PlaceAdapter.PLACE_CODE , true);
        }else{
            placeAdapter = new PlaceAdapter(getActivity(),placeItemsList , PlaceAdapter.PLACE_CODE);
        }

        placeRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
        placeRecyclerView.setAdapter(placeAdapter);
        placeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null){
            spotList = (ArrayList<RepoSpot>) bundle.getSerializable("spotList");
        }
        else{
            initData();
        }

    }

    private void initData(){
        placeItemsList = new ArrayList<>();
        placeItemsList.add(new PlaceItem(R.drawable.default_img, "name"));
        placeItemsList.add(new PlaceItem(R.drawable.default_img , "name2"));
        placeItemsList.add(new PlaceItem(R.drawable.default_img, "name3"));
        placeItemsList.add(new PlaceItem(R.drawable.default_img, "name4"));
    }


}
