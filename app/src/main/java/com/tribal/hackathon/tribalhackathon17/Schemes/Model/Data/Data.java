package com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data;

import java.util.List;

public class Data {
    private List<Places> places;
    private String message;
    private boolean success;
    private List<Department> departments;
    private List<SchemeData> schemes;

    public Data(String message, boolean success, List<Department> departments, List<Places> places, List<SchemeData> schemes) {
        this.message = message;
        this.success = success;
        this.departments = departments;
        this.places = places;
        this.schemes = schemes;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public List<SchemeData> getSchemes() {
        return schemes;
    }
}