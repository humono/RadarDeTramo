package com.example.radardetramo;

import java.io.Serializable;


public class Tramo implements Serializable {
    private int id;
    private float punto_kilometrico;
    private int id_carretera;

    public Tramo() {}

    public Tramo(int id, float punto_kilometrico, int id_carretera) {
        this.id = id;
        this.punto_kilometrico = punto_kilometrico;
        this.id_carretera = id_carretera;
    }

    @Override
    public String toString() {
        return "Tramo{" +
                "id=" + id +
                ", punto_kilometrico=" + punto_kilometrico +
                ", id_carretera=" + id_carretera +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId_carretera() {
        return id_carretera;
    }

    public void setId_carretera(int id_carretera) {
        this.id_carretera = id_carretera;
    }

    public float getPunto_kilometrico(){
        return punto_kilometrico;
    }

    public void setPunto_kilometrico(float puntoKilometrico) {
        this.punto_kilometrico = puntoKilometrico;
    }
}
