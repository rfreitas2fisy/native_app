package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Usuario;

import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 18/06/2016.
 */
public class ControleUsuario {
    public boolean logar(Usuario usuario) {
        boolean ret = false;

        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=autenticar&user="+usuario.getLogin()+"&pass="+usuario.getSenha()+"", "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

if (retornoWeb.equalsIgnoreCase("0")) // nao encontrou um registro com o nome de usuario e senha informados
{
    return false;
} else return true;
    }


}
