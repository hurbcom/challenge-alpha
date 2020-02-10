package com.example.desafioalphahurb.activitites;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.desafioalphahurb.R;

//CALSSE QUE APRESENTA A TELA CASO O DISPOSITIVO SEM CONEXÃO COM A INTERNET
public class SemConexaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_conexao);

        //BOTÃO PARA REINICIAR O APLICATIVO
        Button button = (Button) findViewById(R.id.btn_tentar_novamente);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(getBaseContext(), SplashScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
