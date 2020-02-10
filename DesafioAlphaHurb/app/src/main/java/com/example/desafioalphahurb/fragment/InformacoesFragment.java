package com.example.desafioalphahurb.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.desafioalphahurb.R;

public class InformacoesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_info, container, false);


        TextView txt_versao = (TextView) rootView.findViewById(R.id.nm_versao);
        txt_versao.setText("Versão " + getString(R.string.versao));

        TextView txt_desenvolvido = (TextView) rootView.findViewById(R.id.nm_desenvolvido);
        txt_desenvolvido.setText(getString(R.string.desenvolvido));

        TextView txt_desenvolvidor = (TextView) rootView.findViewById(R.id.nm_desenvolvedor);
        txt_desenvolvidor.setText(getString(R.string.desenvolvidor));

        return rootView;
    }
}