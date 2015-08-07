package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 7/28/15.
 */
public interface Phenotype extends DataSet {

    public String getName();

    public int getSortOrder();

    public String getGroup();

    public List<Property> getProperties();

}
