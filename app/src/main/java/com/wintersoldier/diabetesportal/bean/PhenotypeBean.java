package com.wintersoldier.diabetesportal.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/3/15.
 */
public class PhenotypeBean implements Phenotype {
    // instance variables
    private String name;
    private int sortOrder;
    private String group;
    private List<Property> propertyList;

    public void setName(String name) {
        this.name = name;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override

    public String getName() {
        return this.getName();
    }

    @Override
    public int getSortOrder() {
        return this.sortOrder;
    }

    @Override
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public List<Property> getProperties() {
        if (this.propertyList == null) {
            this.propertyList = new ArrayList<Property>();
        }

        return this.propertyList;
    }
}