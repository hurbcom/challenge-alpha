package com.example.desafioalpha;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private ListView lista;
    private List<Hotels> hotelsLista;
    private List<Hotels> hotelsListaOrdenada;

    //Lista para guardar os valores recebidos do JSON e popular o Listview
    private List<Hotels> Hotels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mostrar a Action bar e o título desta activity
        getSupportActionBar().show();

        //Titulo da Activity
        getSupportActionBar().setTitle((android.text.Html.fromHtml("<font color=\"WHITE\">"
                + "HOTÉIS" + "</font>")));


        //Popular a ListView com os dados do JSON montado acima
        lista = (ListView) findViewById(R.id.lista);

        requestQueue = Volley.newRequestQueue(this);
        Dados dados = new Dados();

        dados.ctx = this;
        dados.lista = lista;
        dados.jsonParse();

    }

}