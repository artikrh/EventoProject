package com.ick.eventoproject;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Theatre extends AppCompatActivity {

    int[] IMAGES = {R.drawable.t1,R.drawable.t2};
    String[] NAMES = {"Nata e Dymbëdhjetë","Vrima e Lepurit"};
    String[] DESC = {"Nata e Dymbëdhjetë, është njëra nga komeditë e Shekspirit.","Shfaqja Vrima e Lepurit e David Lindsay Abaire dhe në regji të Zana Hoxha"};
    String[] TIME = {"MAY 17 @ 21:00","JUNE 11 @ 17:00"};


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
        setContentView(R.layout.activity_theatre);

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
