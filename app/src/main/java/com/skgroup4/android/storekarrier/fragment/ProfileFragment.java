package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.skgroup4.android.storekarrier.HostModeActivity;
import com.skgroup4.android.storekarrier.NewHostingActivity;
import com.skgroup4.android.storekarrier.R;

/**
 * Created by Seo on 2017-07-18.
 */

public class ProfileFragment extends Fragment {
    public ProfileFragment(){

    }
    LinearLayout layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button buttton = (Button) view.findViewById(R.id.newhosting_button);
        Button button = (Button) view.findViewById(R.id.host_mode_button);
        button.setOnClickListener(mListener);
        buttton.setOnClickListener(kListener);

        return view;
    }
    private View.OnClickListener kListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
           Intent intent = new Intent(getActivity(), NewHostingActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), HostModeActivity.class);
            startActivity(intent);
        }
    };
}
