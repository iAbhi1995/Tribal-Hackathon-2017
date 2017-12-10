package com.tribal.hackathon.tribalhackathon17.Schemes.Model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tribal.hackathon.tribalhackathon17.Helper.Urls;
import com.tribal.hackathon.tribalhackathon17.Schemes.Api.SchemeApi;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Data;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSchemeProvider implements SchemeProvider {
    private final Retrofit retrofit;
    private SchemeApi schemeApi;

    public RetrofitSchemeProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(Urls.Base_Url).
                addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();

    }

    @Override
    public void getScheme(final SchemeCallback callback) {
        schemeApi = retrofit.create(SchemeApi.class);
        Call<Data> call = schemeApi.getSchemes();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
