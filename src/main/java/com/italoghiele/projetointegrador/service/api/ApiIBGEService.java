package com.italoghiele.projetointegrador.service.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ApiIBGEService {

    @GET("estados/{code}/municipios")
    Call<List<Object>> getCitiesByState(@Path(value = "code") String code);
}
