package com.wintersoldier.diabetesportal.metadata.query;

import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.metadata.sort.PropertyListForQueryComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mduby on 9/3/15.
 */
public class GetDataQueryBean implements GetDataQuery {

    // local variables
    private Map<String, Property> queryPropertyMap = new HashMap<String, Property>();
    private List<QueryFilter> filterList = new ArrayList<QueryFilter>();
    String passback = "abc123";
    String entity = "variant";
    int pageNumber = 0;
    int pageSize = 100;
    int limit = 1000;
    boolean isCount = false;


    public void addQueryProperty(Property property) {
        this.queryPropertyMap.put(property.getId(), property);
    }

    public void addFilterProperty(Property property, String operator, String value) {
        this.filterList.add(new QueryFilterBean(property, operator, value));
    }

    public void addOrderByProperty(Property property) {

    }

    public void isCount(boolean isCountQuery) {
        this.isCount = isCountQuery;
    }

    public void setPassback(String passback) {
        this.passback = passback;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getPassback() {
        return passback;
    }

    public String getEntity() {
        return entity;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isCount() {
        return isCount;
    }

    /**
     * returns a sorted list of distinct properties to query
     *
     * @return
     */
    public List<Property> getQueryPropertyList() {
        List<Property> propertyList = new ArrayList<Property>();

        // add all distinct properties
        propertyList.addAll(this.queryPropertyMap.values());

        // sort
        Collections.sort(propertyList, new PropertyListForQueryComparator());

        // return
        return propertyList;
    }

    public List<QueryFilter> getFilterList() {
        return filterList;
    }
}
