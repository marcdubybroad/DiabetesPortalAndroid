package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/25/15.
 */
public class PropertyPerExperimentVisitor implements DataSetVisitor {
    private String propertyName;
    private List<Property> propertyList = new ArrayList<Property>();

    public PropertyPerExperimentVisitor(String propertyName){
        this.propertyName = propertyName;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void visit(DataSet dataSet) {
        if (dataSet.getType().equals(PortalConstants.TYPE_PROPERTY_KEY)) {
            if (dataSet.getName().equalsIgnoreCase(this.propertyName)){
                this.propertyList.add((Property)dataSet);

            }
        } else {
            for (DataSet child:dataSet.getAllChildren()){
                child.acceptVisitor(this);
            }
        }

    }

}
