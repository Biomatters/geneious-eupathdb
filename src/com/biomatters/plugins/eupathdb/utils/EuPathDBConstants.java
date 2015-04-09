package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.plugins.eupathdb.EuPathDBGenes.EuPathDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class <code>EuPathDBConstants</code> contains a list of constants for
 * EuPathDB service.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public final class EuPathDBConstants {

    /* Defines DB specific array of tags */
    public static final Map<String, String[]> TAGS = new HashMap<String, String[]>();
    /* Key to retrieve DB specific Web service url from resources */
    public static final String WEB_SERVICE_URI = ".WEB_SERVICE_URI";
    /* DB specific url used to set as a qualifier */
    public static final String DBURL = ".DBURL";
    /* Web service url path for genes search by and format */
    public static final String WEB_SERVICE_PATH_JSON_GENE_BY_LOCUS_TAG = "GeneByLocusTag.json";
    public static final String WEB_SERVICE_PATH_JSON_GENES_BY_TEXT_SEARCH = "GenesByTextSearch.json";
    public static final String WEB_SERVICE_PATH_XML_GENE_BY_LOCUS_TAG = "GeneByLocusTag.xml";
    public static final String WEB_SERVICE_PATH_XML_GENES_BY_TEXT_SEARCH = "GenesByTextSearch.xml";
    /* Web service parameters */
    public static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM = "text_search_organism";
    public static final String WEB_SERVICE_O_FILEDS_PARAM = "o-fields";
    public static final String WEB_SERVICE_DS_GENE_IDS_PARAM = "ds_gene_ids_data";
    public static final String WEB_SERVICE_TEXT_EXPRESSION_PARAM = "text_expression";
    public static final String WEB_SERVICE_TEXT_FIELDS_PARAM = "text_fields";
    public static final String WEB_SERVICE_MAX_PVALUE_PARAM = "max_pvalue";
    /* Web service parameter values */
    public static final String WEB_SERVICE_OFILEDS_PARAM_VALUE = "primary_key,organism,product,cds,protein_sequence";
    public static final String WEB_SERVICE_TEXT_FILEDS_PARAM_VALUE = "Gene product";
    public static final String WEB_SERVICE_MAX_PVALUE_PARAM_VALUE = "-30";
    public static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE_KEY = ".WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE";
    public static final String EUPATHDB_PROPERTIES = "eupathdb.properties";
    /* Plugin specific constants */
    public static final String PLUGIN_NAME = "EuPathDB Plugin";
    public static final String PLUGIN_VERSION = "0.1";
    public static final String PLUGIN_DESCRIPTION = "Provides services to search for genes in various EuPathDB databases";
    public static final String PLUGIN_AUTHORS = "Biomatters Ltd, Charles Ma and Svenja Gunther";
    public static final String PLUGIN_MIN_API_VERSION = "4.715";
    public static final int PLUGIN_MAX_API_VERSION = 4;
    public static final String PLUGIN_ICON = "database32.png";
    private static final String[] PLASMODB_TAGS = {"PF", "MAL", "PV", "PY",
            "PB", "PC", "PK"};
    private static final String[] PIROPLASMADB_TAGS = {"BB", "TA", "TP"};

    /* Generates map of DB and tag array to allow easy searching and comparison */
    static {
        TAGS.put(EuPathDatabase.PLASMODB.name(), PLASMODB_TAGS);
        TAGS.put(EuPathDatabase.PIROPLASMADB.name(), PIROPLASMADB_TAGS);
    }


    /**
     * Constant provider utility.
     */
    private EuPathDBConstants() {
    }
}
