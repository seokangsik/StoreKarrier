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

public class HostStatisticsFragment extends Fragment {
    public HostStatisticsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host_statistic,container, false);
        return view;
    }
}
