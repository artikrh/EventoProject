package com.ick.eventoproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import java.util.Calendar;

public class PostEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView txtEventName;
    TextView txtDesc;
    TextView txtTime;
    Button btnPhoto;
    Button btnSubmit;
    Button btnTime;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPref sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()){
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);


        btnTime = findViewById(R.id.btnTime);
        txtTime = findViewById(R.id.txtResult);
        txtTime.setText("Select date and time");

        btnTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PostEventActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog,PostEventActivity.this,year,month,day);
                datePickerDialog.show();

            }
        });

    }


    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1+1;
        dayFinal = i2;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(PostEventActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog, PostEventActivity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int i, int i1) {
            hourFinal = i;
            minuteFinal = i1;
        String AM_PM ;
        if(i < 12) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }

            txtTime.setText(hourFinal+":"+minuteFinal+" "+AM_PM+" - "+dayFinal+"/"+monthFinal+"/"+yearFinal);
    }

}
