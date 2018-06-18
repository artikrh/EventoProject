package com.ick.eventoproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Music extends AppCompatActivity {


    int[] IMAGES = {R.drawable.zzo, R.drawable.ledri,R.drawable.alban,R.drawable.capitalt,R.drawable.era};
    String[] NAMES = {"Zig Zag Orchestra","Ledri Vula","Alban Skenderaj","Capital-T","Era Istrefi"};
    String[] DESC = {"Apartamenti 196","Zone Club","MIQT Pub","Zone Club","Duplex Premium"};
    String[] TIME = {"20:30","21:00","21:00","22:00","19:30"};

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
        setContentView(R.layout.activity_music);

        ListView listView = findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
            TextView tvDesc = convertView.findViewById(R.id.tvDesc);
            TextView tvTime = convertView.findViewById(R.id.tvTime);

            imageView.setImageResource(IMAGES[position]);
            tvTitle.setText(NAMES[position]);
            tvDesc.setText(DESC[position]);
            tvTime.setText(TIME[position]);

            return convertView;

        }
    }

}
