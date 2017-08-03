package com.skgroup4.android.storekarrier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2017-08-03.
 */

public class NewHostingActivity extends AppCompatActivity{
    static final int COMPLETED_OK = 10;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hosting);


    }
    public void onButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), ExplainStorageActivity.class);
        startActivityForResult(intent,1);
    }
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        Intent resultIntent = new Intent();

        EditText country = (EditText) findViewById(R.id.new_hosting_country);
        EditText city = (EditText) findViewById(R.id.new_hosting_city);
        EditText region = (EditText) findViewById(R.id.new_hosting_region);
        EditText roadaddr = (EditText) findViewById(R.id.new_hosting_roadaddress);
        EditText details = (EditText) findViewById(R.id.new_hosting_details);
        EditText zipcode = (EditText) findViewById(R.id.new_hosting_mailaddr);
        EditText carrier = (EditText) findViewById(R.id.new_hosting_numcarrier);

        String ctry = country.getText().toString();
        String cty = city.getText().toString();
        String regin = region.getText().toString();
        String rdaddr = roadaddr.getText().toString();
        String dtils = details.getText().toString();
        String mladdr = zipcode.getText().toString();
        String crrier = carrier.getText().toString();

        resultIntent.putExtra("country",ctry);
        resultIntent.putExtra("city",cty);
        resultIntent.putExtra("region",regin);
        resultIntent.putExtra("road_addr",rdaddr);
        resultIntent.putExtra("details",dtils);
        resultIntent.putExtra("zipcode",mladdr);
        resultIntent.putExtra("carrier",crrier);

        setResult(COMPLETED_OK,resultIntent);
        finish();
    }
}
