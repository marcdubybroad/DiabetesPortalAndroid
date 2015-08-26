package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.util.PortalConstants;

/**
 * Created by mduby on 8/25/15.
 */
public class PropertyByNameFinderVisitor implements DataSetVisitor {
    // instance variables
    Property property;
    String propertyDatabaseId;

    /**
     * default constructor with the property id string to find by
     *
     * @param propertyId
     */
    public PropertyByNameFinderVisitor(String propertyId) {
        this.propertyDatabaseId = propertyId;
    }

    /**
     * traverse dataset tree until find the first property with that value
     *
     * @param dataSet
     */
    public void visit(DataSet dataSet) {
        if (property == null) {
            if (dataSet.getType() == PortalConstants.TYPE_PROPERTY_KEY) {
                if (dataSet.getName().equalsIgnoreCase(this.propertyDatabaseId)) {
                    this.property = (Property)dataSet;
                }
            } else {
                for (DataSet child : dataSet.getAllChildren()) {
                    child.acceptVisitor(this);
                }
            }
        }

    }

    /**
     * return the found property
     *
     * @return
     */
    public Property getProperty() {
        return property;
    }
}
