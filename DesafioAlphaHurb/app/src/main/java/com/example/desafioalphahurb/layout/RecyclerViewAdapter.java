package com.example.desafioalphahurb.layout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.function.CompararStars;
import com.example.desafioalphahurb.model.Hotel;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Hotel> listHotel;
    private Context context;

    public RecyclerViewAdapter(List<Hotel> listHotel, Context context){
        this.listHotel = listHotel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        //ORDERNAR A LISTA PELA ORDEM DECRESCENTE DE ESTRELAS DO HOTEL
        Collections.sort(listHotel, new CompararStars());

        holder.name.setText(listHotel.get(position).getName());
        holder.cidade_estado.setText(listHotel.get(position).getCity() + ", " + listHotel.get(position).getState().replace("Rio de Janeiro","RJ"));

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        holder.price.setText(nf.format(Double.parseDouble(listHotel.get(position).getPrice())));
        holder.stars.setNumStars(listHotel.get(position).getStars());

        //MONTA UM ARRAY, PARA LISTAR AS 'Amenities'
        String[] array = listHotel.get(position).getAmenities().split(";");

        //TRATA O ERRO, CASO ALGUMA 'Amenities' SEJA NULA
        try {
            holder.amenities.setText(array[0] + " • " + array[1] + " • " + array[2]);
        } catch (Exception e) {
            Log.d("AMENITIES", e.getMessage());
        }

        // A BIBLIOTECA 'PICASSO' CARREA AS IMAGENS DOS HOTEIS
        Picasso.with(context).load(listHotel.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listHotel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView cidade_estado;
        private TextView price;
        private RatingBar stars;
        private TextView amenities;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            cidade_estado = (TextView)itemView.findViewById(R.id.cidade_estado);
            price = (TextView)itemView.findViewById(R.id.price);
            stars = (RatingBar) itemView.findViewById(R.id.stars);
            amenities = (TextView)itemView.findViewById(R.id.amenities);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}