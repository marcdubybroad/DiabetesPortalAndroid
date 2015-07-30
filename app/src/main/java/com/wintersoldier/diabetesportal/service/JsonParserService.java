package com.wintersoldier.diabetesportal.service;

import com.wintersoldier.diabetesportal.bean.Experiment;

import java.util.List;

/**
 * Created by mduby on 7/29/15.
 */
public class JsonParserService {
    // static singleton
    private static JsonParserService service;

    // private cached results
    private List<Experiment> experimentList = null;

    /**
     * singleton service to parse metadata
     * @return
     */
    public static JsonParserService getService() {
        if (service == null) {
            service = new JsonParserService();
        }

        return service;
    }


    /**
     * return all the experiments in the metadata chache
     *
     * @return                  all the experiments in the metadata chache
     */
    public List<Experiment> getAllExperiments() {
        // check to see if the experiments are already loaded
        if (this.experimentList == null) {
            // load the json
            this.experimentList = this.loadExperiments();
        }

        // return
        return this.experimentList;
    }

    /**
     * load all the getMetadata experiment data
     *
     * @return
     */
    protected List<Experiment> loadExperiments() {
        // instance variables
        List<Experiment> experimentList = null;

        // get the json

        // parse the json

        // return
        return experimentList;
    }

}
