package com.italoghiele.projetointegrador.service.api;

import com.italoghiele.projetointegrador.dto.response.ViacepAddressResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiViacepService {

    @GET("{code}/json/")
    Call<ViacepAddressResponse> getAddressByCEP(@Path(value = "code") String code);
}
