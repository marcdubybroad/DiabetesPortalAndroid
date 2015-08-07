package com.wintersoldier.diabetesportal.bean;

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

    public List<SampleGroup> getDataSets() {
        if (this.sampleGroupList == null) {
            this.sampleGroupList = new ArrayList<SampleGroup>();
        }

        return sampleGroupList;
    }

    public void setDataSets(List<SampleGroup> sampleGroupList) {
        this.sampleGroupList = sampleGroupList;
    }
}
