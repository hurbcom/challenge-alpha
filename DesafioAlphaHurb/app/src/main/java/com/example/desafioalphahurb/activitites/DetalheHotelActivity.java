package com.example.desafioalphahurb.activitites;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.fragment.HoteisFragment;
import com.example.desafioalphahurb.model.Hotel;
import com.example.desafioalphahurb.task.HotelAsyncTask;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DetalheHotelActivity extends AppCompatActivity {

    ActionBar actionBar;
    List<Hotel> Hotels = new ArrayList<>();

    //VARIAVEL CRIADA PARA RECEBER O INDICE DOS HOTEIS
    Integer index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_hotel);

        carregarJsonHoteis();

        //RECUPERA O INDICE DOS HOTEIS, PARA APRESENTAR AS INFORMAÇÕES DO HOTEL SELECIONADO NA ACTIVITY/FRAGMENT ANTERIOR
        Bundle args = getIntent().getExtras();
        index = args.getInt("index");

        actionBar = getSupportActionBar();
        actionBar.setTitle(Hotels.get(index).getName());
        actionBar.setDisplayHomeAsUpEnabled(true);

        //CARREGA O MÉTODO COM AS INFORMAÇÕES DO HOTEL
        carregarInfoDetalhesHotel();
    }

    //MONTA O MÉTODO COM AS INFORMAÇÕES DO HOTEL, NAS 'TextView' 'ImageView' 'RatingBar'
    public void carregarInfoDetalhesHotel() {

        ImageView gallery = (ImageView) findViewById(R.id.gallery);
        Picasso.with(getBaseContext()).load(Hotels.get(index).getImage()).into(gallery);

        TextView nome = (TextView) findViewById(R.id.nome_detalhes);
        nome.setText(Hotels.get(index).getName());

        TextView city_state = (TextView) findViewById(R.id.cidade_detalhes);
        city_state.setText(Hotels.get(index).getCity() + ", " + Hotels.get(index).getState().replace("Rio de Janeiro","RJ"));

        RatingBar stars = (RatingBar) findViewById(R.id.stars_detalhes);
        stars.setRating(Hotels.get(index).getStars());

        TextView preco = (TextView) findViewById(R.id.preco_detalhes);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        preco.setText(nf.format(Double.parseDouble(Hotels.get(index).getPrice())));

        TextView descricao = (TextView) findViewById(R.id.descricao_detalhes);
        descricao.setText(Hotels.get(index).getDescription());

        TextView amenities = (TextView) findViewById(R.id.amenities_dados_detalhes);
        amenities.setText(Hotels.get(index).getAmenities().
                replace(";",", "));

    }

    //MÉTODO QUE CARREGA O JSON
    public void carregarJsonHoteis() {
        try {
            Hotels = new HotelAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //CRIA O BOTÃO VOLTAR PARA ACTIVITY/FRAGMENT ANTERIOR
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}