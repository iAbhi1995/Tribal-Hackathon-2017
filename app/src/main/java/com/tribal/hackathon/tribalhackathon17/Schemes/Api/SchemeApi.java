package com.tribal.hackathon.tribalhackathon17.Schemes.Api;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Data;
import com.tribal.hackathon.tribalhackathon17.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchemeApi {

    @GET(Urls.SCHEME)
    Call<Data> getSchemes();

}
