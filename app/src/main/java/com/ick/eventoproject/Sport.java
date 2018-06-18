package com.ick.eventoproject;

import android.content.Intent;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.ick.eventoproject.R;

public class Sport extends AppCompatActivity {

    int[] IMAGES = {R.drawable.ffk,R.drawable.ffk};
    String[] NAMES = {"Prishtina vs Drita","Trepça'89 vs Flamurtari"};
    String[] DESC = {"Vala Superliga, Edicioni garues 2017/18","Vala Superliga, Edicioni garues 2017/18"};
    String[] TIME = {"AUGUST 18 @ 13:00","JUNE 20 @ 13:00"};
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToogle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

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

            //imageView.setImageResource(R.drawable.ffk);
            imageView.setImageResource(IMAGES[0]);
            tvTitle.setText(NAMES[position]);
            tvDesc.setText(DESC[position]);
            tvTime.setText(TIME[position]);

            return convertView;

        }
    }

}
