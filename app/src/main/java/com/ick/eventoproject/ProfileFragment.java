package com.ick.eventoproject;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView txtLocation;
    Geocoder mGeocoder;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPref sharedpref = new SharedPref(getActivity());

        if(sharedpref.loadNightModeState()){
            getActivity().setTheme(R.style.darktheme);
        }
        else{
            getActivity().setTheme(R.style.AppTheme);
        }

        txtLocation = view.findViewById(R.id.txtLocation);
        try {
            txtLocation.setText(getCityNameByCoordinates(44.786568,20.448922));
        } catch (Exception e){

        }

        return view;
    }

    private String getCityNameByCoordinates(double lat, double lon) throws IOException {
        mGeocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        return null;
    }

}
