package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skgroup4.android.storekarrier.R;

/**
 * Created by Seo on 2017-07-30.
 */

public class HostStoreFragment extends Fragment {
    public HostStoreFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host_store , container , false);
        return view;
    }
    public void onClick4StorageInfo(){

    }
}
