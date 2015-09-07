package com.wintersoldier.diabetesportal.metadata.query;

import com.wintersoldier.diabetesportal.bean.Property;

/**
 * Created by mduby on 9/3/15.
 */
public class QueryFilterBean implements QueryFilter {
    // instance variables
    Property property;
    String operator;
    String value;

    /**
     * default constructor
     *
     * @param property
     * @param operator
     * @param value
     */
    public QueryFilterBean(Property property, String operator, String value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * returns the filter string for the property and values given
     *
     * @return
     */
    public String getFilterString() {
        return (this.property == null ? "" : property.getWebServiceFilterString(operator, value));
    }

    public Property getProperty() {
        return property;
    }

    public String getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }
}
