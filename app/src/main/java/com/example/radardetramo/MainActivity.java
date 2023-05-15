package com.example.radardetramo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pasarAFoto(View v) {
        EditText carretera = findViewById(R.id.editTextCarretera);
        EditText puntoKilometrico = findViewById(R.id.editTextKilometro);

        carretera.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
        puntoKilometrico.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));

        if (carretera.getText().toString().equals("")) {
            carretera.requestFocus();
            carretera.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            Toast.makeText(this, "Nombre de la carretera obligatorio", Toast.LENGTH_SHORT).show();
        } else if (puntoKilometrico.getText().toString().equals("")) {
            puntoKilometrico.requestFocus();
            puntoKilometrico.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            Toast.makeText(this, "Punto kilométrico obligatorio", Toast.LENGTH_SHORT).show();
        } else {
            Tramo t = new Tramo();
            t.setCarretera(carretera.getText().toString());
            t.setPuntoKilometrico(puntoKilometrico.getText().toString());
            Log.i("Información de carretera", t.getCarretera());
            Log.i("Información de kilómetro", t.getPuntoKilometrico());

            Intent i = new Intent(this, FotoActivity.class);
            i.putExtra("tramo", t);
            startActivity(i);
        }
    }
}