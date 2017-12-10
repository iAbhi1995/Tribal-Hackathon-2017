package com.tribal.hackathon.tribalhackathon17.Schemes.Presenter;

import android.content.Context;
import android.util.Log;

import com.tribal.hackathon.tribalhackathon17.Helper.DataBaseHandler;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Data;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.SchemeProvider;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.View.SchemeView;


public class PresenterImpl implements SchemePresenter {
    private SchemeProvider provider;
    private SchemeView view;
    private DataBaseHandler db;

    public PresenterImpl(SchemeProvider provider, SchemeView view, Context context) {
        this.provider = provider;
        this.view = view;
        db = new DataBaseHandler(context);
    }

    @Override
    public void getScheme() {
        view.showProgressBar(true);
        provider.getScheme(new SchemeCallback() {
            @Override
            public void onSuccess(Data body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    db.addSchemes(body.getSchemes());
                    db.addDepartments(body.getDepartments());
                    db.addPlaces(body.getPlaces());
                    view.setView();
                    db.addInitialEntries(body.getSchemes(), body.getPlaces());
                    Log.d("abhi", body.getSchemes().size() + " " + body.getDepartments().size() + " " + body.getPlaces().size());
                } else {
                    view.showMessage(body.getMessage());
                }

            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
            }
        });
    }
}
