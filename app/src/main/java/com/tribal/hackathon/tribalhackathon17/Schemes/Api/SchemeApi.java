package com.tribal.hackathon.tribalhackathon17.Schemes.Api;

import com.tribal.hackathon.tribalhackathon17.Helper.Urls;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Departments;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SchemeApi {

    @GET(Urls.SCHEME_ALL)
    Call<Schemes> getSchemes();

    @GET(Urls.PLACES_ALL)
    Call<Places> getPlaces();

    @GET(Urls.DEPARTMENTS_ALL)
    Call<Departments> getDepartments();

    @GET
    Call<SearchResult> getSearchByPlace(@Url String url);

}
