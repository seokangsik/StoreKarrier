package com.skgroup4.android.storekarrier;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.skgroup4.android.storekarrier.fragment.HostProfileFragment;
import com.skgroup4.android.storekarrier.fragment.HostStatisticsFragment;
import com.skgroup4.android.storekarrier.fragment.HostStoreFragment;

/**
 * Created by Seo on 2017-07-30.
 */

public class HostModeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        //처음 Fragment 생성해서 붙이는 부분
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.host_main_container , new HostProfileFragment());
        fragmentTransaction.commit();


        //하단 BottomNavigation 설정
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_host);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
