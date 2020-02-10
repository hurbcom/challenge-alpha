package com.example.desafioalphahurb.activitites;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.connection.Conexao;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(Conexao.IsConnected(this)) {
            new Thread(new Runnable() {
                public void run() {
                    SystemClock.sleep(2000);
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).start();
        }else{
            Intent intent = new Intent(getBaseContext(), SemConexaoActivity.class);
            startActivity(intent);
            finish();
        }
    }
}