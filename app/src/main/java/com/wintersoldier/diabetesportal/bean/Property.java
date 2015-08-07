package com.wintersoldier.diabetesportal.bean;

/**
 * Created by mduby on 7/28/15.
 */
public interface Property extends DataSet {

    public String getName();

    public String getDescription();

    public String getVariableType();

    public String getPropertyType();

    public boolean isSearchable();

    public int getSortOrder();
}
