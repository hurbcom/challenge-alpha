package com.example.desafioalphahurb.web;

import android.util.Log;

import com.example.desafioalphahurb.model.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DadosHotel {

    public Hotel create(JSONObject jsonPost) {
        Hotel hotel = null;
        try {
            //PERCORRE POR TODAS AS INFORMAÇÕES DA API, QUE SERÁ CARREGADO NO PACOTE 'task' CLASSE 'HotelAsyncTask'
            String name = jsonPost.getString("name");
            String city = jsonPost.getJSONObject("address").getString("city");
            String state = jsonPost.getJSONObject("address").getString("state");
            String price = jsonPost.getJSONObject("price").getString("current_price");
            String description = jsonPost.getString("description");
            Integer stars = jsonPost.getInt("stars");
            String image = jsonPost.getString("image");

            String _amenities = jsonPost.getString("amenities");
            JSONArray amenities_array = new JSONArray(_amenities);

            String amenities = "";
            for (int i = 0; i < amenities_array.length(); i++) {
                //MONTA UM ARRAY, QUE SERA CARREGADO NA CLASSE, RecyclerViewAdapter
                JSONObject childObject = amenities_array.getJSONObject(i);
                amenities = amenities + childObject.getString("name") + ";";
            }

            hotel = new Hotel(name, city, state, price, description, stars, image, amenities);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hotel;
    }
}