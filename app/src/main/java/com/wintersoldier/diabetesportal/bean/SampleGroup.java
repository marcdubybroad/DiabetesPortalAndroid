package com.wintersoldier.diabetesportal.bean;

import java.util.List;

/**
 * Created by mduby on 7/28/15.
 */
public interface SampleGroup extends DataSet {

    public List<SampleGroup> getSampleGroups();

    public List<SampleGroup> getRecursiveChildren();

    public List<SampleGroup> getRecursiveParents();

    public List<SampleGroup> getRecursiveChildrenForPhenotype(Phenotype phenotype);

    public List<SampleGroup> getRecursiveParentsForPhenotype(Phenotype phenotype);

    public List<Phenotype> getPhenotypes();

    public List<Property> getProperties();

    public String getName();

    public String getAncestry();

    public int getSortOrder();

    public String getSystemId();

    /**
     * returns how many levels down this sample grou is nested in other sample groups
     *
     * @return
     */
    public Integer getNestedLevel();
}
