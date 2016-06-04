package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Proprietario;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 21/05/2016.
 */
public class ControleProprietario {


    public ArrayList<Proprietario> obterTodosProprietarios() {

        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listAllProprietarios", "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerProprietariosWeb(retornoWeb);

    }

    public  Proprietario obterProprietario(int id)
    {
        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listProprietario&id="+id+"").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerProprietariosWeb(retornoWeb).get(0);

    }

}
