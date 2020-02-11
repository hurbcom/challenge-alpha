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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Classe para receber o JSON, ordenar e agrupar por número de estrelas e mostrar na lista.
public class Dados {

    private RequestQueue requestQueue;
    public ListView lista;
    private List<Hotels> hotelsLista;
    private List<Hotels> hotelsListaOrdenada;

    //Lista para guardar os valores recebidos do JSON e popular o Listview
    public List<Hotels> Hotels;
    public Context ctx;

    public void jsonParse() {
        requestQueue = Volley.newRequestQueue(ctx);

        String url = "https://www.hurb.com/search/api?q=buzios&page=1";
        hotelsLista = new ArrayList<Hotels>();
        hotelsListaOrdenada = new ArrayList<Hotels>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Deve mostrar nome, preço, cidade, estado, uma foto e três amenidades.
                            //TODO - colocar lógica busca cidade, state, amemities e montar layout para a lista dos dados
                            JSONArray jsonArray = response.getJSONArray("results");

                            String final1 = "";
                            String nome = "";
                            String preco = "";
                            String currency = "";
                            String cidade = "";
                            String state = "";
                            String foto = "";
                            String[] amenidadeName = new String[3];
                            String[] amenidadeCategoria = new String[3];
                            String[] amenidadeNameOrd = new String[3];
                            String[] amenidadeCategoriaOrd = new String[3];
                            String[] amenidadeNameOrdVazia = new String[3];
                            String[] amenidadeCategoriaOrdVazia = new String[3];
                            Integer stars = 0;
                            boolean isSeparator = false;
                            Integer estrelas = 0;


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hotelsJSON = jsonArray.getJSONObject(i);

                                nome = hotelsJSON.getString("name");

                                //Percorre objeto price para buscar o ammount e o currency
                                String price = hotelsJSON.getString("price");
                                JSONObject price1 = hotelsJSON.getJSONObject("price");
                                preco = price1.getString("amount");
                                try {
                                    if (price1.getString("currency") != null) {
                                        currency = price1.getString("currency");
                                    } else {
                                        currency = " ";
                                    }
                                }
                                catch (JSONException jj) {
                                    currency = " ";
                                }
                                if (preco != null) {
                                    preco = preco  + " " + currency;
                                }

                                //Percorre objeto address para buscar a cidade
                                String address = hotelsJSON.getString("address");
                                JSONObject address1 =  hotelsJSON.getJSONObject("address");
                                cidade = address1.getString("city");
                                state = address1.getString("state");

                                //Percorre array amenities para buscar 3 amenidades
                                String amenities = hotelsJSON.getString("amenities");
                                JSONArray arrAmenidades = hotelsJSON.getJSONArray("amenities");

                                if (arrAmenidades.length() > 0) {
                                    int x = 0;
                                    int limite = 0;
                                    if (arrAmenidades.length() < 3) {
                                        limite = arrAmenidades.length()-1;
                                    } else {
                                        limite = 2;
                                    }
                                    for (x = 0; x <= limite; x++) {
                                        JSONObject amen = arrAmenidades.getJSONObject(x);
                                        amenidadeName[x] = amen.getString("name");
                                        amenidadeCategoria[x] = amen.getString("category");
                                        if (x == limite) {
                                            break;
                                        }
                                    }
                                }

                                try {
                                    if (hotelsJSON.getInt("stars") > 0) {
                                        stars = hotelsJSON.getInt("stars");

                                    } else
                                        stars = 0;
                                }
                                catch (JSONException j) {
                                    stars = 0;
                                }

                                //Percorre objeto gallery para buscar a url da foto
                                String gallery = hotelsJSON.getString("gallery");
                                JSONArray arrGallery = hotelsJSON.getJSONArray("gallery");

                                if (arrGallery.length() > 0) {
                                    JSONObject aFoto = arrGallery.getJSONObject(0);
                                    foto = aFoto.getString("url");
                                }


                                //Tarefa 2: Guardar os dados na lista Hotels
                                hotelsLista.add(new Hotels(nome, preco, cidade, state,
                                        amenidadeName,
                                        amenidadeCategoria, stars, foto, false));
                            }

                            //Ordenar pelo número de estrelas
                            Collections.sort(hotelsLista, new Sortbyroll());

                            //depois de ordenar agrupa pelo número de estrelas
                            Integer tamanhoLista = hotelsLista.size();
                            Integer position = 0;
                            for (Integer grupo=0; grupo <= tamanhoLista; grupo++) {
                                isSeparator = false;

                                // If it is the first item then need a separator
                                try {
                                    nome = hotelsLista.get(grupo).nome;
                                    preco = hotelsLista.get(grupo).preco;
                                    cidade = hotelsLista.get(grupo).cidade;
                                    state = hotelsLista.get(grupo).estado;
                                    stars = hotelsLista.get(grupo).stars;
                                    foto = hotelsLista.get(grupo).foto;
                                    amenidadeNameOrd = hotelsLista.get(grupo).amenidadeName;
                                    amenidadeCategoriaOrd = hotelsLista.get(grupo).amenidadeCategoria;
                                }
                                catch (IndexOutOfBoundsException iob) {
                                    stars = 0;
                                }

                                if (position == 0) {
                                    isSeparator = true;
                                    estrelas = stars;
                                }

                                //verifica se a estrela atual é diferente da estrela anterior
                                if (stars != estrelas) {
                                    isSeparator= true;
                                    estrelas = stars;
                                }
                                //Tarefa 2: Guardar os dados na lista Hotels
                                if (isSeparator == false) {
                                    hotelsListaOrdenada.add(new Hotels(nome,
                                            preco,
                                            cidade,
                                            state,
                                            amenidadeNameOrd,
                                            amenidadeCategoriaOrd,
                                            stars,
                                            foto,
                                            isSeparator));
                                } else {
                                    //1 - Salva na lista a linha do separador sem os outros valores
                                    nome = "";
                                    preco = "";
                                    cidade = "";
                                    state = "";
                                    foto = "";

                                    for (int am1=0;am1<=2; am1++) {
                                        amenidadeNameOrdVazia[am1] = "";
                                        amenidadeCategoriaOrdVazia[am1] = "";
                                    }

                                    hotelsListaOrdenada.add(new Hotels(nome, preco  , cidade, state,
                                            amenidadeNameOrdVazia,
                                            amenidadeCategoriaOrdVazia, stars, foto, isSeparator));

                                    //2 - Salva o registro lido com separador false
                                    try {
                                        nome = hotelsLista.get(grupo).nome;
                                        preco = hotelsLista.get(grupo).preco;
                                        cidade = hotelsLista.get(grupo).cidade;
                                        state = hotelsLista.get(grupo).estado;
                                        stars = hotelsLista.get(grupo).stars;
                                        foto = hotelsLista.get(grupo).foto;
                                        //amenidadeNameOrd = hotelsLista.get(grupo).amenidadeName;
                                        //amenidadeCategoriaOrd = hotelsLista.get(grupo).amenidadeCategoria;
                                    }
                                    catch (IndexOutOfBoundsException iob) {
                                        stars = 0;
                                    }
                                    hotelsListaOrdenada.add(new Hotels(nome,
                                            preco,
                                            cidade,
                                            state,
                                            amenidadeNameOrd,
                                            amenidadeCategoriaOrd,
                                            stars,
                                            foto,
                                            false));
                                }
                                position++;
                            }

                            //Tarefa 1: Emitir um log dos dados recebidos
                            for (int mm=0;mm <= hotelsListaOrdenada.size()-1;mm++) {
                                String log_dados_recebidos = hotelsListaOrdenada.get(mm).nome + " " +
                                        hotelsListaOrdenada.get(mm).preco + " " +
                                        hotelsListaOrdenada.get(mm).cidade + " " + state + " " +
                                        hotelsListaOrdenada.get(mm).amenidadeName + " " +
                                        hotelsListaOrdenada.get(mm).amenidadeCategoria + " " +
                                        hotelsListaOrdenada.get(mm).stars + " " +
                                        hotelsListaOrdenada.get(mm).foto + " " +
                                        hotelsListaOrdenada.get(mm).mIsSeparator;

                                String TAG_LOG = "MeuAplicativo";
                                Log.v(TAG_LOG, log_dados_recebidos);
                            }

                            //creating custom adapter object
                            HotelAdapter mAdapter = new HotelAdapter(ctx,hotelsListaOrdenada);

                            //adding the adapter to list view
                            lista.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
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

    class Sortbyroll implements Comparator<Hotels>
    {
        // Usado para ordenar pelo número de estrelas
        // campo stars
        public int compare(Hotels a, Hotels b)
        {
            return b.stars - a.stars;
        }
    }
}
