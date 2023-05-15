package com.example.radardetramo;

import java.io.Serializable;
import java.util.Arrays;

public class PasoVehiculo implements Serializable {
    private String carretera;
    private String puntoKilometrico;
    private long fechaHora;
    private byte[] imagen;

    public PasoVehiculo() {}

    public PasoVehiculo(String carretera, String puntoKilometrico, long fechaHora, byte[] imagen) {
        this.carretera = carretera;
        this.puntoKilometrico = puntoKilometrico;
        this.fechaHora = fechaHora;
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "PasoVehiculo{" +
                "carretera='" + carretera + '\'' +
                ", puntoKilometrico='" + puntoKilometrico + '\'' +
                ", fechaHora='" + fechaHora + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                '}';
    }

    public String getCarretera() {
        return carretera;
    }

    public void setCarretera(String carretera) {
        this.carretera = carretera;
    }

    public String getPuntoKilometrico() {
        return puntoKilometrico;
    }

    public void setPuntoKilometrico(String puntoKilometrico) {
        this.puntoKilometrico = puntoKilometrico;
    }

    public long getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(long fechaHora) {
        this.fechaHora = fechaHora;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
