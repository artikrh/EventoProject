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

public class Business extends AppCompatActivity {

    int[] IMAGES = {R.drawable.bootcamp,R.drawable.rockstart};
    String[] NAMES = {"Startup Bootcamp #2","Rockstart Answers Prishtina #4"};
    String[] DESC = {"Join us for a four-day intensive startup bootcamp.","Join our Rockstart Answers Pristina #4 and get inspired by new people and ideas."};
    String[] TIME = {"JUNE 14 @ 17:00","MAY 25 @ 14:00"};

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
        setContentView(R.layout.activity_business);

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

            //imageView.setImageResource(IMAGES[1]);
            imageView.setImageResource(IMAGES[position]);
            tvTitle.setText(NAMES[position]);
            tvDesc.setText(DESC[position]);
            tvTime.setText(TIME[position]);

            return convertView;

        }
    }

}
