package com.example.desafioalphahurb.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//CLASSE CRIADA PARA VERIFICAR SE O DISPOSITIVO ESTA CONECTADO A INTERNET
public class Conexao {
    public static boolean IsConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
