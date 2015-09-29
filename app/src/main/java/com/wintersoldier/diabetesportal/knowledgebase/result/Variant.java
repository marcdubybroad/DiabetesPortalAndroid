package com.wintersoldier.diabetesportal.knowledgebase.result;

import java.util.Collection;
import java.util.List;

/**
 * Created by mduby on 9/3/15.
 */
public interface Variant {
    public String getChromosome();

    public String getVariantId();

    public String getPolyphenPredictor();

    public String getSiftPredictor();

    public Integer getMostDelScore();

    public List<PropertyValue> getPropertyValues();

    public void addAllToPropertyValues(Collection<PropertyValue> values);

    public void addToPropertyValues(PropertyValue value);

    /**
     * returns the given property value if the given property search terms find one; null otherwise
     *
     * @param propertyName
     * @param sampleGroupName
     * @param phenotypeName
     * @return
     */
    public PropertyValue getPropertyValueFromCollection(String propertyName, String sampleGroupName, String phenotypeName);
}
