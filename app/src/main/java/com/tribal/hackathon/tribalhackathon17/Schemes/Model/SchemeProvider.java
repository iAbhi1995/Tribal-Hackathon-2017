package com.tribal.hackathon.tribalhackathon17.Schemes.Model;


import com.tribal.hackathon.tribalhackathon17.Schemes.DepartmentsCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.PlacesCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SchemeCallback;
import com.tribal.hackathon.tribalhackathon17.Schemes.SearchCallback;

public interface SchemeProvider {

    void getScheme(SchemeCallback callback);

    void getPlaces(PlacesCallback callback);

    void getDepartments(DepartmentsCallback callback);

    void searchbyplace(String id, SearchCallback callback);

}
