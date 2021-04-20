package com.sandhu.digideals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sandhu.digideals.R;
import com.sandhu.digideals.SessionManagement;

public class SplashActivity extends AppCompatActivity {
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);

        session = new SessionManagement(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(session.isLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);

    }
}