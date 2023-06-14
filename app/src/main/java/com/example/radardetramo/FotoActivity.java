package com.example.radardetramo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FotoActivity extends AppCompatActivity {

    Tramo t;
    final String URLSTRING = "http://localhost:8080/RadarDeTramo/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        t = (Tramo) getIntent().getSerializableExtra("tramo");

        ImageView imageCoche = (ImageView) findViewById(R.id.imageCoche);

        Random rdm = new Random();
        int numero = rdm.nextInt(5 - 1 + 1) + 1;
        if (numero == 1) {
            imageCoche.setImageResource(R.drawable.kkj3253);
        }
        if (numero == 2) {
            imageCoche.setImageResource(R.drawable.ksf8016);
        }
        if (numero == 3) {
            imageCoche.setImageResource(R.drawable.kzs8806);
        }
        if (numero == 4) {
            imageCoche.setImageResource(R.drawable.lvx5609);
        }
        if (numero == 5) {
            imageCoche.setImageResource(R.drawable.zgz2008);
        }
    }

    public void aSeleccionDeTramo(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void enviarPasoDeVehiculo(View v) {
        PasoVehiculo pv = new PasoVehiculo();

        Log.i("Tramo", this.t.toString());

        ImageView imageCoche = (ImageView) findViewById(R.id.imageCoche);
        Bitmap bitmap = ((BitmapDrawable) imageCoche.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();

        pv.setId(1);
        pv.setFecha_hora(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        pv.setPunto_kilometrico(this.t.getPunto_kilometrico());
        pv.setFoto(imageInByte);
        pv.setId_tramo(this.t.getId());
        pv.setVelocidad(0);
        pv.setMatricula_vehiculo("");

        enviarDatos(pv);

        Intent i = new Intent(this, FotoActivity.class);
        i.putExtra("tramo", this.t);
        startActivity(i);
    }


    public void enviarDatos(PasoVehiculo pv) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLSTRING)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Void> call = apiService.insertar(pv);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Envío correcto", response.message());
                Log.i("Envío de infractor", "Fecha/hora: " + pv.getFecha_hora() +
                        " || Id tramo " + pv.getId_tramo() + " || pk: " + pv.getPunto_kilometrico());
                Log.i("Imagen del infractor", Arrays.toString(pv.getFoto()));
                if (response.isSuccessful()) {
                    //COrrecto
                } else {
                    Log.i("Error en en envío", response.message());
                    Log.i("Error en envío de infractor", "Fecha/hora: " + pv.getFecha_hora() +
                            " || Id tramo " + pv.getId_tramo() + " || pk: " + pv.getPunto_kilometrico());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Error", "Error en la comunicación con el servidor");
            }
        });
    }
}