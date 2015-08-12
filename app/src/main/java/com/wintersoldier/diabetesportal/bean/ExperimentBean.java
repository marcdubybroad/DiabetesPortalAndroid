package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.bean.visitor.DataSetVisitor;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/1/15.
 */
public class ExperimentBean implements Experiment {
    private String name;
    private String technology;
    private String version;
    private List<SampleGroup> sampleGroupList;
    private DataSet parent;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return PortalConstants.TYPE_EXPERIMENT_KEY;
    }

    public String getId() {
        return this.getName();
    }

    public DataSet getParent() {
        return null;
    }

    public List<SampleGroup> getSampleGroups() {
        if (this.sampleGroupList == null) {
            this.sampleGroupList = new ArrayList<SampleGroup>();
        }

        return sampleGroupList;
    }

    public void setDataSets(List<SampleGroup> sampleGroupList) {
        this.sampleGroupList = sampleGroupList;
    }

    public void setParent(DataSet dataSet) {
        this.parent = parent;
    }

    /**
     * implement the visitor pattern
     *
     * @param visitor
     */
    public void acceptVisitor(DataSetVisitor visitor) {
        visitor.visit(this);
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
        allChildrenList.addAll(this.getSampleGroups());

        // return the resulting list
        return allChildrenList;
    }
}
