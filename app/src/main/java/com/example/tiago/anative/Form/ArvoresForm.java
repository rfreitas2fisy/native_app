package com.example.tiago.anative.Form;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tiago.anative.Controle.ControleArvore;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.List.SolicitacoesList;
import com.example.tiago.anative.MapsActivity;
import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.Modelo.Especie;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArvoresForm extends AppCompatActivity implements LocationListener {


    EditText id = null;
    EditText latitude = null;
    EditText longitude = null;
    EditText idade = null;
    EditText geocode = null;
    EditText especie = null;
    EditText proprietario = null;
    CheckBox ativo = null;

    Button salvar = null;
    Button btGps = null;
    Button verMapa = null;
    LocationManager lm = null; // usado para pegar posição gps
    boolean atualizouLoc = false; //usado para vefificar se a posição gps foi atualizad
    Arvore v=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_form);


        id = (EditText) findViewById(R.id.id);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.logetude);
        idade = (EditText) findViewById(R.id.idade);
        geocode = (EditText) findViewById(R.id.geocode);
        especie = (EditText) findViewById(R.id.especie_idespecie);

        proprietario = (EditText) findViewById(R.id.proprietaro);
        salvar = (Button) findViewById(R.id.btn_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarArvore();
            }
        });
        btGps = (Button) findViewById(R.id.bt_gps);
        btGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attLocalizacao();
            }
        });
        ativo = (CheckBox) findViewById(R.id.cb_ativo);
        verMapa = (Button) findViewById(R.id.bt_ver_mapa);
        verMapa.setClickable(false);
        verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                Intent secondActivity = new Intent(ArvoresForm.this, MapsActivity.class);
                startActivity(secondActivity);



                ArrayList<String> parametros = new ArrayList<String>(); //pos 0 = lat pos1 = long pos2 = desc arv

                    parametros.add(0, latitude.getText().toString());
                    parametros.add(1, longitude.getText().toString());
                try {
                    ControleArvore ca = new ControleArvore();
                    Arvore a = ca.consultarArvore(Integer.parseInt(id.getText().toString()));
                    parametros.add(a.getGoogleMapsData());

                } catch (Exception ex) {
                    parametros.add(2, "Não foi possivel consultar o proprietario");

                }
                    params.putStringArrayList("dados",parametros);


                secondActivity.putExtras(params);
                startActivity(secondActivity);
            }
        });


        //update arvore


            Intent intent = getIntent();
            Bundle params = intent.getExtras();

            if(params!=null)
            {
                verMapa.setClickable(true);
                int idarvore = params.getInt("id");
                //System.out.println("o parametro que recebi foi"+idarvore);
                ControleArvore ca = new ControleArvore();
                 v = ca.consultarArvore(idarvore);
                id.setText(""+v.getId());
                idade.setText(""+v.getIdade());
                longitude.setText(""+v.getLongitude());
                latitude.setText(""+v.getLatitude());
                geocode.setText(""+v.getEnderecoGeoCode());
                especie.setText(""+v.getEspecie().getId());
                proprietario.setText(""+v.getPropietario().getId());
                ativo.setSelected(true);
                if(v.getStatus().equals("Ativo"))
                {
                    System.out.println("setei no ativo");
                    ativo.setSelected(true);
                } else {
                    System.out.println("setei como inativo");
                    ativo.setSelected(false);
                }




            //setContentView(textView);
        }

    }

    public void startList(View view) {

        Intent secondActivity = new Intent(this, MenuList.class);
        startActivity(secondActivity);

    }


    public void attLocalizacao() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); //esse metodo só pega a ultima localizacao conhecida, não é o caso
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this); // pedir atualizacao localizacao e aguardar
            Util.exibeMensagem("Pedido atualizacao de GPS", getApplicationContext());


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ArvoresForm.this
                    , Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else { //pedir permissão em tempo de execução
                ActivityCompat.requestPermissions(ArvoresForm.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }

            Util.exibeMensagem("Erro de permissão", getApplicationContext());
        }


    }


    private void salvarArvore() {
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
        if (ativo.isSelected()) {
            a.setStatus("Ativo");
        } else {
            a.setStatus("Inativo");

        }

        ControleArvore ca = new ControleArvore();
        try {
            if (ca.salvarArvore(a)) {
                Util.exibeMensagem("Registro salvo com sucesso", getApplicationContext());
            } else Util.exibeMensagem("Erro na ao salvar", getApplicationContext());
        } catch (Exception exception) {
            Util.exibeMensagem("Erro na conexao", getApplicationContext());
        }

    }


    @Override
    public void onLocationChanged(Location location) { //garante que foi atualizada a loc
        Util.exibeMensagem("Location update!", getApplicationContext());
        Util.exibeMensagem("" + location.getLongitude() + " , " + location.getLatitude(), getApplicationContext());
        if (location != null) {
            //  Util.exibeMensagem("Location update!", getApplicationContext());
            latitude.setText("" + location.getLatitude());
            longitude.setText("" + location.getLongitude());


            Geocoder geocoder = new Geocoder(ArvoresForm.this);
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String gc = addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2) + ",  "  + addresses.get(0).getAddressLine(3) + ", ";
                geocode.setText("" + gc);
            } catch (IOException e) {
                Util.exibeMensagem("Erro: "+e, getApplicationContext());
                geocode.setText("" + e);
                 e.printStackTrace();
            }
            atualizouLoc = true;


            stopLocationUpdates();            // parar de pedir atualizacao


        } else
            Util.exibeMensagem("Sem latitude e longitude", getApplicationContext());

    }

    public void stopLocationUpdates() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            lm.removeUpdates(ArvoresForm.this);

            Util.exibeMensagem("Parei de pedir atualização", getApplicationContext());


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ArvoresForm.this
                    , Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(ArvoresForm.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }

            Util.exibeMensagem("Erro de permissão", getApplicationContext());
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Util.exibeMensagem("Statuts changed!", getApplicationContext());
    }

    @Override
    public void onProviderEnabled(String provider) {
        Util.exibeMensagem("Provider enabled!", getApplicationContext());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Util.exibeMensagem("Provider disabled!", getApplicationContext());
    }
}
