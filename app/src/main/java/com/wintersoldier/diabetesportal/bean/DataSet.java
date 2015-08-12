package com.wintersoldier.diabetesportal.bean;

import com.wintersoldier.diabetesportal.bean.visitor.DataSetVisitor;

import java.util.List;

/**
 * Created by mduby on 8/6/15.
 */
public interface DataSet {

    public DataSet getParent();

    public String getId();

    public String getType();

    public void acceptVisitor(DataSetVisitor visitor);

    public String getName();

    public List<DataSet> getAllChildren();
}
