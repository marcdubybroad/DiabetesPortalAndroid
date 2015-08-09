package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 8/9/15.
 */
public interface MetaDataRoot extends DataSet {

    public List<Experiment> getExperiments();

    public List<Property> getProperties();
}
