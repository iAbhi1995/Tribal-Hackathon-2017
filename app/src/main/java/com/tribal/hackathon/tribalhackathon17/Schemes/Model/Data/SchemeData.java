package com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data;

public class SchemeData {

    private String id, name, description, used_amount, allocated_amount, dept_name;

    public SchemeData(String id, String name, String description, String used_amount, String allocated_amount, String dept_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.used_amount = used_amount;
        this.allocated_amount = allocated_amount;
        this.dept_name = dept_name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsed_amount() {
        return used_amount;
    }

    public String getAllocated_amount() {
        return allocated_amount;
    }

    public String getDept_name() {
        return dept_name;
    }
}