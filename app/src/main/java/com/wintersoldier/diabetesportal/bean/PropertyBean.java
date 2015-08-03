package com.wintersoldier.diabetesportal.bean;

/**
 * Created by mduby on 8/3/15.
 */
public class PropertyBean implements Property {
    private String name;
    private String description;
    private String type;
    private int sortOrder;
    private boolean searchable;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @Override

    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public boolean isSearchable() {
        return this.searchable;
    }

    @Override
    public int getSortOrder() {
        return this.sortOrder;
    }
}
