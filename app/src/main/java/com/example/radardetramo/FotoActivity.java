package com.example.radardetramo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class FotoActivity extends AppCompatActivity {

    Tramo t;
    final String URLSTRING = "http://192.168.1.53:8080/RadarDeTramo/";

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
        Drawable drawable = imageCoche.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream); // Se podría cambiar a PNG
        byte[] imageBytes = outputStream.toByteArray();

        pv.setId(1);
        pv.setFecha_hora(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        pv.setPunto_kilometrico(this.t.getPunto_kilometrico());
        pv.setFoto(imageBytes);
        pv.setId_tramo(this.t.getId());
        pv.setVelocidad(0);
        pv.setMatricula_vehiculo("");

        Log.i("Paso de vehículo", pv.toString());
        enviarDatos(pv);

        Intent i = new Intent(this, FotoActivity.class);
        i.putExtra("tramo", this.t);
        startActivity(i);
    }


    public void enviarDatos(PasoVehiculo pv) {
        Gson gson = new Gson();
        String json = gson.toJson(pv);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.53:8080/RadarDeTramo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> call = apiService.insertar(pv);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("Inserción correcta", pv.toString());
                } else {
                    Log.e("Error en la solicitud", "Ha habido algún error en la solicitud");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error desconocido", "Ha habido algún error");
            }
        });
    }
}