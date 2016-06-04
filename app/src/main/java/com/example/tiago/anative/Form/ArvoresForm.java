package com.example.tiago.anative.Form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.List.SolicitacoesList;
import com.example.tiago.anative.R;

public class ArvoresForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_form);
    }
    public void startList(View view) {

        Intent secondActivity = new Intent(this, MenuList.class);
        startActivity(secondActivity);
    }
}
