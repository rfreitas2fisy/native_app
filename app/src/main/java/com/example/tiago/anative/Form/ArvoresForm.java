package com.example.tiago.anative.Form;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tiago.anative.Controle.ControleArvore;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.List.SolicitacoesList;
import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.Modelo.Especie;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;

public class ArvoresForm extends AppCompatActivity {


    EditText id = null;
    EditText latitude = null;
    EditText longitude = null;
    EditText idade = null;
    EditText geocode = null;
    EditText especie = null;
    EditText proprietario = null;
    RadioButton ativosim = null;
    RadioButton ativonao = null;
    Button salvar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_form);



        id = (EditText) findViewById(R.id.id);
        latitude =(EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.logetude);
        idade = (EditText) findViewById(R.id.idade);
        geocode = (EditText) findViewById(R.id.geocode);
        especie = (EditText) findViewById(R.id.especie_idespecie);
        proprietario =(EditText) findViewById(R.id.proprietaro);
        ativosim = (RadioButton) findViewById(R.id.ativo_sim);
        ativonao = (RadioButton) findViewById(R.id.ativo_nao);
        salvar = (Button) findViewById(R.id.btn_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarArvore();
            }
        });


    }
    public void startList(View view) {

        Intent secondActivity = new Intent(this, MenuList.class);
        startActivity(secondActivity);

}




    private  void salvarArvore()
    {
        Arvore a = new Arvore();
        a.setId(Integer.parseInt(id.getText().toString()));
        a.setEnderecoGeoCode(geocode.getText().toString());
        Especie e = new Especie();
        e.setId(Integer.parseInt(especie.getText().toString()));
        a.setEspecie(e);
        a.setIdade(Integer.parseInt(idade.getText().toString()));
        a.setLatitude(latitude.getText().toString());
        a.setLongitude(longitude.getText().toString());
        Proprietario p = new Proprietario();
        p.setId(Integer.parseInt(proprietario.getText().toString()));
        a.setPropietario(p);
        a.setStatus("Ativo");
        if(ativosim.isSelected())
        {
            a.setStatus("Ativo");
        } else{
        if(ativonao.isSelected())
        {
            a.setStatus("Inativo");
        } }

        ControleArvore ca = new ControleArvore();
        try {
            if(ca.salvarArvore(a)) {
                Util.exibeMensagem("Registro salvo com sucesso", getApplicationContext());
            }else            Util.exibeMensagem("Erro na ao salvar", getApplicationContext());
        }catch (Exception exception)
        {
            Util.exibeMensagem("Erro na conexao", getApplicationContext());
        }

    }



}
