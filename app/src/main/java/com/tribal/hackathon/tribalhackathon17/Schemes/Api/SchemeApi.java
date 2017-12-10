package com.tribal.hackathon.tribalhackathon17.Schemes.Api;

import com.tribal.hackathon.tribalhackathon17.Helper.Urls;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchemeApi {

    @GET(Urls.SCHEME)
    Call<Data> getSchemes();

}
