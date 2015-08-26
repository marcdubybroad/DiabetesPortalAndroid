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
    public final static String JSON_ID_KEY              = "id";
    public final static String JSON_SEARCHABLE_KEY      = "searchable";
    public final static String JSON_SORT_ORDER_KEY      = "sort_order";
    public final static String JSON_VARIANTS_KEY        = "variants";
    public final static String JSON_VARIANT_ID_KEY      = "VAR_ID";
    public final static String JSON_NUMBER_RECORDS_KEY  = "numRecords";
    public final static String JSON_ERROR_KEY           = "is_error";
    public final static String JSON_PASSBACK_KEY        = "passback";

    // json mapping array key values
    public final static String JSON_DATASETS_KEY        = "sample_groups";
    public final static String JSON_PROPERTIES_KEY      = "properties";
    public final static String JSON_PHENOTYPES_KEY      = "phenotypes";

    // constant types for metadata object tree
    public final static String TYPE_METADATA_ROOT_KEY                   = "metadata_root";
    public final static String TYPE_EXPERIMENT_KEY                      = "experiment";
    public final static String TYPE_SAMPLE_GROUP_KEY                    = "sample_group";
    public final static String TYPE_PHENOTYPE_KEY                       = "phenotype";
    public final static String TYPE_PROPERTY_KEY                        = "property";
    public final static String TYPE_COMMON_PROPERTY_KEY                 = "cproperty";
    public final static String TYPE_PHENOTYPE_PROPERTY_KEY              = "pproperty";
    public final static String TYPE_SAMPLE_GROUP_PROPERTY_KEY           = "dproperty";

    // constant string for the experiment technology type
    public final static String TECHNOLOGY_GWAS_KEY                      = "GWAS";

    // burden test json keys
    public final static String JSON_BURDEN_VARIANTS_KEY                 = "variants";
    public final static String JSON_BURDEN_COVARIATES_KEY               = "covariates";
    public final static String JSON_BURDEN_FILTERS_KEY                  = "filters";


    // constants for getData calls
    public final static String OPERATOR_LESS_THAN_EQUALS                 = "LTE";
    public final static String OPERATOR_LESS_THAN_NOT_EQUALS             = "LT";
    public final static String OPERATOR_MORE_THAN_EQUALS                 = "GTE";
    public final static String OPERATOR_MORE_THAN_NOT_EQUALS             = "GT";
    public final static String OPERATOR_EQUALS                           = "EQ";

    // constants for the version
    public final static String DATASET_VERSION_2_KEY                    = "mdv2";


}
