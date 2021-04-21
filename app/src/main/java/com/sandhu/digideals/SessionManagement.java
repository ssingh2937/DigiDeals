package com.sandhu.digideals;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SessionManagement {
    Context context;
    public static final String SHARED_PREF_NAME = "DIGIDEALS";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SessionManagement(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    public void loggedIn(boolean input){
        editor.putBoolean("login", input);
        editor.commit();
    }

    public void destroySession(){
        editor.putBoolean("login", false);
        editor.commit();
    }

    public boolean isLoggedIn(){
        boolean result = sp.getBoolean("login", false);
        return result;
    }




}
