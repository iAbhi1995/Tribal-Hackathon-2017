package com.tribal.hackathon.tribalhackathon17.Schemes;


import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;

public interface SearchCallback {
    void onSuccess(SearchResult body);

    void onFailure();
}
