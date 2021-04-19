package com.sandhu.digideals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sandhu.digideals.R;

public class RegisterActivity extends AppCompatActivity {
    TextView loginIntent;
    EditText nameEdt, usernameEdt, passwordEdt, confirmPasswordEdt;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_register);

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
                if(nameEdt.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                } else if(usernameEdt.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                } else if(passwordEdt.getText().toString().equalsIgnoreCase("") || passwordEdt.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
                } else if(!passwordEdt.getText().toString().trim().equalsIgnoreCase(confirmPasswordEdt.getText().toString().trim())){
                    Toast.makeText(RegisterActivity.this, "Confirm password is incorrect", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

    }
}