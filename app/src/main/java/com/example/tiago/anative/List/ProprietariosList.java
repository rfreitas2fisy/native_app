package com.example.tiago.anative.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.Form.ProprietariosForm;
import com.example.tiago.anative.R;

public class ProprietariosList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietarios_list);
    }
    public void startProprietariosForm(View view) {

        Intent secondActivity = new Intent(this, ProprietariosForm.class);
        startActivity(secondActivity);
    }
}
