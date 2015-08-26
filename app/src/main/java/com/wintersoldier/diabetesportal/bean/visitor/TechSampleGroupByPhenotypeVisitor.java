package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Experiment;
import com.wintersoldier.diabetesportal.bean.Phenotype;
import com.wintersoldier.diabetesportal.bean.SampleGroup;
import com.wintersoldier.diabetesportal.util.PortalConstants;

/**
 * Created by mduby on 8/25/15.
 */
public class TechSampleGroupByPhenotypeVisitor implements DataSetVisitor {
    // instance avriables
    SampleGroup sampleGroup;
    String phenotypeToSearchFor;
    String techName;

    /**
     * default constructor with the given phenotype to search for
     *
     * @param phenotype
     */
    public TechSampleGroupByPhenotypeVisitor(String techName, String phenotype) {
        this.phenotypeToSearchFor = phenotype;
        this.techName = techName;
    }

    /**
     * main visitor method
     *
     * @param dataSet
     */
    public void visit(DataSet dataSet) {
        // local variables

        // only keep searching if the sample group has not been found
        if (this.sampleGroup == null) {
            // make sure it is a sample group
            if (dataSet.getType() == PortalConstants.TYPE_SAMPLE_GROUP_KEY) {
                // check that it is a first level sample group (direct child of experiment)
                if ((dataSet.getParent() != null) && (dataSet.getParent().getType() == PortalConstants.TYPE_EXPERIMENT_KEY)) {
                    // cast parent to Experiment interface since we need to access Experiment specific method
                    Experiment parentExperiment = (Experiment)dataSet.getParent();

                    // make sure the experiment used GWAS technology
                    if (parentExperiment.getTechnology().equalsIgnoreCase(this.techName)) {
                        // cast to sample group object since we need to get to SG specific methods
                        SampleGroup tempGroup = (SampleGroup)dataSet;

                        // search through the sample group's phenotypes for the matching one
                        for (Phenotype phenotype : tempGroup.getPhenotypes()) {
                            if (phenotype.getName().equalsIgnoreCase(this.phenotypeToSearchFor)) {
                                this.sampleGroup = tempGroup;
                                break;
                            }
                        }
                    }

                }

            }

            // if sample group still not found, visit children
            if (this.sampleGroup == null) {
                for (DataSet child : dataSet.getAllChildren()) {
                    child.acceptVisitor(this);
                }
            }

        }
    }

    /**
     * return the sample group
     *
     * @return
     */
    public SampleGroup getSampleGroup() {
        return this.sampleGroup;
    }

    /**
     * return the sample group namee
     *
     * @return
     */
    public String getSampleGroupName() {
        if (this.sampleGroup != null) {
            return this.sampleGroup.getName();
        } else {
            return "";
        }
    }


}
