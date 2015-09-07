package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 9/3/15.
 */
public class PropertyByPropertyTypeVisitor implements DataSetVisitor {
    // instance variables
    String propertyType;
    List<Property> propertyList = new ArrayList<Property>();

    /**
     * default constructor that sets the property type to search for
     *
     * @param type
     */
    public PropertyByPropertyTypeVisitor (String type) {
        this.propertyType = type;
    }

    /**
     * visit the data set and add properties to the list that match given property type
     *
     * @param dataSet
     */
    public void visit(DataSet dataSet) {
        // if type property, then cast
        if (dataSet.getType() == PortalConstants.TYPE_PROPERTY_KEY) {
            // if property of type asked for, add to the property list
            Property property = (Property)dataSet;

            if (property.getPropertyType() == this.propertyType) {
                this.propertyList.add(property);
            }

            // properties have no children, so don't visit children

        } else {
            // visit children
            for (DataSet child: dataSet.getAllChildren()) {
                child.acceptVisitor(this);
            }
        }

    }

    /**
     * return the property list
     *
     * @return
     */
    public List<Property> getPropertyList() {
        return propertyList;
    }
}
