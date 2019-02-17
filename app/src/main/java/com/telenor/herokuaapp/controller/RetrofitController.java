package com.telenor.herokuaapp.controller;

import com.telenor.herokuaapp.network.NetworkApi;
import com.telenor.herokuaapp.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by nofal bin idrees on 2/17/2019.
 */
public class RetrofitController {
    private static Retrofit retrofit;

    public static NetworkApi getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(80, TimeUnit.SECONDS).connectTimeout(80, TimeUnit.SECONDS)
                    .addNetworkInterceptor(interceptor).addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit.create(NetworkApi.class);

    }
}
