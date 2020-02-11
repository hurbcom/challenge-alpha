package com.example.desafioalpha;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DadosTest1  {
    private RequestQueue requestQueue;
    public ListView lista;
    private List<Hotels> hotelsLista;
    private List<Hotels> hotelsListaOrdenada;
    protected  Context ctx = null;
    MainActivity activity;

    //Lista para guardar os valores recebidos do JSON e popular o Listview
    public List<Hotels> Hotels;

    public DadosTest1(){
        super();
    }

    @Test
    public void jsonParse() {

        ctx = activity.getApplicationContext();
        requestQueue = Volley.newRequestQueue(ctx);

        String url = "https://www.hurb.com/search/api?q=buzios&page=1";
        hotelsLista = new ArrayList<Hotels>();
        hotelsListaOrdenada = new ArrayList<Hotels>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Assert.assertEquals(response,"ok");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                //displaying the error in toast if occurred
                Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    public static class Sortbyroll implements Comparator<Hotels>
    {
        // Usado para ordenar pelo número de estrelas
        // campo stars
        public int compare(Hotels a, Hotels b)
        {
            return b.stars - a.stars;
        }
    }
}