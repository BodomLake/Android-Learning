package com.bodomlake.jetpack.paging.request;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;

    // 指定host
    private final String HOST_ADDRESS = "http://localhost:8080";
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HOST_ADDRESS)
                .client(getClient())
                .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public MyApi getAPI() {
        // 根据接口文件的Annotation，构建一系列用于请求的API
        return retrofit.create(MyApi.class);
    }
}
