package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.SampleGroup;
import com.wintersoldier.diabetesportal.util.PortalConstants;

/**
 * Created by mduby on 8/12/15.
 */
public class SampleGroupByIdSelectingVisitor implements DataSetVisitor {
    // instance variables
    private String sampleGroupId;
    private SampleGroup sampleGroup;

    /**
     * default constructor
     *
     * @param sampleGroupId
     */
    public SampleGroupByIdSelectingVisitor(String sampleGroupId) {
        this.sampleGroupId = sampleGroupId;
    }

    /**
     * visit the dataset looking for the given sample group id
     *
     * @param dataSet
     */
    public void visit(DataSet dataSet) {
        // local variables
        SampleGroup sampleGroup;

        if (this.sampleGroup == null) {
            if (dataSet.getType() == PortalConstants.TYPE_SAMPLE_GROUP_KEY) {
                sampleGroup = (SampleGroup)dataSet;

                if (sampleGroup.getSystemId().equalsIgnoreCase(this.sampleGroupId)) {
                    this.sampleGroup = sampleGroup;
                }
            }

            // if still null
            if (this.sampleGroup == null) {
                for (DataSet child : dataSet.getAllChildren()) {
                    child.acceptVisitor(this);
                }
            }
        }
    }

    /**
     * return the resulting sample group
     *
     * @return
     */
    public SampleGroup getSampleGroup() {
        return this.sampleGroup;
    }

}
