package com.example.tiago.anative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.List.MenuList;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
    }
    public void startSecondActivity(View view) {

        Intent secondActivity = new Intent(this, MenuList.class);
        startActivity(secondActivity);
    }
}
