package com.sandhu.digideals;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

//Shared preferences file
public class SessionManagement {
    Context context;
    //SharedPreference name
    public static final String SHARED_PREF_NAME = "DIGIDEALS";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    //Parameterized constructor
    public SessionManagement(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    //Save login session
    public void loggedIn(boolean input){
        editor.putBoolean("login", input);
        editor.commit();
    }

    //Destroy login
    public void destroySession(){
        editor.putBoolean("login", false);
        editor.commit();
    }

    //Check login status
    public boolean isLoggedIn(){
        boolean result = sp.getBoolean("login", false);
        return result;
    }




}
