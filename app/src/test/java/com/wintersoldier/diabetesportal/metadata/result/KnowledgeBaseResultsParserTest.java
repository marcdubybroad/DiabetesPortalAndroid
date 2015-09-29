package com.wintersoldier.diabetesportal.metadata.result;

import com.wintersoldier.diabetesportal.knowledgebase.result.PropertyValue;
import com.wintersoldier.diabetesportal.knowledgebase.result.Variant;
import com.wintersoldier.diabetesportal.service.JsonParserService;
import com.wintersoldier.diabetesportal.util.PortalConstants;
import com.wintersoldier.diabetesportal.util.PortalException;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Test class to test the json parsing service for the metadata objects
 */
public class KnowledgeBaseResultsParserTest extends TestCase {
    JsonParserService jsonParser;
    String jsonString;

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
        // get the json strong to test
        try {
            jsonString = new Scanner( getFileFromPath(this, "res/metadata.json") ).useDelimiter("\\A").next();
            this.jsonParser = JsonParserService.getService();
            this.jsonParser.setJsonString(jsonString);

        } catch (FileNotFoundException exception) {
            fail("got file exception: " + exception.getMessage());
        }
    }

    public void testSetup() {
        assertNotNull(this.jsonParser);
//        assertNotNull(this.jsonParser.getJsonMetadata());
    }

    @Test
    public void testCommonPropertyResult() {
        // local variables
        String jsonResult = "{\"CHROM\": \"7\"}";
        PropertyValue propertyValue = null;
        JSONObject jsonObject = null;

        // parse string and see what we get
        try {
            // passing in this string just done to create object and test it's protected method by itself
            KnowledgeBaseResultParser knowledgeBaseResultParser = new KnowledgeBaseResultParser(jsonResult);
            jsonObject = new JSONObject(jsonResult);
            propertyValue = knowledgeBaseResultParser.parseProperty(jsonObject);

        } catch (PortalException exception) {
            fail("Got error parsing common property: " + exception.getMessage());

        } catch (JSONException exception) {
            fail("Got jeon exception: " + exception.getMessage());
        }

        // test
        assertNotNull(jsonObject);
        assertNotNull(propertyValue);
        assertEquals("7", propertyValue.getValue());
        assertNotNull(propertyValue.getProperty());
        assertEquals(PortalConstants.TYPE_COMMON_PROPERTY_KEY, propertyValue.getProperty().getPropertyType());
        assertEquals("metadata_rootCHROM", propertyValue.getProperty().getId());
    }

    @Test
    public void testSampleGroupPropertyResult() {
        // local variables
        String jsonResult = "{\"MAF\": {\"ExSeq_17k_eu_mdv2\": 0.000441209 }}";
        PropertyValue propertyValue = null;
        JSONObject jsonObject = null;

        // parse string and see what we get
        try {
            // passing in this string just done to create object and test it's protected method by itself
            KnowledgeBaseResultParser knowledgeBaseResultParser = new KnowledgeBaseResultParser(jsonResult);
            jsonObject = new JSONObject(jsonResult);
            propertyValue = knowledgeBaseResultParser.parseProperty(jsonObject);

        } catch (PortalException exception) {
            fail("Got error parsing sample group property: " + exception.getMessage());

        } catch (JSONException exception) {
            fail("Got jeon exception: " + exception.getMessage());
        }

        // test
        assertNotNull(jsonObject);
        assertNotNull(propertyValue);
        assertEquals("4.41209E-4", propertyValue.getValue());
        assertNotNull(propertyValue.getProperty());
        assertEquals(PortalConstants.TYPE_SAMPLE_GROUP_PROPERTY_KEY, propertyValue.getProperty().getPropertyType());
        assertEquals("metadata_root_ExSeq_17k_mdv2_17k_17k_euMAF", propertyValue.getProperty().getId());
    }

    @Test
    public void testPhenotypePropertyResult() {
        // local variables
        String jsonResult = "{\"OR_FIRTH_FE_IV\": {\"ExSeq_17k_eu_mdv2\": { \"T2D\": 27.8295943657392}}}";
        PropertyValue propertyValue = null;
        JSONObject jsonObject = null;

        // parse string and see what we get
        try {
            // passing in this string just done to create object and test it's protected method by itself
            KnowledgeBaseResultParser knowledgeBaseResultParser = new KnowledgeBaseResultParser(jsonResult);
            jsonObject = new JSONObject(jsonResult);
            propertyValue = knowledgeBaseResultParser.parseProperty(jsonObject);

        } catch (PortalException exception) {
            fail("Got error parsing sample group property: " + exception.getMessage());

        } catch (JSONException exception) {
            fail("Got jeon exception: " + exception.getMessage());
        }

        // test
        assertNotNull(jsonObject);
        assertNotNull(propertyValue);
        assertEquals("27.8295943657392", propertyValue.getValue());
        assertNotNull(propertyValue.getProperty());
        assertEquals(PortalConstants.TYPE_PHENOTYPE_PROPERTY_KEY, propertyValue.getProperty().getPropertyType());
        assertEquals("metadata_root_ExSeq_17k_mdv2_17k_17k_euT2DOR_FIRTH_FE_IV", propertyValue.getProperty().getId());
    }

    @Test
    public void testParsePropertyValues() {
        // local variables
        String jsonResult = "[{\"VAR_ID\": \"7_50121444_G_A\"},{\"MAF\": {\"ExSeq_17k_eu_mdv2\": 0.000441209 }},{\"OR_FIRTH_FE_IV\": {\"ExSeq_17k_eu_mdv2\": { \"T2D\": 27.8295943657392}}}]";
        List<PropertyValue> propertyValueList = null;
        JSONArray jsonArray = null;

        // parse
        try {
            KnowledgeBaseResultParser knowledgeBaseResultParser = new KnowledgeBaseResultParser(jsonResult);
            jsonArray = new JSONArray(jsonResult);
            propertyValueList = knowledgeBaseResultParser.parsePropertyValues(jsonArray);

        } catch (PortalException exception) {
            fail("Got error parsing sample group property: " + exception.getMessage());

        } catch (JSONException exception) {
            fail("Got jeon exception: " + exception.getMessage());
        }

        // test
        assertNotNull(jsonArray);
        assertNotNull(propertyValueList);
        assertEquals(3, propertyValueList.size());
    }

    @Test
    public void testParseResult() {
        // local variables
        String jsonResult = null;
        List<Variant> variantList = null;
        KnowledgeBaseResultParser knowledgeBaseResultParser = null;

        // load the test file
        try {
            jsonResult = new Scanner( getFileFromPath(this, "res/getdataresult.json") ).useDelimiter("\\A").next();
            knowledgeBaseResultParser = new KnowledgeBaseResultParser(jsonResult);

        } catch (FileNotFoundException exception) {
            fail("got file exception: " + exception.getMessage());
        }

        // parse
        try {
            variantList = knowledgeBaseResultParser.parseResult();

        } catch (PortalException exception) {
            fail("Got error: " + exception.getMessage());
        }

        // test
        assertNotNull(jsonResult);
        assertNotNull(variantList);
        assertEquals(2, variantList.size());

        // loop and test properties on variants
        for (Variant variant : variantList) {
            assertEquals(13, variant.getPropertyValues().size());
        }
    }
}
