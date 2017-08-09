package com.skgroup4.android.storekarrier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * Created by Seo on 2017-08-08.
 */

public class SetLocationActivity extends AppCompatActivity {

    private ImageView cancelLocationBtn;
    private Button saveLocationBtn;
    private EditText setLocationText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        saveLocationBtn = (Button) findViewById(R.id.save_location_btn);
        cancelLocationBtn = (ImageView) findViewById(R.id.cancel_location_btn);
        setLocationText = (EditText) findViewById(R.id.set_location_text);

        saveLocationBtn.setOnClickListener(BtnLisenter);
        cancelLocationBtn.setOnClickListener(BtnLisenter);

    }

    View.OnClickListener BtnLisenter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.cancel_location_btn:
                    finish();
                    break;
                case R.id.save_location_btn:
                    Intent intent = new Intent();
                    intent.putExtra("location" , setLocationText.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    break;
            }
        }
    };
}
