package com.ick.eventoproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.hotspot2.omadm.PpsMoParser;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView etData;
    Button btnPhoto;
    Button btnSubmit;
    Button btnTime;
    ImageView imgPhoto;
    Spinner spinner;
    EditText etEventName;
    EditText etDescription;
    RadioButton rdMusic;
    RadioButton rdSport;
    RadioButton rdTheatre;
    RadioButton rdBusiness;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    private final static int RESULT_LOAD_IMAGE=3;
    private static final String[] paths = {"item 1", "item 2" , "item 3"};
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SharedPref sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);
        imgPhoto = findViewById(R.id.imgPhoto);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnTime = findViewById(R.id.btnTime);
        btnSubmit=findViewById(R.id.btnSubmit);
        etEventName=findViewById(R.id.etEventName);
        etDescription=findViewById(R.id.etDescription);
        rdBusiness=(RadioButton) findViewById(R.id.rdBusiness);
        rdMusic=(RadioButton) findViewById(R.id.rdMusic);
        rdSport=(RadioButton) findViewById(R.id.rdSport);
        rdTheatre=(RadioButton) findViewById(R.id.rdTheatre);
        etData = findViewById(R.id.txtTime);


        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PostEventActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog, PostEventActivity.this, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String eventName= etEventName.getText().toString();
                final String eventDescription=etDescription.getText().toString().trim();
                boolean music=rdMusic.isChecked();
                boolean sport=rdSport.isChecked();
                boolean theatre=rdTheatre.isChecked();
                boolean business=rdBusiness.isChecked();
                final String data=etData.getText().toString();

                            if(business) {
                                Business_register Business=new Business_register(eventName,eventDescription,"Events",data);
                                FirebaseDatabase.getInstance().getReference("Bussineses").child("").push()
                                      .setValue(Business);
                            }
                           else  if(sport){
                                Business_register businnes=new Business_register(eventName,eventDescription,"Music",data);
                                FirebaseDatabase.getInstance().getReference("Bussineses").child("").push()
                                        .setValue(businnes);

                            }
                            else if(theatre){
                                Business_register businnes=new Business_register(eventName,eventDescription,"Theatre",data);
                                FirebaseDatabase.getInstance().getReference("Bussineses").child("").push()
                                        .setValue(businnes);

                            }
                            else {
                                Business_register businnes=new Business_register(eventName,eventDescription,"Sport",data);
                                FirebaseDatabase.getInstance().getReference("Bussineses").child("").push()
                                        .setValue(businnes);

                            }

            }
        });

        //upload image
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
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

            etData.setText(hourFinal+":"+minuteFinal+" - "+dayFinal+"/"+monthFinal+"/"+yearFinal);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data !=null){
            Uri selectedImage=data.getData();
            imgPhoto.setImageURI(selectedImage);
        }
    }


    }




