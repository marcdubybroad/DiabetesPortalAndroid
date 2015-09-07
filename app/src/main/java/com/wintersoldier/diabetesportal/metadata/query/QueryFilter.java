package com.wintersoldier.diabetesportal.metadata.query;

import com.wintersoldier.diabetesportal.bean.Property;

/**
 * Created by mduby on 9/3/15.
 */
public interface QueryFilter {

    /**
     * return a filter string for the getData call
     *
     * @return
     */
    public String getFilterString();

    public Property getProperty();

    public String getOperator();

    public String getValue();
}
