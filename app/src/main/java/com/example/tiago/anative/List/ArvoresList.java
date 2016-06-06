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

import com.example.tiago.anative.Controle.ControleArvore;
import com.example.tiago.anative.Form.ArvoresForm;
import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.R;

import java.util.ArrayList;

public class ArvoresList extends AppCompatActivity {


    GridView gridview = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_list);
        gridview= (GridView) findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getArvoresGrid());

        gridview.setAdapter(adapter);
    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v,
                                int position, long id) {
            Toast.makeText(getApplicationContext(),
                    ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
        }
    });
    }
    public void startArvoresForm(View view) {

        Intent secondActivity = new Intent(this, ArvoresForm.class);
        startActivity(secondActivity);
    }

    public String[] getArvoresGrid()
    {
try {
    ControleArvore ca = new ControleArvore();
    ArrayList<Arvore> arvores = ca.obterTodasArvores();
    String[] retorno = new String[arvores.size()];
    for(int i = 0; i< arvores.size(); i++)
    {retorno[i] = "ID "+arvores.get(i).getId() + " \nProp: "+arvores.get(i).getPropietario().getNome()+ " \nProp: "+arvores.get(i).getLatitude()+""+ " \nGPS: "+arvores.get(i).getLongitude();

    }
    return retorno;
} catch (Exception exception){
    return new String[] {
            "Erro conexão", "Erro conexão"};
}

    }

}
