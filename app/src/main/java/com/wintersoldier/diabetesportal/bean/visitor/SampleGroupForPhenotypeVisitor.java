package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Experiment;
import com.wintersoldier.diabetesportal.bean.Phenotype;
import com.wintersoldier.diabetesportal.bean.SampleGroup;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/8/15.
 */
public class SampleGroupForPhenotypeVisitor implements DataSetVisitor {
    private List<String> sampleGroupNameList;
    private String phenotypeName;
    private List<SampleGroup> sampleGroupList;

    public SampleGroupForPhenotypeVisitor(String phenotypeName) {
        this.sampleGroupNameList = new ArrayList<String>();
        this.sampleGroupList = new ArrayList<SampleGroup>();
        this.phenotypeName = phenotypeName;
    }

    /**
     * method to visit sample group and add it's name to list if it contains the desired phenotype
     *
     * @param dataSet                   the data set to visit
     */
    public void visit(DataSet dataSet) {
        // local variables
        SampleGroup group;

        // if the data set is a sample group
        if (dataSet.getType() == PortalConstants.TYPE_SAMPLE_GROUP_KEY) {
            group = (SampleGroup)dataSet;

            // go through the phenotypes and see if the one being looked for is contained
            for (Phenotype phenotype: group.getPhenotypes()) {
                // if contained, then add sample group name to list
                if (phenotype.getName().equalsIgnoreCase(this.phenotypeName)) {
                    this.sampleGroupNameList.add(group.getSystemId());
                    this.sampleGroupList.add(group);
                    break;
                }
            }
        }

        for (DataSet child : dataSet.getAllChildren()) {
            child.acceptVisitor(this);
        }
    }

    public List<String> getSampleGroupNameList() {
        return sampleGroupNameList;
    }

    public List<SampleGroup> getSampleGroupList() {
        return sampleGroupList;
    }}
