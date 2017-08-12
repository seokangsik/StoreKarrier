package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.adpater.PlaceAdapter;
import com.skgroup4.android.storekarrier.adpater.StoreAdapter;
import com.skgroup4.android.storekarrier.item.PlaceItem;
import com.skgroup4.android.storekarrier.item.StoreItem;

import java.util.ArrayList;

import static com.skgroup4.android.storekarrier.adpater.StoreAdapter.RECOMMEND_CODE;

/**
 * Created by Seo on 2017-07-18.
 */

public class RecommendFragment extends Fragment {
    public RecommendFragment(){

    }


    private RecyclerView storeRecyclerView;
    private StoreAdapter storeAdapter;
    private ArrayList<StoreItem> storeItemList;
    private TextView showAllStore;

    private RecyclerView placeRecyclerView;
    private PlaceAdapter placeAdapter;
    private ArrayList<PlaceItem> placeItemList;
    private TextView showAllPlace;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend,container,false);
        showAllStore = (TextView) view.findViewById(R.id.show_all_store_txt);
        showAllPlace = (TextView) view.findViewById(R.id.show_all_place_txt);
        showAllStore.setOnClickListener(BtnListener);
        showAllPlace.setOnClickListener(BtnListener);

        //보관소 recycler view 설정
        storeRecyclerView = (RecyclerView) view.findViewById(R.id.recommend_store_recycler);
        storeRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity() , LinearLayoutManager.HORIZONTAL,false);
        storeRecyclerView.setLayoutManager(layoutManager);
        storeRecyclerView.scrollToPosition(0);
        storeAdapter = new StoreAdapter(getActivity(),storeItemList,RECOMMEND_CODE);
        storeRecyclerView.setAdapter(storeAdapter);
        storeRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //장소 recycler view 설정
        placeRecyclerView = (RecyclerView) view.findViewById(R.id.recommend_place_recycler);
        placeRecyclerView.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getActivity() , LinearLayoutManager.HORIZONTAL,false);
        placeRecyclerView.setLayoutManager(layoutManager2);
        placeRecyclerView.scrollToPosition(0);
        placeAdapter = new PlaceAdapter(getActivity() , placeItemList ,RECOMMEND_CODE);
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
        //보관소 임시 데이터
        storeItemList = new ArrayList<>();
        storeItemList.add(new StoreItem(R.drawable.default_img,"price" , "name"));
        storeItemList.add(new StoreItem(R.drawable.default_img,"price2" , "name2"));

        //장소 임시데이터
        placeItemList = new ArrayList<>();
        placeItemList.add(new PlaceItem(R.drawable.default_img,"name"));
        placeItemList.add(new PlaceItem(R.drawable.default_img, "name2"));
    }
    View.OnClickListener BtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            switch (v.getId()){
                case R.id.show_all_store_txt:
                    fragment = new StoreFragment();
                    fragmentTransaction.replace(R.id.search_container, fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.show_all_place_txt:
                    fragment = new PlaceFragment();
                    fragmentTransaction.replace(R.id.search_container, fragment);
                    fragmentTransaction.commit();
                    break;
            }
        }
    };

}
