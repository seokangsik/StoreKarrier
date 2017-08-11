package com.skgroup4.android.storekarrier;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-08-07.
 */

public class ShowExplanainStorage extends AppCompatActivity {
    String name="";
    String desc="";
    String min="";
    String max="";
    String price="";
    String imageuri= "";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_explain_storage);

        Bundle bundle = getIntent().getExtras();
        name= bundle.getString("name");
        desc= bundle.getString("desc");
        min= bundle.getString("min");
        max= bundle.getString("max");
        price= bundle.getString("price");
        imageuri = bundle.getString("imageuri");


        Uri image = Uri.parse(imageuri);


        TextView storage_name = (TextView) findViewById(R.id.new_storage_storage_name2show);
        TextView storage_desc = (TextView) findViewById(R.id.new_storage_desc_storage2show);
        TextView storage_min_time = (TextView) findViewById(R.id.new_storage_least_time2show);
        TextView storage_max_time = (TextView) findViewById(R.id.new_storage_max_time2show);
        TextView storage_payment_info = (TextView) findViewById(R.id.new_storage_pricepertime2show);
        ImageView storage_picture = (ImageView) findViewById(R.id.show_loaded_image);
/*
        try {
           Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),image);
           storage_picture.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        storage_name.setText(name);
        storage_desc.setText(desc);
        storage_min_time.setText(min);
        storage_max_time.setText(max);
        storage_payment_info.setText(price);

    }
    public void onClick4return(View v){
        finish();
    }
}
