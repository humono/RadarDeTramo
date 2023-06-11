package com.example.radardetramo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class FotoActivity extends AppCompatActivity {

    Tramo t;
    final String URLSTRING = "http://localhost:8080/RadarDeTramoServidor/resources/listados/insert/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        t = (Tramo) getIntent().getSerializableExtra("tramo");

        ImageView imageCoche = (ImageView) findViewById(R.id.imageCoche);

        Random rdm = new Random();
        int numero = rdm.nextInt(4 - 1 + 1) + 1;
        if (numero == 1) {
            imageCoche.setImageResource(R.drawable.jpf1712);
        }
        if (numero == 2) {
            imageCoche.setImageResource(R.drawable.ktv4867);
        }
        if (numero == 3) {
            imageCoche.setImageResource(R.drawable.lhs7465);
        }
        if (numero == 4) {
            imageCoche.setImageResource(R.drawable.ltv6631);
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

        pv.setFecha_hora(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        pv.setPunto_kilometrico(this.t.getPunto_kilometrico());
        pv.setFoto(imageInByte);
        pv.setId_tramo(this.t.getId());

        enviarDatos2(pv);

        Log.i("Envío de infractor", "Fecha/hora: " + pv.getFecha_hora() +
                " || Id tramo " + pv.getId_tramo() + " || pk: " + pv.getPunto_kilometrico());
        Log.i("Imagen del infractor", Arrays.toString(pv.getFoto()));

        Intent i = new Intent(this, FotoActivity.class);
        i.putExtra("tramo", this.t);
        startActivity(i);
    }

    public void enviarDatos(PasoVehiculo pv) {
        try {
            URL url = new URL(URLSTRING);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/JSON");
            Uri.Builder params = new Uri.Builder()
                    .appendQueryParameter("fecha_hora", "" + pv.getFecha_hora())
                    .appendQueryParameter("punto_kilometrico", "" + pv.getPunto_kilometrico())
                    .appendQueryParameter("id_tramo", "" + pv.getId_tramo())
                    .appendQueryParameter("foto", pv.getStringOfBytes());

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(http.getOutputStream()));
            bw.write(params.build().getEncodedQuery());

            bw.flush();
            bw.close();
            http.connect();

            Log.i("http", http.getURL().toString());

        } catch (MalformedURLException mue) {
            Log.i("Malformed URL exception", mue.getMessage());
        } catch (IOException ioe) {
            Log.i("IO exception", ioe.getMessage());
        }
    }

    public void enviarDatos2(PasoVehiculo pv) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLSTRING)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<RespuestaServidor> call = apiService.enviarPasoVehiculo(pv);

        call.enqueue(new Callback<RespuestaServidor>() {
            @Override
            public void onResponse(Call<RespuestaServidor> call, Response<RespuestaServidor> response) {
                RespuestaServidor respuesta = response.body();
                if (response.isSuccessful()) {
                    Log.i("Respuesta del servidor: " + respuesta.getCodigo(), respuesta.getMensaje());
                } else {
                    Log.i("Respuesta del servidor: " + respuesta.getCodigo(), respuesta.getMensaje());
                }
            }

            @Override
            public void onFailure(Call<RespuestaServidor> call, Throwable t) {
                Log.i("Error en la solicitud", "");
            }
        });
    }
}