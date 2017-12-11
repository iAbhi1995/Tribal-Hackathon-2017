package com.tribal.hackathon.tribalhackathon17.Schemes.Model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tribal.hackathon.tribalhackathon17.Helper.Urls;
import com.tribal.hackathon.tribalhackathon17.Schemes.Api.SchemeApi;
import com.tribal.hackathon.tribalhackathon17.Schemes.DepartmentsCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Departments;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;
import com.tribal.hackathon.tribalhackathon17.Schemes.PlacesCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SearchCallback;

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
        Call<Schemes> call = schemeApi.getSchemes();
        call.enqueue(new Callback<Schemes>() {
            @Override
            public void onResponse(Call<Schemes> call, Response<Schemes> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Schemes> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getPlaces(final PlacesCallback callback) {
        schemeApi = retrofit.create(SchemeApi.class);
        Call<Places> call = schemeApi.getPlaces();
        call.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getDepartments(final DepartmentsCallback callback) {
        schemeApi = retrofit.create(SchemeApi.class);
        Call<Departments> call = schemeApi.getDepartments();
        call.enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(Call<Departments> call, Response<Departments> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Departments> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void searchbyplace(String id, final SearchCallback callback) {
        schemeApi = retrofit.create(SchemeApi.class);
        Call<SearchResult> call = schemeApi.getSearchByPlace(Urls.Base_Url + "/api/place-search/" + id);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }


}
