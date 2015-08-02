package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 8/1/15.
 */
public class ExperimentBean implements Experiment {
    private String name;
    private String technology;
    private String version;
    private List<DataSet> dataSetList;

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

    public List<DataSet> getDataSets() {
        return dataSetList;
    }

    public void setDataSets(List<DataSet> dataSetList) {
        this.dataSetList = dataSetList;
    }
}
