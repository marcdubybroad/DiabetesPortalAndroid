package com.wintersoldier.diabetesportal.metadata.sort;

import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.Comparator;

/**
 * Created by mduby on 9/3/15.
 */
public class PropertyListForQueryComparator implements Comparator<Property> {

    public int compare(Property property1, Property property2) {
        int returnInt = 0;

        // sort properties in 2 different types based on type (puts them in 3 sections)
        if (property1.getPropertyType() != property2.getPropertyType()) {
            returnInt = property1.getPropertyType().compareTo(property2.getPropertyType());

            // if the properties are of the same type
        } else {
            // then simply order each by name except for pproperties
            if (property1.getPropertyType() != PortalConstants.TYPE_PHENOTYPE_PROPERTY_KEY) {
                returnInt = property1.getName().compareTo(property2.getName());

            } else if (!property1.getName().equals(property2.getName())) {
                returnInt = property1.getName().compareTo(property2.getName());

            } else {
                // if both pproperties and same name, then sort by sample group grandparent (parent will be phenotype)
                returnInt = property1.getParent().getParent().getId().compareTo(property2.getParent().getParent().getId());
            }

            /* bogus code, could save for later
            // common properties
            if (property1.getPropertyType() == PortalConstants.TYPE_COMMON_PROPERTY_KEY) {
                returnInt = property1.getName().compareTo(property2.getName());

            // sample group properties, order by sample group id to make sure they group by distinct sample groups
            } else if (property1.getPropertyType() == PortalConstants.TYPE_SAMPLE_GROUP_PROPERTY_KEY) {
                returnInt = property1.getParent().getId().compareTo(property2.getParent().getId());

            // phenotype properties
            } else {
                // sort by property name
                returnInt = property1.getName().compareTo(property2.getName());
            }
            */
        }

        // return result
        return returnInt;
    }
}
