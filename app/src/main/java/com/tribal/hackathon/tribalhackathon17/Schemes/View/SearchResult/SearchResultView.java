package com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;

/**
 * Created by Abhi on 11-Dec-17.
 */

public interface SearchResultView {
    void showProgressBar(boolean b);

    void showMessage(String success);

    void displayResult(SearchResult body);
}
