package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 7/28/15.
 */
public interface SampleGroup extends DataSet {

    public List<SampleGroup> getChildren();

    public List<SampleGroup> getRecursiveChildren();

    public List<SampleGroup> getRecursiveParents();

    public List<SampleGroup> getRecursiveChildrenForPhenotype(Phenotype phenotype);

    public List<SampleGroup> getRecursiveParentsForPhenotype(Phenotype phenotype);

    public List<Phenotype> getPhenotypes();

    public List<Property> getProperties();

    public String getName();

    public String getAncestry();

    public int getSortOrder();

}
