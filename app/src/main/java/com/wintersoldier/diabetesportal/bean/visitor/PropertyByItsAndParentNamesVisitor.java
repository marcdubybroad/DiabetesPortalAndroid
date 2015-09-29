package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.util.PortalConstants;

/**
 * Created by mduby on 9/29/15.
 */
public class PropertyByItsAndParentNamesVisitor implements DataSetVisitor {
    // instance variables
    String propertyName = null;
    String sampleGroupName = null;
    String phenotypeName = null;
    Property property = null;

    /**
     * default constructor, given the 3 property names to search for
     *
     * @param propertyName
     * @param sampleGroupName
     * @param phenotypeName
     */
    public PropertyByItsAndParentNamesVisitor(String propertyName, String sampleGroupName, String phenotypeName) {
        this.propertyName = propertyName;
        this.sampleGroupName = sampleGroupName;
        this.phenotypeName = phenotypeName;
    }

    /**
     * visitor method to find single property given property, phenotype and sample group name
     *
     * @param dataSet
     */
    public void visit(DataSet dataSet) {
        // if property still not found
        if (this.property == null) {
            if (dataSet.getType() == PortalConstants.TYPE_PROPERTY_KEY) {
                Property tempProperty = (Property)dataSet;

                // see if this is the matching property
                if (tempProperty.isTheMatchingProperty(propertyName, sampleGroupName, phenotypeName)) {
                    this.property = tempProperty;
                }

            } else {
                for (DataSet child : dataSet.getAllChildren()) {
                    child.acceptVisitor(this);
                }
            }
        }
    }

    public Property getProperty() {
        return property;
    }
}
