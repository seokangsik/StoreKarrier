package com.skgroup4.android.storekarrier.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

import com.skgroup4.android.storekarrier.ActivityResultEvent;
import com.skgroup4.android.storekarrier.GetHouse;
import com.skgroup4.android.storekarrier.GetSpot;
import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.SetCarrierNumActivity;
import com.skgroup4.android.storekarrier.SetDateActivity;
import com.skgroup4.android.storekarrier.SetLocationActivity;
import com.skgroup4.android.storekarrier.item.RepoHouse;
import com.skgroup4.android.storekarrier.item.RepoSpot;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Seo on 2017-07-18.
 */

public class SearchFragment extends Fragment {
    public SearchFragment() {

    }


    public static final int REQ_LOC_CODE = 10003;
    public static final int REQ_DATE_CODE = 10004;
    public static final int REQ_CARIIER_CODE = 10005;

    //http connection
    static JSONArray houseArray;
    static JSONArray spotArray;
    Retrofit retrofit = new Retrofit.Builder().baseUrl(GetHouse.house_url).build();
    Retrofit retrofit2 = new Retrofit.Builder().baseUrl(GetSpot.spot_url).build();
    GetHouse getHouse = retrofit.create(GetHouse.class);
    GetSpot getSpot = retrofit2.create(GetSpot.class);
    ArrayList<RepoHouse> houseList;
    ArrayList<RepoSpot> spotList;
    private Button recommendBtn;
    public Button storeBtn;
    public Button placeBtn;
    private ImageView collapseBtn;

    private TextView mainLocationText;
    private TextView mainDateText;
    private TextView mainCarrierText;
    private TextView subLocationText;
    private TextView subDateText;
    private TextView subCarrierText;
    private AppBarLayout appBarLayout;
    private Toolbar mToolbar;
    private LinearLayout setLocationBar;
    private LinearLayout setDateBar;
    private LinearLayout setCarrierNumBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //검색 Fragment에서 추천/보관소/장소 각 버튼들
        recommendBtn = (Button) view.findViewById(R.id.recommend_btn);
        storeBtn = (Button) view.findViewById(R.id.store_btn);
        placeBtn = (Button) view.findViewById(R.id.place_btn);
        collapseBtn = (ImageView) view.findViewById(R.id.collapsing_btn);

        mainLocationText = (TextView) view.findViewById(R.id.location_main_text);
        subLocationText = (TextView) view.findViewById(R.id.location_sub_text);
        mainDateText = (TextView) view.findViewById(R.id.date_main_text);
        subDateText = (TextView) view.findViewById(R.id.date_sub_text);
        mainCarrierText = (TextView) view.findViewById(R.id.carrier_main_text);
        subCarrierText = (TextView) view.findViewById(R.id.carrier_sub_text);

        //툴바 expand collapse
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setLocationBar = (LinearLayout) view.findViewById(R.id.set_location);
        setDateBar = (LinearLayout) view.findViewById(R.id.set_date);
        setCarrierNumBar = (LinearLayout) view.findViewById(R.id.set_carrier);

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_layout);
        appBarLayout.setExpanded(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("OFFSET", verticalOffset + "");

                if (verticalOffset >= -400) {
                    mToolbar.setVisibility(View.INVISIBLE);
                } else if (verticalOffset < -400) {
                    mToolbar.setVisibility(View.VISIBLE);
                }
            }

        });


        //리스너 등록
        mToolbar.setOnClickListener(BtnClickListener);
        setLocationBar.setOnClickListener(BtnClickListener);
        setDateBar.setOnClickListener(BtnClickListener);
        setCarrierNumBar.setOnClickListener(BtnClickListener);
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
        fragmentTransaction.add(R.id.search_container,new RecommendFragment());
        fragmentTransaction.commit();

    }


    // 추천 보관소 장소 버튼에 따라 Fragment를 replace하는 이벤트
    public View.OnClickListener BtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Fragment fragment;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            Intent intent;
            switch (v.getId()) {
                case R.id.recommend_btn:
                    fragment = new RecommendFragment();

                    if(houseList!=null && spotList!=null){
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("houseList", houseList);
                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable("spotList" , spotList);
                        bundle.putBundle("spotBundle" , bundle1);
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.search_container, fragment , "recommendFragment");
                       // fm.putFragment(bundle, "recommendFragment" , fragment);
                    }
                    else{
                        fragmentTransaction.replace(R.id.search_container, fragment );
                    }

                    fragmentTransaction.commit();
                    break;
                case R.id.store_btn:
                    StoreFragment storeFragment = (StoreFragment) getFragmentManager().findFragmentByTag("storeFragment");
                    if(storeFragment!=null){
                        fragment = storeFragment;
                    }
                    else{
                        fragment = new StoreFragment();
                    }


                    if (houseList != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("houseList", houseList);
                        fragment.setArguments(bundle);
                      //  fm.putFragment(bundle , "storeFragment" , fragment);
                        fragmentTransaction.replace(R.id.search_container, fragment ,"storeFrament");
                    }
                    else{
                        fragmentTransaction.replace(R.id.search_container, fragment );
                    }

                    fragmentTransaction.commit();
                    break;
                case R.id.place_btn:
                    PlaceFragment placeFragment = (PlaceFragment) getFragmentManager().findFragmentByTag("placeFragment");

                    if(placeFragment!=null){
                        fragment = placeFragment;
                    }else{
                        fragment = new PlaceFragment();
                    }

                    if(spotList!=null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("spotList" , spotList);
                        fragment.setArguments(bundle);
                       // fm.putFragment(bundle, "placeFragment" , fragment);
                        fragmentTransaction.replace(R.id.search_container, fragment, "placeFragment");
                    }
                    else{
                        fragmentTransaction.replace(R.id.search_container, fragment);
                    }

                    fragmentTransaction.commit();
                    break;
                case R.id.toolbar:
                    appBarLayout.setExpanded(true);
                    break;
                case R.id.collapsing_btn:
                    appBarLayout.setExpanded(false);
                    break;
                case R.id.set_location:
                    intent = new Intent(getActivity(), SetLocationActivity.class);
                    startActivityForResult(intent, REQ_LOC_CODE);
                    break;
                case R.id.set_date:
                    intent = new Intent(getActivity(), SetDateActivity.class);
                    startActivityForResult(intent, REQ_DATE_CODE);
                    break;
                case R.id.set_carrier:
                    intent = new Intent(getActivity(), SetCarrierNumActivity.class);
                    startActivityForResult(intent, REQ_CARIIER_CODE);
                    break;
            }
        }
    };

    @SuppressWarnings("unused")
    @Subscribe
    public void onActivityResultEvent(@NonNull ActivityResultEvent event) {
        onActivityResult(event.getRequestCode(), event.getResultCode(), event.getData());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_LOC_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String locationString = data.getStringExtra("location");
                    mainLocationText.setText(locationString);
                    subLocationText.setText(locationString);
                    Call<ResponseBody> house;
                    Call<ResponseBody> spot;
                    if (locationString.equals("파리")) {
                        house = getHouse.getLocation("1", "48.856489", "2.352398");
                        spot = getSpot.getSpot("1", "48.856489", "2.352398");
                    } else {
                        house = getHouse.getLocation("1", "41.904365", "12.49346");
                        spot = getSpot.getSpot("1", "41.904365", "12.49346");
                    }
                    house.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String result = response.body().string();
                                houseList = new ArrayList<>();
                                Log.d("Test", result);
                                houseArray = new JSONArray(result.toString());
                                for (int i = 0; i < houseArray.length(); i++) {
                                    JSONObject jsonObject = houseArray.getJSONObject(i);
                                    RepoHouse tmpRepo = new RepoHouse();
                                    tmpRepo.setHostName(jsonObject.getString("HOSTNAME"));
                                    tmpRepo.setHostImg(jsonObject.getString("HOSTIMG"));
                                    tmpRepo.setHostTel(jsonObject.getString("HOSTTEL"));
                                    tmpRepo.setHouseImg(jsonObject.getString("HOUSEIMG"));
                                    tmpRepo.setPrice(jsonObject.getString("PRICE"));
                                    tmpRepo.setAvg(jsonObject.getString("AVG"));
                                    tmpRepo.setLatitude(jsonObject.getString("LAT"));
                                    tmpRepo.setLongitude(jsonObject.getString("LONG"));
                                    houseList.add(tmpRepo);
                                }
                                for (int i = 0; i < houseList.size(); i++) {
                                    Log.e("HOUSE", "HostName " + houseList.get(i).getHostName() + " , HostImg " + houseList.get(i).getHostImg() +
                                            " , HostTel " + houseList.get(i).getHostTel() + " , HouseImg " + houseList.get(i).getHouseImg() +
                                            " , LATITUDE " + houseList.get(i).getLatitude() + " , LONG " + houseList.get(i).getLongitude()
                                            + " , AVG " + houseList.get(i).getAvg() + " , Price " + houseList.get(i).getPrice());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                    spot.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            try {
                                String result = response.body().string();
                                spotList = new ArrayList<>();

                                spotArray = new JSONArray(result.toString());
                                for (int i = 0; i < spotArray.length(); i++) {
                                    JSONObject jsonObject = spotArray.getJSONObject(i);
                                    RepoSpot tmpRepo = new RepoSpot();
                                    tmpRepo.setId(jsonObject.getString("S_ID"));
                                    tmpRepo.setLatitude(jsonObject.getString("LAT"));
                                    tmpRepo.setLongitude(jsonObject.getString("LONG"));
                                    tmpRepo.setPlaceName(jsonObject.getString("NAME"));
                                    tmpRepo.setPlaceImg(jsonObject.getString("IMG"));
                                    tmpRepo.setAddress(jsonObject.getString("ADDRESS"));
                                    tmpRepo.setDescription(jsonObject.getString("NOTE"));
                                    tmpRepo.setPoint(jsonObject.getString("PRE"));
                                    spotList.add(tmpRepo);
                                }
                                for (int i = 0; i < spotList.size(); i++) {
                                    Log.e("SPOTTEST", spotList.get(i).getDescription() + spotList.get(i).getPoint() + spotList.get(i).getId()
                                            + spotList.get(i).getLatitude() + spotList.get(i).getLongitude() + spotList.get(i).getPoint() + spotList.get(i).getPlaceName());
                                }
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }


                    });



                }
                break;
            case REQ_DATE_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String date = data.getStringExtra("date");
                    mainDateText.setText(date);
                    subDateText.setText(date);
                }
                break;
            case REQ_CARIIER_CODE:

                break;
        }
    }
}
