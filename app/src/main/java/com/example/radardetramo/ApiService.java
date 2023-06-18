package com.example.radardetramo;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("insertar")
    Call<ResponseBody> insertar(@Body PasoVehiculo pv);
}
