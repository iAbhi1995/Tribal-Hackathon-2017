package com.tribal.hackathon.tribalhackathon17.Schemes.Presenter;

import android.content.Context;
import android.util.Log;

import com.tribal.hackathon.tribalhackathon17.Helper.DataBaseHandler;
import com.tribal.hackathon.tribalhackathon17.R;
import com.tribal.hackathon.tribalhackathon17.Schemes.DepartmentsCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Departments;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.SchemeProvider;
import com.tribal.hackathon.tribalhackathon17.Schemes.PlacesCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SearchCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SchemeView;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SearchResult.SearchResultView;


public class PresenterImpl implements SchemePresenter {
    private SchemeProvider provider;
    private SchemeView view;
    private SearchResultView view1;

    private DataBaseHandler db;
    private Context context;

    public PresenterImpl(SchemeProvider provider, SchemeView view, Context context) {
        this.provider = provider;
        this.view = view;
        this.context = context;
        db = new DataBaseHandler(context);
    }

    public PresenterImpl(SchemeProvider provider, SearchResultView view, Context context) {
        this.provider = provider;
        this.view1 = view;
        this.context = context;
        db = new DataBaseHandler(context);
    }

    @Override
    public void getScheme() {
        Log.d("abhi", "in presenter");

        view.showProgressBar(true);
        provider.getScheme(new SchemeCallback() {
            @Override
            public void onSuccess(Schemes body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    db.addSchemes(body.getSchemeDatas());
                } else {
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));
                }

            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
            }
        });
        provider.getPlaces(new PlacesCallback() {
            @Override
            public void onSuccess(Places body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    db.addPlaces(body.getPlaces());
                    view.setView();
                } else {
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));
                }

            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
            }
        });
        provider.getDepartments(new DepartmentsCallback() {
            @Override
            public void onSuccess(Departments body) {
                view.showProgressBar(false);
                Log.d("abhi", body.isSuccess() + "");
                if (body.isSuccess()) {
                    db.addDepartments(body.getDepartmentList());
                    Log.d("abhi", "Department " + body.getDepartmentList().size());
                    view.setView();
                } else {
                    Log.d("abhi", "Department000 ");
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));
                }

            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                Log.d("abhi", "Department11 ");
            }
        });
        db.addInitialEntries(db.getSchemes(), db.getPlaces());
    }

    @Override
    public void searchByPlace(String id) {
        view1.showProgressBar(true);
        provider.searchbyplace(id, new SearchCallback() {
            @Override
            public void onSuccess(SearchResult body) {
                if (body.isSuccess()) {

                    view1.showMessage("Success");
                    view1.displayResult(body);
                } else {
                    view1.showMessage(context.getResources().getString(R.string.Connection_Error));
                }
            }

            @Override
            public void onFailure() {
                view1.showMessage("Error");
            }
        });
    }
}
