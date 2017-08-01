package com.skgroup4.android.storekarrier;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Seo on 2017-07-29.
 */

public class ReserveActivity extends AppCompatActivity{
    private Toolbar reserveToolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_reserve);
        reserveToolbar = (Toolbar) findViewById(R.id.reserve_toolbar);
        setSupportActionBar(reserveToolbar);
    }
}
