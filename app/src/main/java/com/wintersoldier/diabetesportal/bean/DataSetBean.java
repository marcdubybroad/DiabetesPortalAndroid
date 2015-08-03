package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 8/2/15.
 */
public class DataSetBean implements DataSet {
    // instance variables
    private String name;
    private String ancestry;

    public void setName(String name) {
        this.name = name;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    private int sortOrder;

    @Override
    public List<DataSet> getRecursiveChildren() {
        return null;
    }

    @Override
    public List<DataSet> getRecursiveParents() {
        return null;
    }

    @Override
    public List<DataSet> getRecursiveChildrenForPhenotype(Phenotype phenotype) {
        return null;
    }

    @Override
    public List<DataSet> getRecursiveParentsForPhenotype(Phenotype phenotype) {
        return null;
    }

    @Override
    public List<Phenotype> getPhenotypes() {
        return null;
    }

    @Override
    public List<Property> getProperties() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAncestry() {
        return this.ancestry;
    }

    @Override
    public int getSortOrder() {
        return this.sortOrder;
    }
}
