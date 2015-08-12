package com.wintersoldier.diabetesportal.service;

import android.content.Context;
import android.util.Log;

import com.wintersoldier.diabetesportal.R;
import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.MetaDataRoot;
import com.wintersoldier.diabetesportal.bean.MetaDataRootBean;
import com.wintersoldier.diabetesportal.bean.SampleGroup;
import com.wintersoldier.diabetesportal.bean.SampleGroupBean;
import com.wintersoldier.diabetesportal.bean.Experiment;
import com.wintersoldier.diabetesportal.bean.ExperimentBean;
import com.wintersoldier.diabetesportal.bean.Phenotype;
import com.wintersoldier.diabetesportal.bean.PhenotypeBean;
import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.bean.PropertyBean;
import com.wintersoldier.diabetesportal.bean.visitor.DataSetVisitor;
import com.wintersoldier.diabetesportal.bean.visitor.PhenotypeNameVisitor;
import com.wintersoldier.diabetesportal.bean.visitor.SampleGroupForPhenotypeVisitor;
import com.wintersoldier.diabetesportal.util.PortalConstants;
import com.wintersoldier.diabetesportal.util.PortalException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mduby on 7/29/15.
 */
public class JsonParserService {
    // static singleton
    private static JsonParserService service;

    // private cached results
    private MetaDataRoot metaDataRoot = null;
    private Context context;                            // better to pass this in at method time, but gums up junit tests then
    private String jsonString;

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
    /*
    public List<Experiment> getAllExperiments() {
        // check to see if the experiments are already loaded
        if (this.metaDataRoot == null) {
            // load the json
            this.metaDataRoot = new MetaDataRootBean();

            try {
                // get the metadata root experiment
                this.parseExperiments(this.metaDataRoot.getExperiments());


            } catch (PortalException exception) {
                // TODO - message
                Log.e(this.getClass().getName(), "Got portal exception loading experiments: " + exception.getMessage());
            }
        }

        // return
        return this.metaDataRootBean.getExperiments();
    }
    */

    /**
     * returns the root object containing the parsed json onformation
     *
     * @return
     */
    public MetaDataRoot getMetaDataRoot() throws PortalException {
        if (this.metaDataRoot == null) {
            // create a new metadata root object
            this.metaDataRoot = this.populateMetaDataRoot();
        }

        return metaDataRoot;
    }

    protected MetaDataRoot populateMetaDataRoot() throws PortalException {
        // local variables
        String jsonString;
        JSONTokener tokener;
        JSONObject rootJson;
        MetaDataRootBean metaDataBean = new MetaDataRootBean();
        JSONArray tempArray;
        Property tempProperty;

        // parse the json
        try {
            // get the json string
            jsonString = this.getJsonMetadata();

            // create the tokener
            tokener = new JSONTokener(jsonString);

            // create the json object from the string
            rootJson = new JSONObject(tokener);

            // parse the experiments
            this.parseExperiments(metaDataBean.getExperiments(), rootJson);

            // parse the common properties
            // get the sub properties
            tempArray = rootJson.getJSONArray(PortalConstants.JSON_PROPERTIES_KEY);
            for (int i = 0; i < tempArray.length(); i++) {
                tempProperty = this.createPropertyFromJson(tempArray.getJSONObject(i), metaDataBean);
                metaDataBean.getProperties().add(tempProperty);
            }

        } catch (JSONException exception) {
            throw new PortalException("Got error creating metadata root: " + exception.getMessage());
        }

        return metaDataBean;
    }

    /**
     * return all the disctint phneotype names in the metadata
     *
     * @return
     */
    public List<String> getAllDistinctPhenotypeNames() throws PortalException {
        // instance variables
        HashSet<String> hashSet = new HashSet<String>();
        PhenotypeNameVisitor visitor = new PhenotypeNameVisitor();
        List<String> nameList;

        // go through tree and add in any phenotype names
        for (Experiment experiment : this.getMetaDataRoot().getExperiments()) {
            experiment.acceptVisitor(visitor);
        }
        hashSet = visitor.getResultSet();

        // convert the set to a list
        nameList = new ArrayList<String>();
        nameList.addAll(hashSet);

        // sort the list
        Collections.sort(nameList);

        // return the list
        return nameList;
    }


    /**
     * load all the getMetadata experiment data
     *
     * @return
     */
    /*
    protected List<Experiment> loadExperiments() throws PortalException {
        // instance variables
        List<Experiment> experimentList = new ArrayList<Experiment>();
        JSONObject jsonObject = null;
        JSONTokener tokener = null;

        // get the json
        tokener = new JSONTokener(this.getJsonMetadata());

        // parse the json
        this.parseExperiments(experimentList);

        // return
        return experimentList;
    }
    */

    /**
     * get the json metadata
     *
     * @return
     */
    protected String getJsonMetadata() {
        // local variables
        String jsonString = null;

        // read the file
        if (this.context != null) {
            InputStream inputStream = context.getResources().openRawResource(R.raw.metadata);
            jsonString = new Scanner(inputStream).useDelimiter("\\A").next();

        } else {
            // TODO - mostly for junit testing sake; need to refactor
            jsonString = (this.jsonString != null ? this.jsonString : "");
        }

        // return
        return jsonString;
    }

    /**
     * method to return a list of experiments from the json string
     *
     * @param experimentList                a not null experiment list
     * @throws JSONException                if there are any errors
     */
    protected void parseExperiments(List<Experiment> experimentList, JSONObject rootJson) throws PortalException {
        // local variables
        JSONObject tempJson;
        JSONArray experimentArray, dataSetArray;
        SampleGroup sampleGroup;

        try {
            // get the array of experiments
            experimentArray = rootJson.getJSONArray(PortalConstants.JSON_EXPERIMENT_KEY);

            // reinitialize the experiment list
            experimentList.clear();

            // build the experiments
            for (int i = 0; i < experimentArray.length(); i++) {
                tempJson = experimentArray.getJSONObject(i);
                ExperimentBean experiment = new ExperimentBean();
                experiment.setName(tempJson.getString(PortalConstants.JSON_NAME_KEY));
                experiment.setTechnology(tempJson.getString(PortalConstants.JSON_TECHNOLOGY_KEY));
                experiment.setVersion(tempJson.getString(PortalConstants.JSON_VERSION_KEY));
                experimentList.add(experiment);

                // look for sample groups
                dataSetArray = tempJson.getJSONArray(PortalConstants.JSON_DATASETS_KEY);
                for (int j = 0; j < dataSetArray.length(); j++) {
                    // for each dataset, create a dataset object and add to experiment
                    sampleGroup = this.createDataSetFromJson(dataSetArray.getJSONObject(j), experiment);
                    experiment.getSampleGroups().add(sampleGroup);
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
    protected SampleGroup createDataSetFromJson(JSONObject jsonObject, DataSet parent) throws PortalException {
        // local variables
        SampleGroupBean sampleGroup = new SampleGroupBean();
        JSONArray tempArray;
        SampleGroup tempSampleGroup;
        Property tempProperty;
        Phenotype tempPhenotype;

        try {
            // create the object and add the primitive variables
            sampleGroup.setName(jsonObject.getString(PortalConstants.JSON_NAME_KEY));
            sampleGroup.setAncestry(jsonObject.getString(PortalConstants.JSON_ANCESTRY_KEY));
            sampleGroup.setSystemId(jsonObject.getString(PortalConstants.JSON_ID_KEY));
            sampleGroup.setParent(parent);

            // add in properties
            tempArray = jsonObject.getJSONArray(PortalConstants.JSON_PROPERTIES_KEY);
            for (int i = 0; i < tempArray.length(); i++) {
                tempProperty = this.createPropertyFromJson(tempArray.getJSONObject(i), sampleGroup);
                sampleGroup.getProperties().add(tempProperty);
            }

            // add in phenotypes
            tempArray = jsonObject.getJSONArray(PortalConstants.JSON_PHENOTYPES_KEY);
            for (int i = 0; i < tempArray.length(); i++) {
                tempPhenotype = this.createPhenotypeFromJson(tempArray.getJSONObject(i), sampleGroup);
                sampleGroup.getPhenotypes().add(tempPhenotype);
            }

            // recursively add in any other child sample groups
            tempArray = jsonObject.getJSONArray(PortalConstants.JSON_DATASETS_KEY);
            for (int i = 0; i < tempArray.length(); i++) {
                tempSampleGroup = this.createDataSetFromJson(tempArray.getJSONObject(i), sampleGroup);
                sampleGroup.getChildren().add(tempSampleGroup);
            }

        } catch (JSONException exception) {
            throw new PortalException("Got error creating dataSet: " + exception.getMessage());
        }

        // return the object
        return sampleGroup;
    }

    /**
     * create a property object from the json object handed in
     *
     * @param jsonObject
     * @return
     * @throws PortalException
     */
    protected Property createPropertyFromJson(JSONObject jsonObject, DataSet parent) throws PortalException {
        // local variables
        PropertyBean property = new PropertyBean();
        String tempJsonValue;
        
        // get values and put in new object
        try {
            property.setName(jsonObject.getString(PortalConstants.JSON_NAME_KEY));
            property.setVariableType(jsonObject.getString(PortalConstants.JSON_TYPE_KEY));
            tempJsonValue = jsonObject.getString(PortalConstants.JSON_SEARCHABLE_KEY);
            if (tempJsonValue != null) {
                property.setSearchable(Boolean.valueOf(tempJsonValue));
            }
            tempJsonValue = jsonObject.getString(PortalConstants.JSON_SORT_ORDER_KEY);
            if (tempJsonValue != null) {
                property.setSortOrder(Integer.valueOf(tempJsonValue));
            }
            property.setParent(parent);

        } catch (JSONException exception) {
            throw new PortalException("Got property building exception: " + exception.getMessage());
        }

        return property;
    }

    /**
     * create a phenotype object from a given json object
     *
     * @param jsonObject
     * @return
     * @throws PortalException
     */
    protected Phenotype createPhenotypeFromJson(JSONObject jsonObject, DataSet parent) throws PortalException {
        // local variables
        PhenotypeBean phenotype = new PhenotypeBean();
        JSONArray tempArray;
        Property tempProperty;

        // get values from json object and put in new phenotype object
        try {
            // get the primitive variables
            phenotype.setName(jsonObject.getString(PortalConstants.JSON_NAME_KEY));
            phenotype.setGroup(jsonObject.getString(PortalConstants.JSON_GROUP_KEY));

            // get the sub properties
            tempArray = jsonObject.getJSONArray(PortalConstants.JSON_PROPERTIES_KEY);
            for (int i = 0; i < tempArray.length(); i++) {
                tempProperty = this.createPropertyFromJson(tempArray.getJSONObject(i), phenotype);
                phenotype.getProperties().add(tempProperty);
            }

        } catch (JSONException exception) {
            throw new PortalException("Got phenotype parsign error: " + exception.getMessage());
        }

        return phenotype;
    }

    /**
     * returns a sorted list of the names of sample groups that contain the given phenotype
     *
     * @param phenotypeName
     * @return
     */
    public List<String> getSamplesGroupsForPhenotype(String phenotypeName) throws PortalException {
        // local variables
        SampleGroupForPhenotypeVisitor sampleGroupVisitor = new SampleGroupForPhenotypeVisitor(phenotypeName);
        List<String> sampleGroupNameList;

        // pass in visitor looking for sample groups with the selected phenotype
        for (Experiment experiment: this.getMetaDataRoot().getExperiments()) {
            experiment.acceptVisitor(sampleGroupVisitor);
        }

        // return the resulting string list
        sampleGroupNameList = sampleGroupVisitor.getSampleGroupNameList();

        // sort and return
        Collections.sort(sampleGroupNameList);
        return sampleGroupNameList;
    }

    /*
    protected JSONArray extractArrayFromJson(final JSONObject jsonObject, final String key) throws JSONException {
        final JSONArray jsonArray =  jsonObject.getJSONArray(key);
    }
    */

    public void setContext(Context context) {
        this.context = context;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
