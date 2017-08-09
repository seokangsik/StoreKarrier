package com.skgroup4.android.storekarrier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-08-07.
 */

public class ShowHostingInfoActivity extends AppCompatActivity{
    String ctry="";
    String cty="";
    String regin="";
    String rdaddr="";
    String dtils="";
    String mladdr="";
    String crrier="";
    String name="";
    String desc="";
    String min="";
    String max="";
    String price="";
    String imageuri="";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hosting_info);

        Bundle bundle = getIntent().getExtras();

        ctry = bundle.getString("country");
        cty = bundle.getString("city");
        regin = bundle.getString("region");
        rdaddr = bundle.getString("road_addr");
        dtils = bundle.getString("details");
        mladdr = bundle.getString("zipcode");
        crrier = bundle.getString("carrier");
        name= bundle.getString("name");
        desc= bundle.getString("desc");
        min= bundle.getString("min");
        max= bundle.getString("max");
        price= bundle.getString("price");
        imageuri = bundle.getString("imageuri");

        TextView myCountry = (TextView) findViewById(R.id.new_hosting_country2show);
        TextView myCity = (TextView) findViewById(R.id.new_hosting_city2show);
        TextView myRegion = (TextView) findViewById(R.id.new_hosting_region2show);
        TextView myRoadaddr = (TextView) findViewById(R.id.new_hosting_roadaddress2show);
        TextView myDetails = (TextView) findViewById(R.id.new_hosting_details2show);
        TextView myZipcode = (TextView) findViewById(R.id.new_hosting_mailaddr2show);
        TextView myCarrier = (TextView) findViewById(R.id.new_hosting_numcarrier2show);

        myCountry.setText(ctry);
        myCity.setText(cty);
        myRegion.setText(regin);
        myRoadaddr.setText(rdaddr);
        myDetails.setText(dtils);
        myZipcode.setText(mladdr);
        myCarrier.setText(crrier);

    }
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        finish();
    }
    public void onClick2showExplainStorage(View v){
        Bundle bundle = new Bundle();

        Intent intent = new Intent(getApplicationContext(),ShowExplanainStorage.class);

        bundle.putString("name",name);
        bundle.putString("desc",desc);
        bundle.putString("min",min);
        bundle.putString("max",max);
        bundle.putString("price",price);
        bundle.putString("imageuri",imageuri);
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
    }
    public void onClick2return(View v){
        finish();
    }
}
