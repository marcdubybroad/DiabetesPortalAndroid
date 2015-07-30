package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 7/30/15.
 */
public interface Experiment {

    public String getName();

    public String getTechnology();

    public String getVersion();

    public List<DataSet> getDataSets();
}
