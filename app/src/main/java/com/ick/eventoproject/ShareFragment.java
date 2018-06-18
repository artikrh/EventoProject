package com.ick.eventoproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {


    public ShareFragment() {
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

}
