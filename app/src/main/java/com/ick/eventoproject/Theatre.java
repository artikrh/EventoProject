package com.ick.eventoproject;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Theatre extends AppCompatActivity {

    //int[] IMAGES = {R.drawable.t1,R.drawable.t2};
    //String[] NAMES = {"Nata e Dymbëdhjetë","Vrima e Lepurit"};
    //String[] DESC = {"Nata e Dymbëdhjetë, është njëra nga komeditë e Shekspirit.","Shfaqja Vrima e Lepurit e David Lindsay Abaire dhe në regji të Zana Hoxha"};
    //String[] TIME = {"MAY 17 @ 21:00","JUNE 11 @ 17:00"};

    public static String[] names;
    public static String[] descriptions;
    public static String[] locations;
    public static String[] times;

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

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.drawerLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        loadData();

                    }
                },2000);
            }
        });
        loadData();
    }

    protected void loadData(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Events").child("Theatre");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> recordsSnaphots = dataSnapshot.getChildren();

                int count = (int) dataSnapshot.getChildrenCount();
                int i = 0;

                names = new String[count];
                descriptions = new String[count];
                locations = new String[count];
                times = new String[count];

                for(DataSnapshot recordSnapshot: recordsSnaphots){
                    MusicRecord record = recordSnapshot.getValue(MusicRecord.class);
                    if(record != null) {
                        names[i] = record.eventName;
                        descriptions[i] = record.eventDesciption;
                        locations[i] = record.location;
                        times[i] = record.date;
                        i++;
                    }
                }
                ListView listView = findViewById(R.id.listView);
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.length;
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

            //ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
            TextView tvDesc = convertView.findViewById(R.id.tvDesc);
            TextView tvTime = convertView.findViewById(R.id.tvTime);
            TextView tvLocation = convertView.findViewById(R.id.tvLocation);

            //imageView.setImageResource(IMAGES[position]);
            tvTitle.setText(names[position]);
            tvDesc.setText(descriptions[position]);
            tvTime.setText(times[position]);
            tvLocation.setText(locations[position]);

            return convertView;

        }
    }

}
