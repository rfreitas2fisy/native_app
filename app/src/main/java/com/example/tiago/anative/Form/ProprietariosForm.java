package com.example.tiago.anative.Form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tiago.anative.Controle.ControleProprietario;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.Modelo.Cidade;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;

import java.util.ArrayList;

public class ProprietariosForm extends AppCompatActivity {

    EditText id = null;
    EditText nome = null;
    EditText identificacao = null;
    EditText rua = null;
    EditText cidade = null;
    EditText latitude = null;
    EditText longitude = null;
    Button salvar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietario_form);


       id = (EditText) findViewById(R.id.idproprietario);
       nome = (EditText) findViewById(R.id.nome);
       identificacao = (EditText) findViewById(R.id.identificacao);
       rua = (EditText) findViewById(R.id.end_rua);

       cidade = (EditText) findViewById(R.id.cidade_idcidade);
       latitude = (EditText) findViewById(R.id.latitude);
       longitude = (EditText) findViewById(R.id.longetude);
        salvar = (Button) findViewById(R.id.bt_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarProprietario();
            }
        });



        //update proprietario


        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if (params != null) {

            int idp = params.getInt("id");
            //System.out.println("o parametro que recebi foi"+idarvore);
            ControleProprietario ca = new ControleProprietario();
            Proprietario p = ca.obterProprietario(idp);
            id.setText("" + p.getId());
            nome.setText(""+p.getNome());
            identificacao.setText(""+p.getIdentificacao());
            rua.setText(""+p.getEnderecoRua());
            cidade.setText(""+p.getCidade().getId());
            latitude.setText(""+p.getLatitude());
            longitude.setText(""+p.getLongitude());


        }


    }


    public void salvarProprietario() {
        Proprietario p = new Proprietario();
        p.setId(Integer.parseInt(id.getText().toString()));
        p.setNome(nome.getText().toString());
        p.setIdentificacao(identificacao.getText().toString());
        p.setEnderecoRua(rua.getText().toString());
        Cidade c = new Cidade();
        c.setId(Integer.parseInt(cidade.getText().toString()));
        p.setCidade(c);
        p.setLatitude(latitude.getText().toString());
        p.setLongitude(longitude.getText().toString());

        ControleProprietario ca = new ControleProprietario();
        try {
            if (ca.salvarProprietario(p)) {
                Util.exibeMensagem("Registro salvo com sucesso", getApplicationContext());
            } else Util.exibeMensagem("Erro na ao salvar", getApplicationContext());
        } catch (Exception exception) {
            Util.exibeMensagem("Erro na conexao, tente novamente", getApplicationContext());

        }



    }



}
