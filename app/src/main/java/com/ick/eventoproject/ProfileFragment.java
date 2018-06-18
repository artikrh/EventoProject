package com.ick.eventoproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPref sharedpref = new SharedPref(getActivity());

        if(sharedpref.loadNightModeState()){
            getActivity().setTheme(R.style.darktheme);
        }
        else{
            getActivity().setTheme(R.style.AppTheme);
        }

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
