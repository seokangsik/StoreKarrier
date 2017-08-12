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
import com.skgroup4.android.storekarrier.adpater.PlaceAdapter;
import com.skgroup4.android.storekarrier.item.PlaceItem;

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
        placeAdapter = new PlaceAdapter(getActivity(),placeItemsList , 10003);
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
        initData();
    }

    private void initData(){
        placeItemsList = new ArrayList<>();
        placeItemsList.add(new PlaceItem(R.drawable.default_img, "name"));
        placeItemsList.add(new PlaceItem(R.drawable.default_img , "name2"));
        placeItemsList.add(new PlaceItem(R.drawable.default_img, "name3"));
        placeItemsList.add(new PlaceItem(R.drawable.default_img, "name4"));
    }


}
