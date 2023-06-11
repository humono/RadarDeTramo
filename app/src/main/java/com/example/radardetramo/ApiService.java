package com.example.radardetramo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/resources/lists/insert")
    Call<Void> insertPasoVehiculo(@Body PasoVehiculo pv);
}
