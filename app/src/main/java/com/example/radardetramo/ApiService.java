package com.example.radardetramo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("insertar")
    Call<Void> insertar(@Body PasoVehiculo pv);
}
