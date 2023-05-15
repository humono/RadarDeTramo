package com.example.radardetramo;

import java.io.Serializable;


public class Tramo implements Serializable {
    private String carretera;
    private String puntoKilometrico;

    public Tramo() {}

    public Tramo(String carretera, String puntoKilometrico) {
        this.carretera = carretera;
        this.puntoKilometrico = puntoKilometrico;
    }

    @Override
    public String toString() {
        return "Tramo{" +
                "carretera='" + carretera + '\'' +
                ", puntoKilometrico=" + puntoKilometrico +
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
}
