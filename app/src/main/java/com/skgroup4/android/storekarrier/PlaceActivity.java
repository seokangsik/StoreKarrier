package com.skgroup4.android.storekarrier;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.skgroup4.android.storekarrier.item.RepoSpot;
import com.squareup.picasso.Picasso;


/**
 * Created by Seo on 2017-07-27.
 */

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView placeImg;
    private TextView placeName;
    private TextView placeDescription;
    private RatingBar placeRating;
    private TextView ratingText;
    private TextView addressText;
    private RepoSpot spotInfo;
    private float latitude;
    private float longitude;
    private String title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        placeImg = (ImageView) findViewById(R.id.activity_place_img);
        placeName = (TextView) findViewById(R.id.activity_place_name);
        placeDescription = (TextView) findViewById(R.id.activity_place_description);
        placeRating = (RatingBar) findViewById(R.id.activity_place_ratingbar);
        ratingText = (TextView) findViewById(R.id.activity_place_rating_text);
        addressText = (TextView) findViewById(R.id.activity_place_address);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            spotInfo = (RepoSpot) bundle.getSerializable("spotInfo");
            Picasso.with(this).load(spotInfo.getPlaceImg()).fit().centerCrop().into(placeImg);
            placeName.setText(spotInfo.getPlaceName());
            placeDescription.setText(spotInfo.getDescription());
            latitude = Float.parseFloat(spotInfo.getLatitude());
            longitude = Float.parseFloat(spotInfo.getLongitude());
            ratingText.setText( spotInfo.getPoint());
            addressText.setText( spotInfo.getAddress());
            placeRating.setStepSize((float) 0.5);
            float starNum = Float.parseFloat(spotInfo.getPoint());
            placeRating.setRating(starNum);
            title = spotInfo.getPlaceName();
        }
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.place_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng SEOUL = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title(title);
        //markerOptions.snippet("한국의 수도");

        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(11));
    }
}
