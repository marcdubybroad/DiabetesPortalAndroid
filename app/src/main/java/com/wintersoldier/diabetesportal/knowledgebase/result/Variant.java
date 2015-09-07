package com.wintersoldier.diabetesportal.knowledgebase.result;

/**
 * Created by mduby on 9/3/15.
 */
public interface Variant {
    public String getChromosome();

    public String getVariantId();

    public String getPolyphenPredictor();

    public String getSiftPredictor();

    public Integer getMostDelScore();
}
