package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.util.PortalException;

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

    public String getWebServiceQueryString() throws PortalException;

    /**
     * returns the filter string based on what type of property it is (common, dataset or phenotype property)
     *
     * @param operator
     * @param value
     * @return
     */
    public String getWebServiceFilterString(String operator, String value);

}
