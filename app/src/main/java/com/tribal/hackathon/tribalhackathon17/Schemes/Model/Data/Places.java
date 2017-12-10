package com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data;

public class Places {
    private String id, name, type, upper_node_id;

    public Places(String id, String name, String type, String upper_node_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.upper_node_id = upper_node_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    public String getUpper_node_id() {
        return upper_node_id;
    }
}