package com.sandhu.digideals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sandhu.digideals.DBHelper;
import com.sandhu.digideals.R;
import com.sandhu.digideals.SessionManagement;

public class LoginActivity extends AppCompatActivity {
    TextView registerIntentText;
    EditText usernameEdt, passwordEdt;
    Button loginBtn;
    SessionManagement session;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        session = new SessionManagement(this);

        registerIntentText = findViewById(R.id.signUpIntentText);
        usernameEdt = findViewById(R.id.login_username_edt);
        passwordEdt = findViewById(R.id.login_password_edt);
        loginBtn = findViewById(R.id.login_btn);

        registerIntentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdt.getText().toString().trim();
                String pass = passwordEdt.getText().toString().trim();

                if(usernameEdt.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                } else if(passwordEdt.getText().toString().equalsIgnoreCase("") || passwordEdt.getText().toString().length()<6){
                    Toast.makeText(LoginActivity.this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
                } else{
                    boolean checkUser = db.checkUsernamePassword(username, pass);

                    if(checkUser){
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        session.loggedIn(true);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
}