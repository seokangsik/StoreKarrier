package com.skgroup4.android.storekarrier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-08-03.
 */

public class ExplainStorageActivity extends AppCompatActivity {
    static final int REQUEST_FOR_IMAGE = 8080;
    String name = "";
    String description = "";
    String least = "";
    String max = "";
    String priceper = "";
    String imageuri = "";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_storage);
    }
    public void onButtonClicked(View v){
        Intent resultIntent = new Intent();

        EditText name_storage = (EditText) findViewById(R.id.new_storage_storage_name);
        EditText explain_storage = (EditText) findViewById(R.id.new_storage_desc_storage);
        EditText least_time = (EditText) findViewById(R.id.new_storage_least_time);
        EditText max_time = (EditText) findViewById(R.id.new_storage_max_time);
        EditText price = (EditText) findViewById(R.id.new_storage_pricepertime);

        name = name_storage.getText().toString();
        description = explain_storage.getText().toString();
        least = least_time.getText().toString();
        max = max_time.getText().toString();
        priceper = price.getText().toString();


        resultIntent.putExtra("name",name);
        resultIntent.putExtra("desc",description);
        resultIntent.putExtra("min",least);
        resultIntent.putExtra("max",max);
        resultIntent.putExtra("price",priceper);
        resultIntent.putExtra("imageuri",imageuri);

        setResult(1,resultIntent);
        finish();
    }
    public void LoadImageFromLocal(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_FOR_IMAGE);
    }
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        ImageView myImage = (ImageView) findViewById(R.id.Loadedimage);
        try{
            if (requestCode == REQUEST_FOR_IMAGE && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                Uri uri = data.getData();
                imageuri = uri.toString();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                myImage.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        }  catch (Exception e) {
        Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }


}

}
