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

import com.skgroup4.android.storekarrier.fragment.HostProfileFragment;
import com.skgroup4.android.storekarrier.fragment.HostStatisticsFragment;
import com.skgroup4.android.storekarrier.fragment.HostStoreFragment;

/**
 * Created by Seo on 2017-07-30.
 */

public class HostModeActivity extends AppCompatActivity {
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
    String imageuri = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        //처음 Fragment 생성해서 붙이는 부분
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.host_main_container , new HostStoreFragment());
        fragmentTransaction.commit();

        //storage info setting

        try {
            Bundle bundle = getIntent().getExtras();


            ctry = bundle.getString("country");
            cty = bundle.getString("city");
            regin = bundle.getString("region");
            rdaddr = bundle.getString("road_addr");
            dtils = bundle.getString("details");
            mladdr = bundle.getString("zipcode");
            crrier = bundle.getString("carrier");

            name = bundle.getString("name");
            desc = bundle.getString("desc");
            min = bundle.getString("min");
            max = bundle.getString("max");
            price = bundle.getString("price");
            imageuri = bundle.getString("imageuri");
        }
        catch(Exception e){
            e.printStackTrace();
        }



        //하단 BottomNavigation 설정
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_host);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void onButtonClick4info(View v){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getApplicationContext(),ShowHostingInfoActivity.class);

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

    public void onButtonClick4EditInfo(View v){
        Intent intent = new Intent(getApplicationContext(),NewHostingActivity.class);
        startActivityForResult(intent,0);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);

        if(requestCode == 0){
            ctry = intent.getStringExtra("country");
            cty = intent.getStringExtra("city");
            regin = intent.getStringExtra("region");
            rdaddr = intent.getStringExtra("road_addr");
            dtils = intent.getStringExtra("details");
            mladdr = intent.getStringExtra("zipcode");
            crrier = intent.getStringExtra("carrier");
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
                case R.id.navigation_host_profile:
                    fragment = new HostProfileFragment();
                    fragmentTransaction.replace(R.id.host_main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_host_statistics:
                    fragment = new HostStatisticsFragment();
                    fragmentTransaction.replace(R.id.host_main_container , fragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_host_store:
                    fragment = new HostStoreFragment();
                    fragmentTransaction.replace(R.id.host_main_container , fragment);
                    fragmentTransaction.commit();
                    return true;

            }
            return false;
        }

    };
}
