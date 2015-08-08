package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;

/**
 * Created by mduby on 8/7/15.
 */
public interface DataSetVisitor {

    public void visit(DataSet dataSet);
}
