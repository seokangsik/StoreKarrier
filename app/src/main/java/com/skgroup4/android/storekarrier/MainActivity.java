package com.skgroup4.android.storekarrier;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.skgroup4.android.storekarrier.fragment.MessageFragment;
import com.skgroup4.android.storekarrier.fragment.ProfileFragment;
import com.skgroup4.android.storekarrier.fragment.SavedFragment;
import com.skgroup4.android.storekarrier.fragment.SearchFragment;
import com.skgroup4.android.storekarrier.fragment.TripFragment;

public class MainActivity extends AppCompatActivity {

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
