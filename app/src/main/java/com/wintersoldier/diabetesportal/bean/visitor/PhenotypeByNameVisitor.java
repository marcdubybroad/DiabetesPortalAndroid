package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Phenotype;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/25/15.
 */
public class PhenotypeByNameVisitor implements DataSetVisitor {
    List<Phenotype> phenotypeList = new ArrayList<Phenotype>();
    private String phenotypeName = "";

    public PhenotypeByNameVisitor(){
    }
    public PhenotypeByNameVisitor(String phenotypeName){
        this.phenotypeName = phenotypeName;
    }

    public List<Phenotype> getPhenotypeList() {
        return phenotypeList;
    }

    /***
     * If we have a phenotype then only pull back phenotypes of that name. Otherwise pull back all phenotypes.
     *
     * @param dataSet
     */
    public void visit(DataSet dataSet) {
        if (dataSet.getType() == PortalConstants.TYPE_PHENOTYPE_KEY) {
            Phenotype tempPhenotype = (Phenotype)dataSet;
            if ((this.phenotypeName!=null)&&
                    (this.phenotypeName.length()>0)){
                if (tempPhenotype.getName().equalsIgnoreCase(this.phenotypeName)) {
                    this.phenotypeList.add(tempPhenotype);
                }
            } else {
                this.phenotypeList.add(tempPhenotype);
            }

        } else {
            for (DataSet child : dataSet.getAllChildren()) {
                child.acceptVisitor(this);
            }
        }

    }

}
