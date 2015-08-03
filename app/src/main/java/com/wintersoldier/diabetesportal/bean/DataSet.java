package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 7/28/15.
 */
public interface DataSet {

    public List<DataSet> getChildren();

    public List<DataSet> getRecursiveChildren();

    public List<DataSet> getRecursiveParents();

    public List<DataSet> getRecursiveChildrenForPhenotype(Phenotype phenotype);

    public List<DataSet> getRecursiveParentsForPhenotype(Phenotype phenotype);

    public List<Phenotype> getPhenotypes();

    public List<Property> getProperties();

    public String getName();

    public String getAncestry();

    public int getSortOrder();

}
