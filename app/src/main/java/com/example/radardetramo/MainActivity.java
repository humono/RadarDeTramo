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
        EditText tramo = findViewById(R.id.editTextIdTramo);
        EditText puntoKilometrico = findViewById(R.id.editTextKilometro);

        tramo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
        puntoKilometrico.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));

        if (tramo.getText().toString().equals("")) {
            tramo.requestFocus();
            tramo.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            Toast.makeText(this, "Nombre de la tramo obligatorio", Toast.LENGTH_SHORT).show();
        } else if (puntoKilometrico.getText().toString().equals("")) {
            puntoKilometrico.requestFocus();
            puntoKilometrico.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            Toast.makeText(this, "Punto kilométrico obligatorio", Toast.LENGTH_SHORT).show();
        } else {
            Tramo t = new Tramo();
            t.setId(Integer.parseInt(tramo.getText().toString()));
            t.setPunto_kilometrico(Float.parseFloat(puntoKilometrico.getText().toString()));
            Log.i("ID del tramo", "" + t.getId());
            Log.i("kilómetro", "" + t.getPunto_kilometrico());

            Intent i = new Intent(this, FotoActivity.class);
            i.putExtra("tramo", t);
            startActivity(i);
        }
    }
}