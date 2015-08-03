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
    private List<Property> propertyList;

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public int getSortOrder() {
        return this.sortOrder;
    }

    @Override
    public List<Property> getProperties() {
        if (this.propertyList == null) {
            this.propertyList = new ArrayList<Property>();
        }

        return this.propertyList;
    }
}
