package com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data;


import java.util.List;

public class SearchResult {
    private boolean success;
    private List<allocation> allocations;
    private List<sub_level> sub_levels;
    private long allocated_amount;
    private long used_amount;

    public SearchResult(boolean success, List<allocation> allocations, List<sub_level> sub_levels, long allocated_amount, long used_amount) {
        this.success = success;
        this.allocations = allocations;
        this.sub_levels = sub_levels;
        this.allocated_amount = allocated_amount;
        this.used_amount = used_amount;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<allocation> getAllocations() {
        return allocations;
    }

    public List<sub_level> getSub_levels() {
        return sub_levels;
    }

    public long getAllocated_amount() {
        return allocated_amount;
    }

    public long getUsed_amount() {
        return used_amount;
    }

    public static class allocation {
        private long used_amount, allocated_amount;
        private String scheme_name, id;

        public allocation(String id, String scheme_name, long allocated_amount, long used_amount) {
            this.id = id;
            this.used_amount = used_amount;
            this.allocated_amount = allocated_amount;
            this.scheme_name = scheme_name;
        }

        public String getId() {
            return id;
        }

        public long getUsed_amount() {
            return used_amount;
        }

        public long getAllocated_amount() {
            return allocated_amount;
        }

        public String getScheme_name() {
            return scheme_name;
        }
    }

    public static class sub_level {
        private String id, name, description;

        public sub_level(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
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
    }
}
