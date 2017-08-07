package com.skgroup4.android.storekarrier;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Seo on 2017-07-29.
 */

public class ReserveActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Toolbar reserveToolbar;
    private LinearLayout dateLayout;
    private LinearLayout startLayout;
    private LinearLayout endLayout;
    private TextView dateText;
    private TextView startText;
    private TextView endText;
    DatePickerDialog datePickerDialog;
    TimePickerDialog startTimePickerDialog;
    TimePickerDialog endTimePickerDialog;

    private int startHour;
    private int startMinute;
    private boolean flag;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        reserveToolbar = (Toolbar) findViewById(R.id.reserve_toolbar);
        setSupportActionBar(reserveToolbar);
        dateLayout = (LinearLayout) findViewById(R.id.set_date_layout);
        startLayout = (LinearLayout) findViewById(R.id.set_start_layout);
        endLayout = (LinearLayout) findViewById(R.id.set_end_layout);
        dateText = (TextView) findViewById(R.id.date_text);
        startText = (TextView) findViewById(R.id.start_text);
        endText = (TextView) findViewById(R.id.end_text);


        datePickerDialog = new DatePickerDialog(
                this, ReserveActivity.this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        startTimePickerDialog = new TimePickerDialog(this, ReserveActivity.this, Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE) , false);
        endTimePickerDialog = new TimePickerDialog(this, ReserveActivity.this, Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE) , false);
        dateLayout.setOnClickListener(BtnListener);
        startLayout.setOnClickListener(BtnListener);
        endLayout.setOnClickListener(BtnListener);


    }
    View.OnClickListener BtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case  R.id.set_date_layout:
                    datePickerDialog.show();
                    break;
                case R.id.set_start_layout:
                    flag = false;
                    startTimePickerDialog.show();
                    break;
                case R.id.set_end_layout:
                    flag = true;
                    endTimePickerDialog.show();
                    break;
            }
        }
    };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateText.setText(year + " 년  " + month + " 월 " + dayOfMonth + " 일 ");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(!flag){
            startText.setText("시작시간 : " + hourOfDay + " 시 " + minute + " 분 ");
        }
        else{
            endText.setText("종료시간 : " + hourOfDay + " 시 " + minute + " 분 ");
        }
    }
}
