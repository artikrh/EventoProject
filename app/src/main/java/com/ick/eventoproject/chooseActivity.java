package com.ick.eventoproject;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class chooseActivity extends AppCompatActivity {

    ImageView img_Perdoruesi;
    ImageView img_Biznesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        img_Biznesi = (ImageView) findViewById(R.id.biznes_ikona);
        img_Perdoruesi = (ImageView) findViewById(R.id.perdoruesi_ikona);

        img_Perdoruesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perdoruesi();
            }
        });
        img_Biznesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biznesi();
            }
        });



    }

    public void perdoruesi() {
        Intent perdoruesi = new Intent(this, UserActivity.class);
        startActivity(perdoruesi);

    }
    public void biznesi(){
        Intent biznesi=new Intent(this,PostEventActivity.class);
        startActivity(biznesi);
    }


}