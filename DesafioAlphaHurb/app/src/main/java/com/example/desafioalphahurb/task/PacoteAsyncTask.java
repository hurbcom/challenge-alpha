package com.example.desafioalphahurb.task;

import android.os.AsyncTask;

import com.example.desafioalphahurb.model.Pacote;
import com.example.desafioalphahurb.web.DadosPacote;
import com.example.desafioalphahurb.web.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PacoteAsyncTask extends AsyncTask<Object, Object, List<Pacote>> {

    //URL DA API
    private String URL_JSON = "https://www.hurb.com/search/api?q=buzios&page=1";

    //CRIA O OBJETO 'pacotes'
    private static List<Pacote> pacotes = new ArrayList<>();

    //CRIA O OBJETO 'dados'
    private DadosPacote dados = new DadosPacote();

    //EXECUTA A CONSULTA NA API
    @Override
    protected List<Pacote> doInBackground(Object... params) {
        cleanList();

        try {

            StringBuilder builder = new Json().getJsonFromAPI(URL_JSON);
            JSONObject jObject = new JSONObject(builder.toString());
            //CARREGA AS INORMAÇÕES DA API, A PARTIR DO NÓ 'results'
            JSONArray jsonArray = jObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //SE NÃO TIVER ESTRELA, ENTÃO É UM PACOTE
                if(jsonObject.isNull("stars")) {
                    Pacote pacote = dados.create(jsonObject);
                    pacotes.add(pacote);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pacotes;
    }

    private void cleanList(){
        pacotes.clear();
    }
}