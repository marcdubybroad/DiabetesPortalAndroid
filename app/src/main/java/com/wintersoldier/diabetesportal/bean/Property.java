package com.wintersoldier.diabetesportal.bean;

/**
 * Created by mduby on 7/28/15.
 */
public interface Property {

    public String getName();

    public String getDescription();

    public String getType();

    public boolean isSearchable();

    public int getSortOrder();
}
