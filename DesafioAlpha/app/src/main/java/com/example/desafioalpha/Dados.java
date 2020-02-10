package com.example.desafioalpha;

import android.content.Context;
import android.util.Log;

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
import java.util.List;

public class Dados {

    private RequestQueue requestQueue;

    //Lista para guardar os valores recebidos do JSON e popular o Listview
    public List<Hotels> Hotels;
    public Context ctx;

    public void jsonParse() {
        requestQueue = Volley.newRequestQueue(ctx);

        String url = "https://www.hurb.com/search/api?q=buzios&page=1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Deve mostrar nome, preço, cidade, estado, uma foto e três amenidades.
                            //TODO - colocar lógica busca cidade, state, amemities e montar layout para a lista dos dados
                            JSONArray jsonArray = response.getJSONArray("results");

                            String final1 = "";
                            String preco = "";
                            String cidade = "";
                            String state = "";
                            String foto = "";
                            String[] amenidadeName = new String[3];
                            String[] amenidadeCategoria = new String[3];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hotelsJSON = jsonArray.getJSONObject(i);

                                String nome = hotelsJSON.getString("name");

                                //Percorre objeto price para buscar o ammount e o currency
                                String price = hotelsJSON.getString("price");
                                JSONObject price1 = hotelsJSON.getJSONObject("price");
                                preco = price1.getString("amount") + " " + price1.get("currency");

                                //Percorre objeto address para buscar a cidade
                                String address = hotelsJSON.getString("address");
                                JSONObject address1 =  hotelsJSON.getJSONObject("address");
                                cidade = address1.getString("city");
                                state = address1.getString("state");

                                //Percorre array amenities para buscar 3 amenidades
                                String amenities = hotelsJSON.getString("amenities");
                                JSONArray arrAmenidades = hotelsJSON.getJSONArray("amenities");

                                for (int l=0; l < 3; l++) {
                                    JSONObject amen = arrAmenidades.getJSONObject(l);
                                    amenidadeName[l] = amen.getString("name");
                                    amenidadeCategoria[l] = amen.getString("category");
                                }

                                Integer stars = hotelsJSON.getInt("stars");

                                //Percorre objeto gallery para buscar a url da foto
                                foto = hotelsJSON.getString("url");

                                //Tarefa 1: Emitir um log dos dados recebidos
                                String log_dados_recebidos = nome+" "+preco+" " +
                                        cidade+" "+ state + " " +
                                        amenidadeName[0] + " "+ amenidadeCategoria[0] + " " +
                                        amenidadeName[1] + " "+ amenidadeCategoria[1] + " " +
                                        amenidadeName[2] + " "+ amenidadeCategoria[2] + " "+ " " +
                                        stars + " " + foto;
                                String TAG_LOG = "MeuAplicativo";
                                Log.v(TAG_LOG, log_dados_recebidos);

                                //Tarefa 2: Guardar os dados na lista Hotels
                                Hotels = new ArrayList<Hotels>();
                                Hotels.add(new Hotels(nome, preco, cidade, state,
                                        amenidadeName,
                                        amenidadeCategoria, stars, foto, true) );

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
