package com.example.desafioalphahurb.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.layout.RecyclerViewPacoteAdapter;
import com.example.desafioalphahurb.model.Pacote;
import com.example.desafioalphahurb.task.PacoteAsyncTask;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PacotesFragment extends Fragment {

    List<Pacote> Pacotes = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerViewPacoteAdapter recyclerViewPacoteAdapter;
    private List<Pacote> listPacotes;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pacotes, container, false);

        carregarJsonPacotes();

        Log.d("Pacotes", String.valueOf(Pacotes.size()));

        listPacotes = new ArrayList<>();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewPacotes);

        recyclerViewPacoteAdapter = new RecyclerViewPacoteAdapter(listPacotes, getContext());

        //TRATA O ERRRO CASO ALGUM DADO SEJA NULO
        try {
                carregarPacotes();
        } catch (Exception e) {
            Log.d("Erro", e.getMessage());
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewPacoteAdapter);

        Log.d("JSON PACOTES", listPacotes.toString());

        // SE A RESPOSTA DA API FOR VAZIA, IMPRIMIMOS UMA MENSAGEM NO TEXTVIEW
        TextView mensagem = (TextView) root.findViewById(R.id.mensagem);
        if (listPacotes.size() == 0) {
            mensagem.setText("As informações dos pacotes estão indisponíveis no momento, por favor, tente mais tarde.");
        }

        return root;
    }

    //MONTA A LISTA COMS OS DADOS DOS PACOTES
    private void carregarPacotes(){

        Pacote pacote;

        carregarJsonPacotes();

        for(int i=0; i <= Pacotes.size() - 1; i++) {
            pacote = new Pacote(
                    Pacotes.get(i).getName(),
                    Pacotes.get(i).getCity(),
                    Pacotes.get(i).getState(),
                    Pacotes.get(i).getPrice(),
                    Pacotes.get(i).getImage(),
                    Pacotes.get(i).getAmenities());

            listPacotes.add(pacote);
        }

        recyclerViewPacoteAdapter.notifyDataSetChanged();
    }

    public void carregarJsonPacotes() {
        try {
            Pacotes = new PacoteAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}