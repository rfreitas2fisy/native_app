package com.example.tiago.anative.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiago.anative.Controle.ControleProprietario;
import com.example.tiago.anative.Form.ProprietariosForm;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;

import java.util.ArrayList;

public class ProprietariosList extends AppCompatActivity {


    GridView gridview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietarios_list);

        gridview= (GridView) findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getProprietariosGrid());

        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startProprietariosForm(View view) {

        Intent secondActivity = new Intent(this, ProprietariosForm.class);
        startActivity(secondActivity);
    }




    public String[] getProprietariosGrid()
    {
        try {
            ControleProprietario ca = new ControleProprietario();
            ArrayList<Proprietario> proprietarios = ca.obterTodosProprietarios();
            String[] retorno = new String[proprietarios.size()];
            for(int i = 0; i< proprietarios.size(); i++)
            {retorno[i] = "ID "+proprietarios.get(i).getId() + " \nNome: "+proprietarios.get(i).getNome()+ " \nCpf:: "+proprietarios.get(i).getIdentificacao();
            }
            return retorno;
        } catch (Exception exception){
            return new String[] {
                    "Erro conexão", "Erro conexão"};
        }

    }
}