package com.example.desafioalphahurb.activitites;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.function.CompararStars;
import com.example.desafioalphahurb.layout.BuscarHotelAdapter;
import com.example.desafioalphahurb.model.Hotel;
import com.example.desafioalphahurb.task.HotelAsyncTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BuscarHotelActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ActionBar actionBar;

    public static ArrayList<Hotel> hotelNameArrayList = new ArrayList<Hotel>();

    //DECRESCENTE E OUTRA PARA PEGAR OS INDICES DESSES HOTEIS
    List<Hotel> Hotels = new ArrayList<>();
    private List<Hotel> listHoteis;

    private ListView listView;
    private BuscarHotelAdapter adapter;
    private SearchView editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_hotel);

        actionBar = getSupportActionBar();
        actionBar.hide();

        listView = (ListView) findViewById(R.id.listViewBuscar);

        carregarJsonBuscarHotel();

        listHoteis = new ArrayList<>();

        //TRATA O ERRRO CASO ALGUM DADO SEJA NULO
        try {
            carregarHoteis();
        } catch (Exception e) {
            Log.d("Erro", e.getMessage());
        }

        //EXECUTA O MÉTODO 'CompararStars', QUE ORDENA AS ESTRELAS DOS HOTEIS);
        Collections.sort(listHoteis, new CompararStars());

        hotelNameArrayList = new ArrayList<>();

        Log.d("Json Buscar", listHoteis.toString());

        for (int i = 0; i < listHoteis.size(); i++) {

            Hotel hoteis = new Hotel(
                    listHoteis.get(i).getName() + " _limitador; " +
                            listHoteis.get(i).getCity() + ", " + listHoteis.get(i).getState().replace("Rio de Janeiro","RJ")  + "_limitador;" +
                            listHoteis.get(i).getPrice() + "_limitador;" +
                            listHoteis.get(i).getStars() + "_limitador;" +
                            listHoteis.get(i).getImage() + "_limitador;");

            // CONSTROI AS STRINGS NO ARRAY
            hotelNameArrayList.add(hoteis);
        }

        // PASSA OS RESULTADOS DPS DADOS PARA A CLASSE 'BuscarHotelAdapter'
        adapter = new BuscarHotelAdapter(getBaseContext());



        // VINCULA O ADAPDADOR AO 'ListView'
        listView.setAdapter(adapter);

        // LOCALIZA O 'EditText' no 'ListView'
        editsearch = (SearchView) findViewById(R.id.buscar_hotel);
        editsearch.setOnQueryTextListener(this);

        //BOTÃO PARA FECHAR TELA DE BUSCA
        ImageButton imgBtn = (ImageButton) findViewById(R.id.btn_fechar);
        imgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    //MÉTODO CRIADO PARA MONTAR O ARRAY, COM OS HOTEIS
    private void carregarHoteis(){

        Hotel hotel;

        for(int i=0; i <= Hotels.size() - 1; i++) {
            hotel = new Hotel(
                    i,
                    Hotels.get(i).getName(),
                    Hotels.get(i).getCity(),
                    Hotels.get(i).getState(),
                    Hotels.get(i).getPrice(),
                    Hotels.get(i).getStars(),
                    Hotels.get(i).getImage(),
                    Hotels.get(i).getAmenities());

            listHoteis.add(hotel);
        }
    }

    //MÉTODO QUE CARRAGA O JSON
    public void carregarJsonBuscarHotel() {
        try {
            Hotels = new HotelAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //MÉTODO CRIADO PARA IMPLEMENTAR A BUSCA
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    //MÉTODO CRIADO PARA IMPLEMENTAR A BUSCA
    @Override
    public boolean onQueryTextChange(String novoTexto) {
        String texto = novoTexto;
        adapter.filtrar(texto);
        return false;
    }
}
