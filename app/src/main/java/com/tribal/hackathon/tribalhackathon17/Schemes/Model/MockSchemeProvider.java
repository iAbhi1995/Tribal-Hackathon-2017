package com.tribal.hackathon.tribalhackathon17.Schemes.Model;


import android.os.Handler;

import com.tribal.hackathon.tribalhackathon17.Schemes.DepartmentsCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Departments;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;
import com.tribal.hackathon.tribalhackathon17.Schemes.PlacesCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SearchCallback;

import java.util.ArrayList;
import java.util.List;


public class MockSchemeProvider implements SchemeProvider {
    private Schemes schemes;
    private Places placesData;
    private Departments departmentData;
    private SearchResult searchData;

    @Override
    public void getScheme(final SchemeCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getSchemeData());
            }
        }, 500);
    }

    @Override
    public void getPlaces(final PlacesCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getPlacesData());
            }
        }, 500);
    }

    @Override
    public void getDepartments(final DepartmentsCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getDepartmentData());
            }
        }, 500);
    }

    @Override
    public void searchbyplace(String id, final SearchCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getSearchData());
            }
        }, 500);
    }

    public Schemes getSchemeData() {
        List<Schemes.SchemeData> schemeDatas = new ArrayList<>();
        schemeDatas.add(new Schemes.SchemeData("1", "Education yojna", "", "", "", "education"));
        schemeDatas.add(new Schemes.SchemeData("2", "Education yojna2", "", "", "", "education"));
        schemeDatas.add(new Schemes.SchemeData("3", "Education yojna3", "", "", "", "education"));
        schemeDatas.add(new Schemes.SchemeData("4", "Education yojna4", "", "", "", "education"));
        schemeDatas.add(new Schemes.SchemeData("5", "Education yojna5", "", "", "", "education"));
        schemeDatas.add(new Schemes.SchemeData("6", "Education yojna6", "", "", "", "education"));
        schemes = new Schemes(true, schemeDatas);
        return schemes;
    }

    public Places getPlacesData() {
        List<Places.Place> places = new ArrayList<>();
        places.add(new Places.Place("1", "bhatapara", "districs", "3"));
        places.add(new Places.Place("2", "bhatapara", "districs", "0"));
        places.add(new Places.Place("3", "bijupur", "districs", "0"));
        places.add(new Places.Place("4", "raigarh", "districs", "2"));
        places.add(new Places.Place("5", "rajnandgaon", "districs", "2"));
        places.add(new Places.Place("6", "bhatapara", "districs", "3"));
        placesData = new Places(true, places);
        return placesData;
    }

    public Departments getDepartmentData() {
        List<Departments.Department> departments = new ArrayList<>();
        departments.add(new Departments.Department("1", "water"));
        departments.add(new Departments.Department("2", "fire"));
        departments.add(new Departments.Department("3", "air"));
        departmentData = new Departments(departments, true);
        return departmentData;
    }

    public SearchResult getSearchData() {
        List<SearchResult.allocation> allocations = new ArrayList<>();
        List<SearchResult.sub_level> sub_levels = new ArrayList<>();
        allocations.add(new SearchResult.allocation("1", "Road bnao yojna", 23232, 2345));
        allocations.add(new SearchResult.allocation("2", "Lag jao bnao yojna", 23232, 2345));
        allocations.add(new SearchResult.allocation("3", "Nayi Scheme", 23232, 2345));

        sub_levels.add(new SearchResult.sub_level("2", "G E Road", ""));
        searchData = new SearchResult(true, allocations, sub_levels, 69696, 7035);
        return searchData;
    }
}
