package com.tribal.hackathon.tribalhackathon17.Schemes.Model;


import android.os.Handler;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Data;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Department;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SchemeData;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;

import java.util.ArrayList;
import java.util.List;

public class MockSchemeProvider implements SchemeProvider {


    private Data mockData;

    @Override
    public void getScheme(final SchemeCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);
    }

    public Data getMockData() {

        List<Department> departments = new ArrayList<>();
        departments.add(new Department("1", "water"));
        departments.add(new Department("2", "fire"));
        departments.add(new Department("3", "air"));

        List<SchemeData> schemeDatas = new ArrayList<>();
        schemeDatas.add(new SchemeData("1", "Education yojna", "", "", "", "education"));
        schemeDatas.add(new SchemeData("2", "Education yojna2", "", "", "", "education"));
        schemeDatas.add(new SchemeData("3", "Education yojna3", "", "", "", "education"));
        schemeDatas.add(new SchemeData("4", "Education yojna4", "", "", "", "education"));
        schemeDatas.add(new SchemeData("5", "Education yojna5", "", "", "", "education"));
        schemeDatas.add(new SchemeData("6", "Education yojna6", "", "", "", "education"));

        List<Places> places = new ArrayList<>();
        places.add(new Places("1", "bhatapara", "districs", "3"));
        places.add(new Places("2", "bhatapara", "districs", "0"));
        places.add(new Places("3", "bijupur", "districs", "0"));
        places.add(new Places("4", "raigarh", "districs", "2"));
        places.add(new Places("5", "rajnandgaon", "districs", "2"));
        places.add(new Places("6", "bhatapara", "districs", "3"));

        mockData = new Data("success", true, departments, places, schemeDatas);
        return mockData;
    }
}