package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.bean.visitor.DataSetVisitor;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/9/15.
 */
public class MetaDataRootBean implements MetaDataRoot {
    // instance variables
    private List<Experiment> experimentList;
    private List<Property> propertyList;

    @Override
    public List<Experiment> getExperiments() {
        if (this.experimentList == null) {
            this.experimentList = new ArrayList<Experiment>();
        }

        return this.experimentList;
    }

    @Override
    public List<Property> getProperties() {
        if (this.propertyList == null) {
            this.propertyList = new ArrayList<Property>();
        }

        return this.propertyList;
    }

    @Override
    public DataSet getParent() {
        return null;
    }

    @Override
    public String getId() {
        return "metadata_root";
    }

    @Override
    public String getType() {
        return PortalConstants.TYPE_METADATA_ROOT_KEY;
    }

    @Override
    public void acceptVisitor(DataSetVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return this.getId();
    }

    /**
     * return a list of all the object's dataset children
     *
     * @return
     */
    public List<DataSet> getAllChildren() {
        // local variable
        List<DataSet> allChildrenList = new ArrayList<DataSet>();

        // add all children lists
        allChildrenList.addAll(this.getExperiments());
        allChildrenList.addAll(this.getProperties());

        // return the resulting list
        return allChildrenList;
    }
}
