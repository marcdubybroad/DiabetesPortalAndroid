package com.wintersoldier.diabetesportal.metadata.result;

import com.wintersoldier.diabetesportal.bean.Property;
import com.wintersoldier.diabetesportal.knowledgebase.result.PropertyValue;
import com.wintersoldier.diabetesportal.knowledgebase.result.PropertyValueBean;
import com.wintersoldier.diabetesportal.knowledgebase.result.Variant;
import com.wintersoldier.diabetesportal.knowledgebase.result.VariantBean;
import com.wintersoldier.diabetesportal.service.JsonParserService;
import com.wintersoldier.diabetesportal.util.PortalConstants;
import com.wintersoldier.diabetesportal.util.PortalException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to parse getData variant array results into object form
 *
 */
public class KnowledgeBaseResultParser {
    // instance variables
    String jsonString;
    JsonParserService jsonParser = JsonParserService.getService();

    public KnowledgeBaseResultParser(String jsonString) {
        this.jsonString = jsonString;
    }

    /**
     * parse the json string and return the variant list
     *
     * @return
     * @throws PortalException
     */
    public List<Variant> parseResult() throws PortalException {
        // local variables
        Integer recordCountIndicated = null;
        String tempString = null;
        JSONObject jsonObject;
        JSONArray jsonArray;
        List<Variant> variantList = new ArrayList<Variant>();

        if (this.jsonString != null) {
            try {
                // get the json object
                jsonObject = new JSONObject(this.jsonString);

                // get the property count given
//                tempString = jsonObject.get(PortalConstants.JSON_NUMBER_RECORDS_KEY).toString();
//                recordCountIndicated = Integer.parseInt(tempString);

                // get the property json array
                jsonArray = jsonObject.getJSONArray(PortalConstants.JSON_VARIANTS_KEY);

                // the variants json array is a series of json object arrays
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONArray tempArray = jsonArray.getJSONArray(i);

                    // create variant
                    Variant variant = new VariantBean();

                    // parse
                    variant.addAllToPropertyValues(this.parsePropertyValues(tempArray));

                    // add variant to the list
                    variantList.add(variant);
                }

            } catch (JSONException exception) {
                throw new PortalException("Got json exception parsing getData result: " + exception.getMessage());
            }
        } else {
            throw new PortalException("Got null getData string to parse");
        }

        // return
        return variantList;
    }

    /**
     * parse the json array of json objects comprising one variant result entity
     *
     * @param jsonArray
     * @return
     * @throws PortalException
     */
    protected List<PropertyValue> parsePropertyValues(JSONArray jsonArray) throws PortalException {
        // local variables
        List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
        PropertyValue propertyValue = null;
        JSONObject jsonObject = null;

        try {
            // parse the array
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                propertyValue = this.parseProperty(jsonObject);
                propertyValueList.add(propertyValue);
            }

        } catch (JSONException exception) {
            throw new PortalException("Got json exception parsing getData result array: " + exception.getMessage());
        }

        // return
        return propertyValueList;
    }

    /**
     * parse the json object comprising one property value result
     *
     * @param jsonObject
     * @return
     * @throws PortalException
     */
    protected PropertyValue parseProperty(JSONObject jsonObject) throws PortalException {
        // local variables
        PropertyValue propertyValue = null;
        String propertyName = null;
        String sampleGroupName = null;
        String phenotypeName = null;
        String value = null;
        String tempString = null;
        JSONObject tempObject = null;
        Property property = null;

        // the property name is the key
        Iterator<String> keyIterator = jsonObject.keys();
        propertyName = keyIterator.next();

        // now find out if is is a complex object
        try {
            tempObject = jsonObject.getJSONObject(propertyName);

        } catch (JSONException exception) {
            // do nothing, not a key/value object
        }

        try {
            // if temp object is still null, then the value is a string
            if (tempObject == null) {
                tempString = jsonObject.get(propertyName).toString();
                value = tempString;

            } else  {
                // try again to see if the object is more complex than a sample group property
                // key of new object will be sample group
                keyIterator = tempObject.keys();
                sampleGroupName = keyIterator.next();
                JSONObject tempObject2 = null;

                // if this sub object is simple key value pair, then only sample group property
                try {
                    tempObject2 = tempObject.getJSONObject(sampleGroupName);

                } catch (JSONException exception) {
                    // do nothing, not a key/value object
                }

                // if string still null, then it is a sample group property
                if (tempObject2 == null) {
                    tempString = tempObject.get(sampleGroupName).toString();
                    value = tempString;

                } else {
                    // this time, should be simple key/value pair
                    // key of new object will be phenotype
                    keyIterator = tempObject2.keys();
                    phenotypeName = keyIterator.next();
                    value = tempObject2.get(phenotypeName).toString();
                }
            }

        } catch (JSONException exception) {
            throw new PortalException("Got json exception parsing getData property value line: " + exception.getMessage());
        }

        // get the property from the parser
        property = this.jsonParser.getPropertyGivenItsAndPhenotypeAndSampleGroupNames(propertyName, phenotypeName, sampleGroupName);

        // create property value
        propertyValue = new PropertyValueBean(property, value);

        // return
        return propertyValue;
    }
}
