package com.wintersoldier.diabetesportal.metadata.query;

import com.wintersoldier.diabetesportal.bean.Property;

import java.util.List;

/**
 * Created by mduby on 9/3/15.
 */
public interface GetDataQuery {

    public void addQueryProperty(Property property);

    public void addFilterProperty(Property property, String operator, String value);

    public void addOrderByProperty(Property property);

    public void isCount(boolean isCountQuery);

    public List<Property> getQueryPropertyList();

    public List<QueryFilter> getFilterList();

    public void setPassback(String passback);

    public void setEntity(String entity);

    public void setPageNumber(int pageNumber);

    public void setPageSize(int pageSize);

    public void setLimit(int limit);

    public String getPassback();

    public String getEntity();

    public int getPageNumber();

    public int getPageSize();

    public int getLimit();

    public boolean isCount();
}
