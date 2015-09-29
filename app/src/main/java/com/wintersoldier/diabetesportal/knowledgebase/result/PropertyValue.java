package com.wintersoldier.diabetesportal.knowledgebase.result;

import com.wintersoldier.diabetesportal.bean.Property;

/**
 * Created by mduby on 9/29/15.
 */
public interface PropertyValue {
    public Property getProperty();

    public String getValue();

    /**
     * returns true if the property contained matches the 3 criteria names given
     * <br/>
     * its name, its sample group name and its phenotype name (the latter 2 can be null)
     *
     * @param propertyName
     * @param sampleGroupName
     * @param phenotypeName
     * @return
     */
    public boolean isTheMatchingPropertyValue(String propertyName, String sampleGroupName, String phenotypeName);
}
