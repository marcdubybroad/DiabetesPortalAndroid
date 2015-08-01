package com.wintersoldier.diabetesportal.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mduby on 8/1/15.
 */
public class JsonParserServiceTest {
    // instance variables
    private JsonParserService jsonParserService;

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

    @Test
    public void testGetJsonMetadata() {
        String jsonMetadata = null;

        // get the metadata and test
        jsonMetadata = this.jsonParserService.getJsonMetadata();
        assertNotNull(jsonMetadata);
        assertTrue(jsonMetadata.length() > 0);
    }
}
