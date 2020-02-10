package com.example.desafioalphahurb.task;

import android.os.AsyncTask;

import com.example.desafioalphahurb.model.Hotel;
import com.example.desafioalphahurb.web.DadosHotel;
import com.example.desafioalphahurb.web.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotelAsyncTask extends AsyncTask<Object, Object, List<Hotel>> {

    //URL DA API
    private String URL_JSON = "https://www.hurb.com/search/api?q=buzios&page=1";

    //CRIA O OBJETO 'hoteis'
    private static List<Hotel> hoteis = new ArrayList<>();

    //CRIA O OBJETO 'dados'
    private DadosHotel dados = new DadosHotel();

    //EXECUTA A CONSULTA NA API
    @Override
    protected List<Hotel> doInBackground(Object... params) {
        cleanList();

        try {

            StringBuilder builder = new Json().getJsonFromAPI(URL_JSON);
            JSONObject jObject = new JSONObject(builder.toString());
            //CARREGA AS INORMAÇÕES DA API, A PARTIR DO NÓ 'results'
            JSONArray jsonArray = jObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //SE TIVER ESTREMAS, ENTÃO É UM HOTEL
                if (!jsonObject.isNull("stars")) {
                    Hotel hotel = dados.create(jsonObject);
                    hoteis.add(hotel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hoteis;
    }

    private void cleanList(){
        hoteis.clear();
    }
}