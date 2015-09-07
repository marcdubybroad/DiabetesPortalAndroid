package com.wintersoldier.diabetesportal.knowledgebase.result;

/**
 * Created by mduby on 9/3/15.
 */
public class VariantBean implements Variant {
    // instance variables
    private String chromosome;
    private String variantId;
    private String polyphenPredictor;
    private String siftPredictor;
    private Integer mostDelScore;


    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getPolyphenPredictor() {
        return polyphenPredictor;
    }

    public void setPolyphenPredictor(String polyphenPredictor) {
        this.polyphenPredictor = polyphenPredictor;
    }

    public String getSiftPredictor() {
        return siftPredictor;
    }

    public void setSiftPredictor(String siftPredictor) {
        this.siftPredictor = siftPredictor;
    }

    public String toString() {return this.variantId;}

    public Integer getMostDelScore() {
        return mostDelScore;
    }

    public void setMostDelScore(Integer mostDelScore) {
        this.mostDelScore = mostDelScore;
    }
}
