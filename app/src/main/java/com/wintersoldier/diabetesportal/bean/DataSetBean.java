package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/2/15.
 */
public class DataSetBean implements DataSet {
    // instance variables
    private String name;
    private String ancestry;
    private List<DataSet> dataSetList;
    private List<Property> propertyList;
    private List<Phenotype> phenotypeList;
    private int sortOrder;
    private MetadataLeaf parent;

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
        return PortalConstants.TYPE_DATASET_KEY;
    }

    public String getId() {
        return (this.parent == null ? "" : this.parent.getId()) + this.getId();
    }

    public MetadataLeaf getParent() {
        return this.parent;
    }

    public List<DataSet> getChildren() {
        if (this.dataSetList == null) {
            this.dataSetList = new ArrayList<DataSet>();
        }

        return this.dataSetList;
    }

    @Override
    public List<DataSet> getRecursiveChildren() {
        // create a new list from the direct children
        List<DataSet> tempList = new ArrayList<DataSet>();
        tempList.addAll(this.getChildren());

        // add in the children's children
        for (DataSet dataSet : this.dataSetList) {
            tempList.addAll(dataSet.getRecursiveChildren());
        }

        return tempList;
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
