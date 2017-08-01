package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.skgroup4.android.storekarrier.R;

/**
 * Created by Seo on 2017-07-18.
 */

public class SearchFragment extends Fragment {
    public SearchFragment(){

    }
    private Button recommendBtn;
    private Button storeBtn;
    private Button placeBtn;
    private ImageView collapseBtn;
    private AppBarLayout appBarLayout;
    private Toolbar mToolbar;
    private LinearLayout searchTabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //검색 Fragment에서 추천/보관소/장소 각 버튼들
        recommendBtn = (Button) view.findViewById(R.id.recommend_btn);
        storeBtn = (Button) view.findViewById(R.id.store_btn);
        placeBtn = (Button) view.findViewById(R.id.place_btn);
        collapseBtn = (ImageView) view.findViewById(R.id.collapsing_btn);
        //툴바 expand collapse

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_layout);
        appBarLayout.setExpanded(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("OFFSET" , verticalOffset + "");

                if(verticalOffset >=-400){
                    mToolbar.setVisibility(View.INVISIBLE);
                }else if (verticalOffset < -400) {
                    mToolbar.setVisibility(View.VISIBLE);
                }
            }

        });



        //리스너 등록
        mToolbar.setOnClickListener(BtnClickListener);
        recommendBtn.setOnClickListener(BtnClickListener);
        storeBtn.setOnClickListener(BtnClickListener);
        placeBtn.setOnClickListener(BtnClickListener);
        collapseBtn.setOnClickListener(BtnClickListener);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //처음 검색 Fragment에 추천 Fragment 붙이기
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.search_container, new RecommendFragment());
        fragmentTransaction.commit();

    }

    // 추천 보관소 장소 버튼에 따라 Fragment를 replace하는 이벤트
    public View.OnClickListener BtnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Fragment fragment;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            switch(v.getId()){
                case R.id.recommend_btn:
                    fragment = new RecommendFragment();
                    fragmentTransaction.replace(R.id.search_container, fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.store_btn:
                    fragment = new StoreFragment();
                    fragmentTransaction.replace(R.id.search_container, fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.place_btn:
                    fragment = new PlaceFragment();
                    fragmentTransaction.replace(R.id.search_container, fragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.toolbar:
                    appBarLayout.setExpanded(true);
                    break;
                case R.id.collapsing_btn:
                    appBarLayout.setExpanded(false);
                    break;
            }
        }
    };
}
