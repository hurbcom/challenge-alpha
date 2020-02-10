package com.example.desafioalphahurb.layout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.model.Pacote;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class RecyclerViewPacoteAdapter extends RecyclerView.Adapter<RecyclerViewPacoteAdapter.ViewHolder> {

    private List<Pacote> listPacotes;
    private Context context;

    public RecyclerViewPacoteAdapter(List<Pacote> listPacotes, Context context){
        this.listPacotes = listPacotes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout_pacotes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewPacoteAdapter.ViewHolder holder, int position) {

        holder.name.setText(listPacotes.get(position).getName());
        holder.cidade_estado.setText(listPacotes.get(position).getCity() + ", " + listPacotes.get(position).getState().replace("Rio de Janeiro","RJ"));

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        holder.price.setText(nf.format(Double.parseDouble(listPacotes.get(position).getPrice())));

        //MONTA UM ARRAY, PARA LISTAR AS 'Amenities'
        String[] array = listPacotes.get(position).getAmenities().split(";");

        //TRATA O ERRO, CASO ALGUMA 'Amenities' SEJA NULA
        try {
            holder.amenities.setText(array[0] + " • " + array[1] + " • " + array[2]);
        } catch (Exception e) {
            Log.d("AMENITIES", e.getMessage());
        }

        // A BIBLIOTECA 'PICASSO' CARREA AS IMAGENS DOS PACOTES
        Picasso.with(context).load(listPacotes.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listPacotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView cidade_estado;
        private TextView price;
        private TextView amenities;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            cidade_estado = (TextView)itemView.findViewById(R.id.cidade_estado);
            price = (TextView)itemView.findViewById(R.id.price);
            amenities = (TextView)itemView.findViewById(R.id.amenities);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}