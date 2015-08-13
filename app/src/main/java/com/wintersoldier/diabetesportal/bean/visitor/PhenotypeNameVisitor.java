package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.visitor.DataSetVisitor;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.HashSet;

/**
 * Created by mduby on 8/7/15.
 */
public class PhenotypeNameVisitor implements DataSetVisitor {
    HashSet<String> phenotypNameSet = new HashSet<String>();

    @Override
    public void visit(DataSet dataSet) {
        // if phenotype, then add name
        if (dataSet.getType() == PortalConstants.TYPE_PHENOTYPE_KEY) {
            this.phenotypNameSet.add(dataSet.getName());

            // if not, then only look at children if not property (leaf node)
        } else if (dataSet.getType() != PortalConstants.TYPE_PROPERTY_KEY) {
            for (DataSet child : dataSet.getAllChildren()) {
                child.acceptVisitor(this);
            }
        }
    }

    /**
     * return the phenotype name set
     *
     * @return
     */
    public HashSet<String> getResultSet() {
        return phenotypNameSet;
    }
}
