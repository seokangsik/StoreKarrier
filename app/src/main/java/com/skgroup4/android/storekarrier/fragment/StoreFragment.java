package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.adpater.StoreAdapter;
import com.skgroup4.android.storekarrier.item.StoreItem;

import java.util.ArrayList;

import static com.skgroup4.android.storekarrier.adpater.StoreAdapter.STORECODE;

/**
 * Created by Seo on 2017-07-18.
 */

public class StoreFragment extends Fragment {
    public StoreFragment(){

    }

    private RecyclerView storeRecyclerView;
    private StoreAdapter storeAdapter;
    private ArrayList<StoreItem> storeItemList;

    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store,container,false);
        //보관소 recycler view 설정
        storeRecyclerView = (RecyclerView) view.findViewById(R.id.store_recycler);
        storeRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL,false);
        storeRecyclerView.setLayoutManager(layoutManager);
        storeRecyclerView.scrollToPosition(0);
        storeAdapter = new StoreAdapter(getActivity(),storeItemList, STORECODE);
        storeRecyclerView.setAdapter(storeAdapter);
        storeRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
        //보관소 임시 데이터
        storeItemList = new ArrayList<>();
        storeItemList.add(new StoreItem(R.drawable.default_img,"price" , "name"));
        storeItemList.add(new StoreItem(R.drawable.default_img,"price2" , "name2"));


    }
}
