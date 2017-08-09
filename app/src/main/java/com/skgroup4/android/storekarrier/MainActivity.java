package com.skgroup4.android.storekarrier;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.skgroup4.android.storekarrier.fragment.MessageFragment;
import com.skgroup4.android.storekarrier.fragment.ProfileFragment;
import com.skgroup4.android.storekarrier.fragment.SavedFragment;
import com.skgroup4.android.storekarrier.fragment.SearchFragment;
import com.skgroup4.android.storekarrier.fragment.TripFragment;

public class MainActivity extends AppCompatActivity {
static final int REQUEST_CODE_GET_HOSTING_INFO = 1001;
    String ctry="";
    String cty="";
    String regin="";
    String rdaddr="";
    String dtils="";
    String mladdr="";
    String crrier="";
    String name="";
    String desc="";
    String min="";
    String max="";
    String price="";
    String imageuri="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //처음 Fragment 생성해서 붙이는 부분
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.main_container , new SearchFragment());
        fragmentTransaction.commit();


        //하단 BottomNavigation 설정
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void onButtonClicked4HostingMode(View v){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getApplicationContext(),HostModeActivity.class);
        bundle.putString("country",ctry);
        bundle.putString("city",cty);
        bundle.putString("region",regin);
        bundle.putString("road_addr",rdaddr);
        bundle.putString("details",dtils);
        bundle.putString("zipcode",mladdr);
        bundle.putString("carrier",crrier);
        bundle.putString("name",name);
        bundle.putString("desc",desc);
        bundle.putString("min",min);
        bundle.putString("max",max);
        bundle.putString("price",price);
        bundle.putString("imageuri",imageuri);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onButtonClicked4NewHosting(View v){
        Intent intent = new Intent(getApplicationContext(),NewHostingActivity.class);
        startActivityForResult(intent,REQUEST_CODE_GET_HOSTING_INFO);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        EventBus.getInstance().post(ActivityResultEvent.create(requestCode, resultCode, intent));

        if(requestCode == REQUEST_CODE_GET_HOSTING_INFO){
           ctry = intent.getStringExtra("country");
           cty = intent.getStringExtra("city");
           regin = intent.getStringExtra("region");
           rdaddr = intent.getStringExtra("road_addr");
           dtils = intent.getStringExtra("details");
           mladdr = intent.getStringExtra("zipcode");
           crrier = intent.getStringExtra("carrier");
            name = intent.getStringExtra("name");
            desc = intent.getStringExtra("desc");
            min = intent.getStringExtra("min");
            max = intent.getStringExtra("max");
            price = intent.getStringExtra("price");
            imageuri = intent.getStringExtra("imageuri");
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            //하단의 BottomNavigation의 버튼 클릭할 때마다 Fragment 교체
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    fragment = new SearchFragment();
                    fragmentTransaction.replace(R.id.main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_saved_list:
                    fragment = new SavedFragment();
                    fragmentTransaction.replace(R.id.main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_trip:
                    fragment = new TripFragment();
                    fragmentTransaction.replace(R.id.main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_message:
                    fragment = new MessageFragment();
                    fragmentTransaction.replace(R.id.main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    fragmentTransaction.replace(R.id.main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };

}
