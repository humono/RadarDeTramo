package com.example.radardetramo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PasoVehiculo implements Serializable{
    @SerializedName("id")
    private int id;
    @SerializedName("fecha_hora")
    private long fecha_hora;
    @SerializedName("punto_kilometrico")
    private float punto_kilometrico;
    @SerializedName("velocidad")
    private float velocidad;
    @SerializedName("foto")
    private byte[] foto;
    @SerializedName("matricula_vehiculo")
    private String matricula_vehiculo;
    @SerializedName("id_tramo")
    private int id_tramo;

    public PasoVehiculo() {
    }

    public PasoVehiculo(int id, long fecha_hora, float punto_kilometrico, float velocidad, byte[] foto, String matricula_vehiculo, int id_tramo) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.punto_kilometrico = punto_kilometrico;
        this.velocidad = velocidad;
        this.foto = foto;
        this.matricula_vehiculo = matricula_vehiculo;
        this.id_tramo = id_tramo;
    }

    public PasoVehiculo(int id, long fecha_hora, float punto_kilometrico, String matricula_vehiculo, int id_tramo) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.punto_kilometrico = punto_kilometrico;
        this.matricula_vehiculo = matricula_vehiculo;
        this.id_tramo = id_tramo;
    }

    @Override
    public String toString() {
        return "PasoVehiculo{" + "id=" + id + ", fecha_hora=" + fecha_hora + ", punto_kilometrico=" + punto_kilometrico + ", velocidad=" + velocidad + ", foto=" + foto + ", matricula_vehiculo=" + matricula_vehiculo + ", id_tramo=" + id_tramo + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(long fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public float getPunto_kilometrico() {
        return punto_kilometrico;
    }

    public void setPunto_kilometrico(float punto_kilometrico) {
        this.punto_kilometrico = punto_kilometrico;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getMatricula_vehiculo() {
        return matricula_vehiculo;
    }

    public void setMatricula_vehiculo(String matricula_vehiculo) {
        this.matricula_vehiculo = matricula_vehiculo;
    }

    public int getId_tramo() {
        return id_tramo;
    }

    public void setId_tramo(int id_tramo) {
        this.id_tramo = id_tramo;
    }
}
