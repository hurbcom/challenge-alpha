package com.example.desafioalphahurb.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.activitites.BuscarHotelActivity;
import com.example.desafioalphahurb.model.Hotel;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BuscarHotelAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Hotel> arraylist;

    //CONSTRUTOR QUE PROCESSA OS DADOS DA PESQUISA
    public BuscarHotelAdapter(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Hotel>();
        this.arraylist.addAll(BuscarHotelActivity.hotelNameArrayList);
    }

    //AUXILIA NO PROCESSAMENTO DOS DADOS DOS HOTEIS
    public class ViewHolder {
        TextView name;
        TextView city;
        TextView price;
        RatingBar stars;
        ImageView image;
    }

    @Override
    public int getCount() {
        return BuscarHotelActivity.hotelNameArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return BuscarHotelActivity.hotelNameArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //PROCESSA DOS DADOS DA CLASSE 'ViewHolder'
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.hoteis, null);

            holder.name = (TextView) view.findViewById(R.id.name);
            holder.city = (TextView) view.findViewById(R.id.city);
            holder.price = (TextView) view.findViewById(R.id.price);
            holder.stars = (RatingBar) view.findViewById(R.id.stars);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        //MONTA UM ARRAY STRING COM LIMITADOR '_limitador;' PARA APRESENTAR CADA INFORMAÇÃO DO HOTEL NO 'ListView'
        String resultadoString = BuscarHotelActivity.hotelNameArrayList.get(position).getHotelNames();
        String[] limitador = resultadoString.split("_limitador;");

        //CONVERTE A STRING PARA O FORMADO DE MOEDA/REAL
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // SET OS RESULTODOS DO TEXTVIEW
        holder.name.setText(limitador[0]);
        holder.city.setText(limitador[1]);
        holder.price.setText(nf.format(Double.parseDouble(limitador[2])));
        holder.stars.setNumStars(Integer.parseInt(limitador[3]));
        Picasso.with(mContext).load(limitador[4]).into(holder.image);

        return view;
    }

    // FAZ O FILTRO DOS DADOS
    public void filtrar(String texto) {
        texto = texto.toLowerCase(Locale.getDefault());
        BuscarHotelActivity.hotelNameArrayList.clear();
        if (texto.length() == 0) {
            BuscarHotelActivity.hotelNameArrayList.addAll(arraylist);
        } else {
            for (Hotel wp : arraylist) {
                if (wp.getHotelNames().toLowerCase(Locale.getDefault()).contains(texto)) {
                    BuscarHotelActivity.hotelNameArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}