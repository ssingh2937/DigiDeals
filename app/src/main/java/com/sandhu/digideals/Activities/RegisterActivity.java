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

public class RegisterActivity extends AppCompatActivity {
    TextView loginIntent;
    EditText nameEdt, usernameEdt, passwordEdt, confirmPasswordEdt;
    Button registerBtn;
    SessionManagement session;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);
        session = new SessionManagement(this);

        loginIntent = findViewById(R.id.login_intent_text);
        nameEdt = findViewById(R.id.register_name_text);
        usernameEdt = findViewById(R.id.register_username_text);
        passwordEdt = findViewById(R.id.register_password_text);
        confirmPasswordEdt = findViewById(R.id.confirm_password_text);
        registerBtn = findViewById(R.id.signup_btn);

        loginIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdt.getText().toString().trim();
                String username = usernameEdt.getText().toString().trim();
                String pass = passwordEdt.getText().toString().trim();
                String confirmPass = confirmPasswordEdt.getText().toString();

                if(name.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                } else if(username.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                } else if(pass.equalsIgnoreCase("") || passwordEdt.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
                } else if(!pass.equalsIgnoreCase(confirmPass)){
                    Toast.makeText(RegisterActivity.this, "Confirm password is incorrect", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUsername = db.checkUsername(username);
                    if (!checkUsername){
                        boolean insertUser = db.registerUser(name, username, pass);

                        if(insertUser){
                            Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            session.loggedIn(true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}