package com.italoghiele.projetointegrador.interceptor;

import com.italoghiele.projetointegrador.exception.RequestErrorException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Response response = chain.proceed(request);

        if (!response.isSuccessful()) {
            String message = "Error on request";

            if (response.body() != null) {
                message = response.body().string();
            }

            throw new RequestErrorException(message);
        }

        return response;
    }
}
