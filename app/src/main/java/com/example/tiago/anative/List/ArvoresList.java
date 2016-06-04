package com.example.tiago.anative.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.Form.ArvoresForm;
import com.example.tiago.anative.R;

public class ArvoresList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_list);
    }
    public void startArvoresForm(View view) {

        Intent secondActivity = new Intent(this, ArvoresForm.class);
        startActivity(secondActivity);
    }

}
