package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/2/15.
 */
public class SampleGroupBean implements SampleGroup {
    // instance variables
    private String name;
    private String ancestry;
    private List<SampleGroup> sampleGroupList;
    private List<Property> propertyList;
    private List<Phenotype> phenotypeList;
    private int sortOrder;
    private DataSet parent;

    public void setName(String name) {
        this.name = name;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getType() {
        return PortalConstants.TYPE_SAMPLE_GROUP_KEY;
    }

    public String getId() {
        return (this.parent == null ? "" : this.parent.getId()) + this.getId();
    }

    public DataSet getParent() {
        return this.parent;
    }

    public List<SampleGroup> getChildren() {
        if (this.sampleGroupList == null) {
            this.sampleGroupList = new ArrayList<SampleGroup>();
        }

        return this.sampleGroupList;
    }

    public void setParent(DataSet parent) {
        this.parent = parent;
    }

    @Override
    public List<SampleGroup> getRecursiveChildren() {
        // create a new list from the direct children
        List<SampleGroup> tempList = new ArrayList<SampleGroup>();
        tempList.addAll(this.getChildren());

        // add in the children's children
        for (SampleGroup sampleGroup : this.sampleGroupList) {
            tempList.addAll(sampleGroup.getRecursiveChildren());
        }

        return tempList;
    }

    @Override
    public List<SampleGroup> getRecursiveParents() {
        return null;
    }

    @Override
    public List<SampleGroup> getRecursiveChildrenForPhenotype(Phenotype phenotype) {
        return null;
    }

    @Override
    public List<SampleGroup> getRecursiveParentsForPhenotype(Phenotype phenotype) {
        return null;
    }

    @Override
    public List<Phenotype> getPhenotypes() {
        if (this.phenotypeList == null) {
            this.phenotypeList = new ArrayList<Phenotype>();
        }

        return this.phenotypeList;
    }

    @Override
    public List<Property> getProperties() {
        if (this.propertyList == null) {
            this.propertyList = new ArrayList<Property>();
        }

        return this.propertyList;
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
