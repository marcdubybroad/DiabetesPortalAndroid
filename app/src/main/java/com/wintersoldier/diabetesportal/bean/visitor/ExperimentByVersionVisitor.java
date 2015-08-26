package com.wintersoldier.diabetesportal.bean.visitor;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.Experiment;
import com.wintersoldier.diabetesportal.util.PortalConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mduby on 8/25/15.
 */
public class ExperimentByVersionVisitor implements DataSetVisitor {
    List<Experiment> experimentList = new ArrayList<Experiment>();
    String versionToSearchFor;

    public ExperimentByVersionVisitor(String version) {
        this.versionToSearchFor =version;
    }

    public void visit(DataSet dataSet) {
        if (dataSet.getType() == PortalConstants.TYPE_EXPERIMENT_KEY) {
            Experiment tempExperiment = (Experiment)dataSet;
            if (tempExperiment.getVersion().equalsIgnoreCase(this.versionToSearchFor)) {
                this.experimentList.add(tempExperiment);
            }
        } else {
            for (DataSet child : dataSet.getAllChildren()) {
                child.acceptVisitor(this);
            }
        }
    }

    public List<Experiment> getExperimentList() {
        return experimentList;
    }

}
