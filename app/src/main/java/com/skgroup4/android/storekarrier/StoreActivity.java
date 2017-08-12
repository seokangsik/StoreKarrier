package com.skgroup4.android.storekarrier;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.skgroup4.android.storekarrier.adpater.PlaceAdapter;
import com.skgroup4.android.storekarrier.item.PlaceItem;

import java.util.ArrayList;

import static com.skgroup4.android.storekarrier.adpater.StoreAdapter.RECOMMEND_CODE;

/**
 * Created by Seo on 2017-07-27.
 */

public class StoreActivity extends AppCompatActivity implements OnMapReadyCallback {


    private LinearLayout message;
    private Button reserveBtn;
    private LinearLayout storeToolbar;
    private RecyclerView placeRecyclerView;
    private LinearLayoutManager layoutManager;
    private PlaceAdapter placeAdapter;
    private ArrayList<PlaceItem> placeItemList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        storeToolbar = (LinearLayout) findViewById(R.id.store_bottom_toolbar);
        storeToolbar.bringToFront();
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.store_map);
        mapFragment.getMapAsync(this);

        initData();

        placeRecyclerView = (RecyclerView) findViewById(R.id.recommend_place_list);
        placeRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        placeRecyclerView.setLayoutManager(layoutManager);
        placeRecyclerView.scrollToPosition(0);
        placeAdapter = new PlaceAdapter(this , placeItemList ,RECOMMEND_CODE);
        placeRecyclerView.setAdapter(placeAdapter);
        placeRecyclerView.setItemAnimator(new DefaultItemAnimator());


        message =(LinearLayout) findViewById(R.id.message_layout);
        message.setOnClickListener(BtnClickListener);

        reserveBtn = (Button) findViewById(R.id.reserve_btn);
        reserveBtn.setOnClickListener(BtnClickListener);
    }



    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

       map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
    public View.OnClickListener BtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.message_layout:
//                    intent = new Intent(getApplicationContext() , ReserveActivity.class);
//                    startActivity(intent);
                    break;
                case R.id.reserve_btn:
                    intent = new Intent(getApplicationContext(), ReserveActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };
    private void initData(){
        //장소 임시데이터
        placeItemList = new ArrayList<>();
        placeItemList.add(new PlaceItem(R.drawable.default_img, "name"));
        placeItemList.add(new PlaceItem(R.drawable.default_img, "name2"));
        placeItemList.add(new PlaceItem(R.drawable.default_img, "name2"));
        placeItemList.add(new PlaceItem(R.drawable.default_img, "name2"));
        placeItemList.add(new PlaceItem(R.drawable.default_img,"name2"));
    }
}
