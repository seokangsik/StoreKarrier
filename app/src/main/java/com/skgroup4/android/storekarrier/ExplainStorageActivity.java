package com.skgroup4.android.storekarrier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2017-08-03.
 */

public class ExplainStorageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_storage);
    }
    public void onClicked(View v){
        Intent resultIntent = new Intent();

        EditText name_storage = (EditText) findViewById(R.id.new_storage_storage_name);
        EditText explain_storage = (EditText) findViewById(R.id.new_storage_desc_storage);
        EditText least_time = (EditText) findViewById(R.id.new_storage_least_time);
        EditText max_time = (EditText) findViewById(R.id.new_storage_max_time);
        EditText price = (EditText) findViewById(R.id.new_storage_pricepertime);

        String name = name_storage.getText().toString();
        String description = explain_storage.getText().toString();
        String least = least_time.getText().toString();
        String max = max_time.getText().toString();
        String priceper = price.getText().toString();

        resultIntent.putExtra("name",name);
        resultIntent.putExtra("desc",description);
        resultIntent.putExtra("min",least);
        resultIntent.putExtra("max",max);
        resultIntent.putExtra("price",priceper);

        setResult(1,resultIntent);
        finish();
    }

}
