package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.util.PortalConstants;

/**
 * Created by mduby on 8/3/15.
 */
public class PropertyBean implements Property {
    private String name;
    private String description;
    private String variableType;
    private int sortOrder;
    private boolean searchable;
    private DataSet parent;

    public void setParent(DataSet parent) {
        this.parent = parent;
    }

    public String getType() {
        return PortalConstants.TYPE_PROPERTY_KEY;
    }

    public String getPropertyType() {
        String propertyType = PortalConstants.TYPE_COMMON_PROPERTY_KEY;

        if (this.parent != null) {
            if (this.parent.getType() == PortalConstants.TYPE_SAMPLE_GROUP_KEY) {
                propertyType = PortalConstants.TYPE_SAMPLE_GROUP_PROPERTY_KEY;
            } else if (this.getParent().getType() == PortalConstants.TYPE_PHENOTYPE_KEY) {
                propertyType = PortalConstants.TYPE_PHENOTYPE_PROPERTY_KEY;
            }
        }

        return propertyType;
    }

    public String getId() {
        return (this.parent == null ? "" : this.parent.getId()) + this.getId();
    }

    public DataSet getParent() {
        return this.parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @Override

    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getVariableType() {
        return this.variableType;
    }

    @Override
    public boolean isSearchable() {
        return this.searchable;
    }

    @Override
    public int getSortOrder() {
        return this.sortOrder;
    }
}
