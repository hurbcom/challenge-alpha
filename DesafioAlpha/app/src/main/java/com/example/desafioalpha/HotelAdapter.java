package com.example.desafioalpha;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class HotelAdapter extends ArrayAdapter<Hotels>  {   //BaseAdapter {
    private final Context ctx;
    private final List<Hotels> hotel;

    // View Type for Separators
    private static final int ITEM_VIEW_TYPE_SEPARATOR = 0;
    // View Type for Regular rows
    private static final int ITEM_VIEW_TYPE_REGULAR = 1;
    // Types of Views that need to be handled
    // -- Separators and Regular rows --
    private static final int ITEM_VIEW_TYPE_COUNT = 2;

    public HotelAdapter(Context ctx,  List<Hotels> hotel) {
        super(ctx, R.layout.adapter_hotel, hotel);
        this.ctx = ctx;
        this.hotel = hotel;
    }

    @Override
    public int getCount() {
        return hotel != null ? hotel.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        boolean isSection = hotel.get(position).mIsSeparator;

        if (isSection) {
            return ITEM_VIEW_TYPE_SEPARATOR;
        }
        else {
            return ITEM_VIEW_TYPE_REGULAR;
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != ITEM_VIEW_TYPE_SEPARATOR;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view; //= LayoutInflater.from(ctx).inflate(R.layout.adapter_hotel, parent, false);

        int itemViewType = getItemViewType(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (itemViewType == ITEM_VIEW_TYPE_SEPARATOR) {
                // If its a section ?
                view = inflater.inflate(R.layout.hotel_section_header, null);
            }
            else {
                // Regular row
                view = inflater.inflate(R.layout.adapter_hotel, null);
            }
        }
        else {
            view = convertView;
        }

        //Atualiza o valor das views
        Hotels hotel1 = hotel.get(position);

        //Verifica se é separador
        if (itemViewType == ITEM_VIEW_TYPE_SEPARATOR) {
            // If separator

            TextView separadorView = (TextView) view.findViewById(R.id.separator);
            separadorView.setText("Estrelas: " + hotel1.stars);

        } else {

            //Faz findviewbyid das views
            ImageView img = (ImageView) view.findViewById(R.id.foto);
            TextView nome = (TextView) view.findViewById(R.id.nome);
            TextView cidade = (TextView) view.findViewById(R.id.cidade);
            TextView preco = (TextView) view.findViewById(R.id.preco);
            TextView estado = (TextView) view.findViewById(R.id.estado);
            TextView stars = (TextView) view.findViewById(R.id.stars);
            TextView amenidadeNome = (TextView) view.findViewById(R.id.amenidadeNome);
            TextView amenidadeCategoria = (TextView) view.findViewById(R.id.amenidadeCategoria);
            TextView amenidadeNome1 = (TextView) view.findViewById(R.id.amenidadeNome1);
            TextView amenidadeCategoria1 = (TextView) view.findViewById(R.id.amenidadeCategoria1);
            TextView amenidadeNome2 = (TextView) view.findViewById(R.id.amenidadeNome2);
            TextView amenidadeCategoria2 = (TextView) view.findViewById(R.id.amenidadeCategoria2);

            Glide.with(ctx)
                    .load(hotel1.foto)                     // Set image url
                    .into(img);

            nome.setText(hotel1.nome);
            cidade.setText(hotel1.cidade);
            preco.setText(hotel1.preco);
            estado.setText(hotel1.estado);
            stars.setText("Estrelas: " + hotel1.stars);
            amenidadeNome.setText(hotel1.amenidadeName[0]);
            amenidadeCategoria.setText(hotel1.amenidadeCategoria[0]);
            amenidadeNome1.setText(hotel1.amenidadeName[1]);
            amenidadeCategoria1.setText(hotel1.amenidadeCategoria[1]);
            amenidadeNome2.setText(hotel1.amenidadeName[2]);
            amenidadeCategoria2.setText(hotel1.amenidadeCategoria[2]);
        }

        //retorna a view do hotel
        return view;
    }


}
