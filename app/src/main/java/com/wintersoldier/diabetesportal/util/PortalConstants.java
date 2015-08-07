package com.wintersoldier.diabetesportal.util;

/**
 * Created by mduby on 8/1/15.
 */
public class PortalConstants {

    // json mapping keys
    public final static String JSON_EXPERIMENT_KEY      = "experiments";
    public final static String JSON_VERSION_KEY         = "version";
    public final static String JSON_NAME_KEY            = "name";
    public final static String JSON_TYPE_KEY            = "type";
    public final static String JSON_GROUP_KEY            = "group";
    public final static String JSON_TECHNOLOGY_KEY      = "technology";
    public final static String JSON_ANCESTRY_KEY        = "ancestry";

    // json mapping array key values
    public final static String JSON_DATASETS_KEY        = "sample_groups";
    public final static String JSON_PROPERTIES_KEY      = "properties";
    public final static String JSON_PHENOTYPES_KEY      = "phenotypes";

    // constant types for metadata object tree
    public final static String TYPE_EXPERIMENT_KEY                      = "experiment";
    public final static String TYPE_SAMPLE_GROUP_KEY                    = "sample_group";
    public final static String TYPE_PHENOTYPE_KEY                       = "phenotype";
    public final static String TYPE_PROPERTY_KEY                        = "property";
    public final static String TYPE_COMMON_PROPERTY_KEY                 = "cproperty";
    public final static String TYPE_PHENOTYPE_PROPERTY_KEY              = "pproperty";
    public final static String TYPE_SAMPLE_GROUP_PROPERTY_KEY           = "dproperty";

}
