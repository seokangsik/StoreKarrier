package com.skgroup4.android.storekarrier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2017-08-03.
 */

public class NewHostingActivity extends AppCompatActivity {
    static final int COMPLETED_OK = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hosting);


    }

    public void onButtonClicked(View v) {
        Intent intent = new Intent(this, ExplainStorageActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == requestCode) {
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

            String name = data.getStringExtra("name");
            String desc = data.getStringExtra("desc");
            String min = data.getStringExtra("min");
            String max = data.getStringExtra("max");
            String price = data.getStringExtra("price");
            String imageuri = data.getStringExtra("imageuri");

            resultIntent.putExtra("country", ctry);
            resultIntent.putExtra("city", cty);
            resultIntent.putExtra("region", regin);
            resultIntent.putExtra("road_addr", rdaddr);
            resultIntent.putExtra("details", dtils);
            resultIntent.putExtra("zipcode", mladdr);
            resultIntent.putExtra("carrier", crrier);

            resultIntent.putExtra("name", data.getStringExtra("name"));
            resultIntent.putExtra("desc", data.getStringExtra("desc"));
            resultIntent.putExtra("min", data.getStringExtra("min"));
            resultIntent.putExtra("max", data.getStringExtra("max"));
            resultIntent.putExtra("price", data.getStringExtra("price"));
            resultIntent.putExtra("imageuri", data.getStringExtra("imageuri"));

            setResult(COMPLETED_OK, resultIntent);
            Bundle bundle = new Bundle();
            bundle.putString("country", ctry);
            bundle.putString("city", cty);
            bundle.putString("region", regin);
            bundle.putString("road_addr", rdaddr);
            bundle.putString("details", dtils);
            bundle.putString("zipcode", mladdr);
            bundle.putString("carrier", crrier);
            bundle.putString("name", name);
            bundle.putString("desc", desc);
            bundle.putString("min", min);
            bundle.putString("max", max);
            bundle.putString("price", price);
            bundle.putString("imageuri", imageuri);

            Intent intent = new Intent(getApplicationContext(), HostModeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}
