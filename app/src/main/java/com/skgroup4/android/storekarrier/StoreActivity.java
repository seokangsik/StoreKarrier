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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.skgroup4.android.storekarrier.adpater.PlaceAdapter;
import com.skgroup4.android.storekarrier.item.PlaceItem;
import com.skgroup4.android.storekarrier.item.RepoHouse;
import com.squareup.picasso.Picasso;

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

    //info
    private RepoHouse houseInfo;
    private ImageView storeImg;
    private TextView storeName;
    private TextView hostName;
    private ImageView hostImg;
    private TextView storePrice;
    private RatingBar storeRating;
    private TextView ratingText;
    private float latitude;
    private float longitude;
    private String title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        storeToolbar = (LinearLayout) findViewById(R.id.store_bottom_toolbar);
        storeToolbar.bringToFront();

        storeImg = (ImageView) findViewById(R.id.activity_store_img);
        storeName = (TextView) findViewById(R.id.activity_store_name);
        hostName = (TextView) findViewById(R.id.activity_store_host_name);
        hostImg = (ImageView) findViewById(R.id.activity_store_host_img);
        storePrice = (TextView) findViewById(R.id.activity_store_price);
        storeRating = (RatingBar) findViewById(R.id.activity_store_ratingbar) ;
        ratingText = (TextView) findViewById(R.id.activity_store_rating_text) ;
        //받은 데이터 처리
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            houseInfo = (RepoHouse) bundle.getSerializable("houseInfo");
            Picasso.with(this).load(houseInfo.getHouseImg()).fit().centerCrop().into(storeImg);
            storeName.setText(houseInfo.getHostName() + "의 보관소");
            hostName.setText(houseInfo.getHostName());
            Picasso.with(this).load(houseInfo.getHostImg()).fit().centerCrop().into(hostImg);
            storePrice.setText("￦ "+houseInfo.getPrice());
            ratingText.setText(houseInfo.getAvg());
            float starNum = Float.parseFloat(houseInfo.getAvg());
            storeRating.setStepSize((float) 0.5);
            storeRating.setRating(starNum);
            latitude = Float.parseFloat(houseInfo.getLatitude());
            longitude = Float.parseFloat(houseInfo.getLongitude());
            title = houseInfo.getHostName() + "의 보관소";
        }



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
        LatLng location = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        markerOptions.title(title);
//        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

       map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.animateCamera(CameraUpdateFactory.zoomTo(11));

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
