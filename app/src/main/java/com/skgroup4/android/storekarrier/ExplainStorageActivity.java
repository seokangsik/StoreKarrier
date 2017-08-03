package com.skgroup4.android.storekarrier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017-08-03.
 */

public class ExplainStorageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_storage);
    }
    public void onClicked(){
        finish();
    }

}
