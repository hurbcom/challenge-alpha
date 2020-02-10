package com.example.desafioalphahurb.web;

import android.util.Log;

import com.example.desafioalphahurb.model.Pacote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DadosPacote {

    public Pacote create(JSONObject jsonPost) {
        Pacote pacote = null;
        try {
            //PERCORRE POR TODAS AS INFORMAÇÕES DA API, QUE SERÁ CARREGADO NO PACOTE 'task' CLASSE 'PacoteAsyncTask'
            String name = jsonPost.getString("name");
            String city = jsonPost.getJSONObject("address").getString("city");
            String state = jsonPost.getJSONObject("address").getString("state");
            String price = jsonPost.getJSONObject("price").getString("currentPrice");

            String _amenities = jsonPost.getString("amenities");
            JSONArray amenities_array = new JSONArray(_amenities);

            String _gallery = jsonPost.getString("gallery");
            JSONArray gallery_array = new JSONArray(_gallery);

            String image = "";
            for (int i = 0; i < gallery_array.length(); i++) {

                JSONObject childObject = gallery_array.getJSONObject(i);
                image = childObject.getString("url");
            }
            Log.d("url", image);


            String amenities = "";
            for (int i = 0; i < amenities_array.length(); i++) {

                //MONTA UM ARRAY, QUE SERA CARREGADO NA CLASSE, RecyclerViewAdapterPacote
                JSONObject childObject = amenities_array.getJSONObject(i);
                amenities = amenities + childObject.getString("name") + ";";
            }
            Log.d("amenities", amenities);

            pacote = new Pacote(name, city, state, price, image, amenities);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pacote;
    }
}