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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Random;

public class FotoActivity extends AppCompatActivity {

    Tramo t;

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
        pv.setFechaHora(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        ImageView imageCoche = (ImageView) findViewById(R.id.imageCoche);
        Bitmap bitmap = ((BitmapDrawable) imageCoche.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();

        pv.setImagen(imageInByte);
        pv.setCarretera(this.t.getCarretera());
        pv.setPuntoKilometrico(this.t.getPuntoKilometrico());


        Log.i("Envío de infractor", "Fecha/hora: " + pv.getFechaHora() +
                " || Carretera, pk: " + pv.getCarretera() + ", " + pv.getPuntoKilometrico());
        Log.i("Imagen del infractor", Arrays.toString(pv.getImagen()));

        Intent i = new Intent(this, FotoActivity.class);
        i.putExtra("tramo", this.t);
        startActivity(i);
    }
}