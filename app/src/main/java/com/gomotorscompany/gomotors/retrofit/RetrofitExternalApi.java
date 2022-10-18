package com.gomotorscompany.gomotors.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitExternalApi {

    private static Retrofit retrofit;
    private static final String URL_MAP_API2 = RetrofitEndPointsV2.URL_MAP_API;
    private static OkHttpClient okHttpClient;

    public static Retrofit getApiMap2() {
        if (retrofit == null) {
            okHttpClient = new OkHttpClient.Builder()//.sslSocketFactory()
                    .readTimeout(45, TimeUnit.SECONDS)
                    .connectTimeout(45, TimeUnit.SECONDS)
                    .build();
            //SSLContext sslContext=Sslut;

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_MAP_API2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
