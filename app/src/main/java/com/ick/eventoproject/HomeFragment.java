package com.ick.eventoproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    final int[] IMAGES = {R.drawable.ledri,R.drawable.era};
    final String[] NAMES = {"Ledri Vula","Era Istrefi"};
    final String[] DESC = {"Zone Club","Duplex Premium"};
    final String[] TIME = {"JULY 13 @ 21:00","JULY 31 @ 22:00"};
    ImageButton imgBtn_Music;
    ImageButton imgBtn_Sport;
    ImageButton imgBtn_Business;
    ImageButton imgBtn_Theatre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = view.findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        imgBtn_Sport = view.findViewById(R.id.imgBtn_Sport);
        imgBtn_Music = view.findViewById(R.id.imgBtn_Music);
        imgBtn_Business = view.findViewById(R.id.imgBtn_Business);
        imgBtn_Theatre = view.findViewById(R.id.imgBtn_Theatre);

        imgBtn_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { Sport(); }
        });
        imgBtn_Music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Music(); }
        });
        imgBtn_Theatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theatre();
            }
        });
        imgBtn_Business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Business();
            }
        });

        return view;
    }

    /* Metodat per krijimin e nje Intenti per kalimin ne aktivitetin tjeter */
    public void Sport() {
        Intent intent_Sport = new Intent(getActivity(), Sport.class);
        startActivity(intent_Sport);
    }

    public void Music() {
        Intent intent_music = new Intent(getActivity(), Music.class);
        startActivity(intent_music);
    }

    public void Theatre() {
        Intent intent_Theatre = new Intent(getActivity(), Theatre.class);
        startActivity(intent_Theatre);
    }

    public void Business() {
        Intent intent_Business = new Intent(getActivity(), Business.class);
        startActivity(intent_Business);
    }
    /* End metodat per kalimin ne tjeter aktivitet */

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
