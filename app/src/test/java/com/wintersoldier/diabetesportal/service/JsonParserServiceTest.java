package com.wintersoldier.diabetesportal.service;

import com.wintersoldier.diabetesportal.bean.DataSet;
import com.wintersoldier.diabetesportal.bean.MetaDataRoot;
import com.wintersoldier.diabetesportal.bean.MetaDataRootBean;
import com.wintersoldier.diabetesportal.bean.SampleGroup;
import com.wintersoldier.diabetesportal.bean.Experiment;
import com.wintersoldier.diabetesportal.util.PortalException;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mduby on 8/1/15.
 */
public class JsonParserServiceTest extends TestCase {
    // instance variables
    private JsonParserService jsonParserService;

    /**
     * get static file to test from
     *
     * @param obj
     * @param fileName
     * @return
     */
    private static File getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }

    @Before
    public void setUp() throws Exception {
        // set up the service
        this.jsonParserService = JsonParserService.getService();
    }

    @After
    public void tearDown() throws Exception {
        // Code that you wish to run after each test
    }

    @Test
    /**
     * test getting the instance and make sure unit tests work
     */
    public void testGetServiceInstance() {
        JsonParserService service = null;

        // make sure still null (test case)
        assertNull(service);

        // get the service and test not null
        service = JsonParserService.getService();
        assertNotNull(service);
    }

    /*
    @Test
    public void testGetJsonMetadata() {
        String jsonMetadata = null;

        // get the metadata and test
        jsonMetadata = this.jsonParserService.getJsonMetadata();
        assertNotNull(jsonMetadata);
        assertTrue(jsonMetadata.length() > 0);
    }
    */

    @Test
    public void testFileObjectShouldNotBeNull() throws Exception {
        File file = getFileFromPath(this, "res/metadata.json");
        assertNotNull(file);
    }

    @Test
    public void testParseExperiments() {
        // local variables
        List<Experiment> experimentList = new ArrayList<Experiment>();
        String jsonString = null;
        String simpleJsonString = "{\"experiments\": []}";
        JSONTokener tokener;
        JSONObject rootJson = null;
        MetaDataRootBean rootBean = new MetaDataRootBean();

        // get the json strong to test
        try {
            jsonString = new Scanner( getFileFromPath(this, "res/metadata.json") ).useDelimiter("\\A").next();
//            this.jsonParserService.setJsonString(jsonString);

            tokener = new JSONTokener(jsonString);
            rootJson = new JSONObject(tokener);

        } catch (FileNotFoundException exception) {
            fail("got file exception: " + exception.getMessage());
        } catch (JSONException exception) {
            fail("got json exception: " + exception.getMessage());
        }

        // parse the experiments
        try {
            this.jsonParserService.parseExperiments(experimentList, rootJson, rootBean);

        } catch (PortalException exception) {
            fail("got json parsing exception: " + exception.getMessage());
        }

        // test size and non null of list
        assertNotNull(experimentList);
        assertTrue(experimentList.size() > 0);
        assertEquals(25, experimentList.size());

        // test the children dataset
        Experiment experiment = experimentList.get(0);
        assertNotNull(experiment);
        assertTrue(experiment.getSampleGroups().size() > 0);

        // test the grandchildren datasets
        SampleGroup sampleGroup = experiment.getSampleGroups().get(0);
        assertNotNull(sampleGroup);
        assertTrue(sampleGroup.getSampleGroups().size() > 0);
        assertTrue(sampleGroup.getRecursiveChildren().size() > sampleGroup.getSampleGroups().size());

        // get first child of sampe group; make sure it has parent
        DataSet firstChild = sampleGroup.getSampleGroups().get(0);
        assertNotNull(firstChild);
        assertNotNull(firstChild.getParent());

    }

    /**
     * test the get phenotype name list
     */
    @Test
    public void testgetAllDistinctPhenotypeNames() {
        // local variables
        List<Experiment> experimentList = new ArrayList<Experiment>();
        String jsonString = null;
        String simpleJsonString = "{\"experiments\": []}";
        JSONTokener tokener;
        JSONObject rootJson = null;
        List<String> nameList = null;

        // get the json strong to test
        try {
            jsonString = new Scanner( getFileFromPath(this, "res/metadata.json") ).useDelimiter("\\A").next();
            this.jsonParserService.setJsonString(jsonString);

        } catch (FileNotFoundException exception) {
            fail("got file exception: " + exception.getMessage());
        }

        // get the phenotype name list
        try {
            nameList = this.jsonParserService.getAllDistinctPhenotypeNames();

        } catch (PortalException exception) {
            fail("Got portal exception: " + exception.getMessage());
        }
        assertNotNull(nameList);
        assertTrue(nameList.size() > 0);
        assertEquals(29, nameList.size());

    }

    /**
     * test the get sample group name for phenotype name list
     */
    @Test
    public void testgetSamplesGroupsForPhenotype() {
        // local variables
        List<Experiment> experimentList = new ArrayList<Experiment>();
        String jsonString = null;
        String phenotype = "T2D";
        JSONTokener tokener;
        JSONObject rootJson = null;
        List<String> nameList = null;

        // get the json strong to test
        try {
            jsonString = new Scanner( getFileFromPath(this, "res/metadata.json") ).useDelimiter("\\A").next();
            this.jsonParserService.setJsonString(jsonString);

        } catch (FileNotFoundException exception) {
            fail("got file exception: " + exception.getMessage());
        }

        // get the phenotype name list
        try {
            nameList = this.jsonParserService.getSamplesGroupsForPhenotype(phenotype);

        } catch (PortalException exception) {
            fail("Got portal exception: " + exception.getMessage());
        }
        assertNotNull(nameList);
        assertTrue(nameList.size() > 0);
        assertEquals(69, nameList.size());

    }

    @Test
    public void testGetMetaDataRoot() {
        // local variables
        String jsonString = null;
        MetaDataRoot metaData = null;

        // get the json strong to test
        try {
            jsonString = new Scanner( getFileFromPath(this, "res/metadata.json") ).useDelimiter("\\A").next();
            this.jsonParserService.setJsonString(jsonString);

        } catch (FileNotFoundException exception) {
            fail("got file exception: " + exception.getMessage());
        }

        // get the phenotype name list
        try {
            metaData = this.jsonParserService.getMetaDataRoot();

        } catch (PortalException exception) {
            fail("Got portal exception: " + exception.getMessage());
        }

        // test
        assertNotNull(metaData);
        assertNotNull(metaData.getExperiments());
        assertNotNull(metaData.getProperties());
        assertTrue(metaData.getExperiments().size() > 0);
        assertTrue(metaData.getProperties().size() > 0);
        assertEquals(25, metaData.getExperiments().size());
        assertEquals(13, metaData.getProperties().size());
    }

}
