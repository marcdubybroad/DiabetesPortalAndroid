package com.wintersoldier.diabetesportal.service;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.DataSetBean;
import com.wintersoldier.diabetesportal.bean.Experiment;
import com.wintersoldier.diabetesportal.bean.ExperimentBean;
import com.wintersoldier.diabetesportal.util.PortalConstants;
import com.wintersoldier.diabetesportal.util.PortalException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
        JSONObject jsonObject = null;
        JSONTokener tokener = null;

        // get the json
        tokener = new JSONTokener(this.getJsonMetadata());

        // parse the json

        // return
        return experimentList;
    }

    protected String getJsonMetadata() {
        // local variables
        String jsonString = null;

        // read the file
        jsonString = "test";

        // return
        return jsonString;
    }

    /**
     * method to return a list of experiments from the json string
     *
     * @param experimentList                a not null experiment list
     * @param jsonString                    a not null json string
     * @throws JSONException                if there are any errors
     */
    protected void parseExperiments(List<Experiment> experimentList, String jsonString) throws PortalException {
        // local variables
        JSONTokener tokener;
        JSONObject rootJson, tempJson;
        JSONArray experimentArray, dataSetArray;
        DataSet dataSet;

        try {
            // create the tokener
            tokener = new JSONTokener(jsonString);

            // create the json object from the string
            rootJson = new JSONObject(tokener);

            // get the array of experiments
            experimentArray = rootJson.getJSONArray(PortalConstants.JSON_EXPERIMENT_KEY);

            // reinitialize the list
            experimentList.clear();

            // build the experiments
            for (int i = 0; i < experimentArray.length(); i++) {
                tempJson = experimentArray.getJSONObject(i);
                ExperimentBean experiment = new ExperimentBean();
                experiment.setName(tempJson.getString(PortalConstants.JSON_NAME_KEY));
                experimentList.add(experiment);

                // look for sample groups
                dataSetArray = tempJson.getJSONArray(PortalConstants.JSON_DATASETS_KEY);
                for (int j = 0; j < dataSetArray.length(); j++) {
                    // for each dataset, create a dataset object and add to experiment
                    dataSet = this.createDataSetFromJson(dataSetArray.getJSONObject(j));
                    experiment.getDataSets().add(dataSet);
                }
            }

        } catch (JSONException exception) {
            // log error if need be, convert exception
            throw new PortalException(exception.getMessage());
        }
    }

    /**
     * create and add dataset from json object
     *
     * @param jsonObject
     * @return
     * @throws PortalException
     */
    protected DataSet createDataSetFromJson(JSONObject jsonObject) throws PortalException {
        // local variables
        DataSetBean dataSet = new DataSetBean();

        try {
            // create the object and add the primitive variables
            dataSet.setName(jsonObject.getString(PortalConstants.JSON_NAME_KEY));
            dataSet.setAncestry(jsonObject.getString(PortalConstants.JSON_ANCESTRY_KEY));

            // add in properties

            // add in phenotypes

            // recursively add in any other child sampel groups

        } catch (JSONException exception) {
            throw new PortalException("Got error creating dataSet: " + exception.getMessage());
        }

        // return the object
        return dataSet;
    }

    /*
    protected JSONArray extractArrayFromJson(final JSONObject jsonObject, final String key) throws JSONException {
        final JSONArray jsonArray =  jsonObject.getJSONArray(key);
    }
    */
}
