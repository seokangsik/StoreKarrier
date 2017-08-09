package com.skgroup4.android.storekarrier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;

/**
 * Created by Seo on 2017-08-08.
 */

public class SetDateActivity extends AppCompatActivity {
    private ImageView cancelDateBtn;
    private CalendarView calendarView;
    private Button saveDateBtn;
    private String dateString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        cancelDateBtn = (ImageView) findViewById(R.id.cancel_date_btn);
        saveDateBtn = (Button) findViewById(R.id.save_date_btn);
        calendarView = (CalendarView) findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateString = year + "년 " + month + "월 " + dayOfMonth + "일";
            }
        });
        cancelDateBtn.setOnClickListener(BtnListener);
        saveDateBtn.setOnClickListener(BtnListener);
    }

    View.OnClickListener BtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.cancel_date_btn:
                    finish();
                    break;
                case R.id.save_date_btn:
                    Intent intent = new Intent();
                    intent.putExtra("date" ,dateString);
                    setResult(Activity.RESULT_OK , intent);
                    finish();
                    break;
            }
        }
    };
}
