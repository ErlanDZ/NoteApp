package com.example.noteapp.preference;

import android.content.Context;
import android.content.SharedPreferences;

public  class PreferenceHelper {

   // PreferenceHelper sp = null;

   private SharedPreferences sp = null;

    public void  init (Context context){
        sp = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
     public void onSaveOnBoard () {
        sp.edit().putBoolean("onBoard",true ).apply();
     }

     public boolean isShown(){
        return sp.getBoolean("onBoard",false);
     }

     public void onSaveImage () {
        sp.edit().putBoolean("onBoardt",true ).apply();
    }
    public void onSaveImageDefault () {
        sp.edit().putBoolean("onBoardt",false ).apply();
     }

     public boolean isShownImage(){
        return sp.getBoolean("onBoardt",false);
     }




}