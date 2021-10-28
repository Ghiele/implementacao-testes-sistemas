package com.italoghiele.projetointegrador.service.api;

import com.italoghiele.projetointegrador.interceptor.ErrorInterceptor;
import com.italoghiele.projetointegrador.service.api.factory.NullOnEmptyConverterFactory;
import lombok.Setter;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class ApiClientViacepConfig {

    @Value("${api-viacep.url}")
    @Setter
    private String apiUrl;

    @Autowired
    @Setter
    private ErrorInterceptor errorInterceptor;


    @Bean("configure-retrofit-viacep")
    public Retrofit configure() {

        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(errorInterceptor)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(defaultHttpClient)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Bean("api-viacep-service")
    @DependsOn
    public ApiViacepService buildViacepApiClient(
            @Qualifier("configure-retrofit-viacep") Retrofit retrofit) {
        return retrofit.create(ApiViacepService.class);
    }
}
