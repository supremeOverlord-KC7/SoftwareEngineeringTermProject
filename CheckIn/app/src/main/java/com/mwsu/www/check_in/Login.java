package com.mwsu.www.check_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }

    public void onLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        BackgroundLogin backgroundLogin = new BackgroundLogin(this);
        backgroundLogin.execute(username,password);

        //Decide if to go to student or prof???
        Intent intent = new Intent(Login.this, StudentHome.class);
        startActivity(intent);

    }

    public void onSignUp(View view){
        startActivity(new Intent(this, SignUpAs.class));
    }

    //Get rid of the android back function...
    @Override
    public void onBackPressed() {

    }
}
