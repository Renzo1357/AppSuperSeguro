package com.example.appsuperseguro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Metodo para la carga del Splash de Bienvenida
        TimerTask load = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, OnboardingActivity.class);
                startActivity(i);
                finish();
            }
        };

        // Invacion al Splash de Bienvenida
        Timer time = new Timer();
        time.schedule(load, 5000);

    }

}