package com.ick.eventoproject;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences mySharedPref;
    public SharedPref(Context context){

        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);

    }
    // metoda qe e ruan gjendjen e night mode: true ose false
    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    // ngarko gjendjen
    public Boolean loadNightModeState(){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return state;
    }
}

