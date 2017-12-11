package com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data;

import java.util.List;

public class Departments {
    private boolean success;
    private List<Department> departments;

    public Departments(List<Department> departments, boolean success) {
        this.success = success;
        this.departments = departments;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Department> getDepartmentList() {
        return departments;
    }

    public static class Department {
        private String id, name;

        public Department(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}