package com.gomotorscompany.gomotors.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientV3 {
    private static Retrofit retrofit;
    private static final String BASE_URL2 = RetrofitEndPointsV2.URL_SERVERV2;
    //  private static final String URL_MAP_API2 = RetrofitEndPointsV2.URL_MAP_API;
    private static OkHttpClient okHttpClient;
    public static Retrofit getRetrofitInstancev3() {
        if (retrofit == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
