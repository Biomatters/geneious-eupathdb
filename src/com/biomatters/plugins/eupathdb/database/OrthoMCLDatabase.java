package com.biomatters.plugins.eupathdb.database;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>OrthoMCLDatabase</code> represents OrthoMCLDB service {http://orthomcl.org/} which
 * provides web services to search for sequences in OrthoMCL.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class OrthoMCLDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://orthomcl.org/webservices/SequenceQuestions";
    private static final String DBURL = "http://orthomcl.org";
    private static final String PATH_XML_BY_ID_LIST = "ByIdList.xml";
    private static final String PATH_XML_BY_TEXT_SEARCH = "ByTextSearch.xml";

    private static final String WILDCARD = "*";
    private static final String WEB_SERVICE_WDK_RECORD_TYPE_PARAM = "wdk_record_type";
    private static final String WEB_SERVICE_TEXT_EXPRESSION_PARAM = "text_expression";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM = "text_fields";
    private static final String WEB_SERVICE_DETAIL_TABLE_PARAM = "detail_table";
    private static final String WEB_SERVICE_PRIMARY_KEY_COLUMN_PARAM = "primary_key_column";
    private static final String WEB_SERVICE_PROJECT_ID_PARAM = "project_id";
    private static final String WEB_SERVICE_O_FIELDS_PARAM = "o-fields";
    private static final String WEB_SERVICE_SOURCE_IDS_DATA_PARAM = "source_ids_data";
    private static final String WEB_SERVICE_SOURCE_IDS_PARSER_PARAM = "source_ids_parser";

    private static final String WEB_SERVICE_WDK_RECORD_TYPE_PARAM_VALUE = "sequence";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "product,taxon,PFam domains (names and descriptions),EC numbers (and descriptions),ortholog group ID (in current or previous OrthoMCL releases)";
    private static final String WEB_SERVICE_DETAIL_TABLE_PARAM_VALUE = "apidb.OrthomclSequenceDetail";
    private static final String WEB_SERVICE_PRIMARY_KEY_COLUMN_PARAM_VALUE = "full_id";
    private static final String WEB_SERVICE_PROJECT_ID_PARAM_VALUE = "OrthoMCL";
    private static final String WEB_SERVICE_O_FIELDS_PARAM_VALUE = "primary_key,product,sequence";
    private static final String WEB_SERVICE_SOURCE_IDS_PARSER_PARAM_VALUE = "list";
    private static final String[] TAGS = {"PF", "AAE", "NP", "Afu", "AGA", "XP", "ENS", "EBI", "AAL", "AAR", "ABL", "ACL", "AGO", "AGR", "ATG", "EHI", "EIN", "ENS", "GL5", "GLP"};

    /**
     * Overridden method to define DB specific service end point.
     *
     * @return WEB_SERVICE_URI the String
     */
    @Override
    public String getEndPointURI() {
        return WEB_SERVICE_URI;
    }

    /**
     * DB specific tags used to identify if search text contains gene ID.
     *
     * @return TAGS the List<String>
     */
    public List<String> getTags() {
        return Arrays.asList(TAGS);
    }

    /**
     * Overridden method to define DB URL.
     *
     * @return DBURL the String
     */
    @Override
    public String getDBUrl() {
        return DBURL;
    }

    @Override
    protected String getWebServiceTextSearchOrganismParamValue() {
        return null;
    }

    @Override
    protected String getWebServiceTextFieldsParamValue() {
        return null;
    }

    @Override
    URI buildURI(String queryText) {
        return super.isQueryTextContainsTag(queryText) ? buildURIForByIdList() : buildURIForByTextSearch();
    }

    /**
     * Builds uri for the search by ID list.
     *
     * @return uri the URI
     */
    URI buildURIForByIdList() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_BY_ID_LIST).build();
    }

    /**
     * Builds uri for the search by text.
     *
     * @return uri the URI
     */
    URI buildURIForByTextSearch() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_BY_TEXT_SEARCH).build();
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param queryText the query text
     * @return paramMap the Map
     */
    @Override
    Map<String, String> getParametersMapForSearchByTag(String queryText) {
        Map<String, String> paramMap = new HashMap<String, String>(3);
        paramMap.put(WEB_SERVICE_O_FIELDS_PARAM, WEB_SERVICE_O_FIELDS_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_SOURCE_IDS_PARSER_PARAM, WEB_SERVICE_SOURCE_IDS_PARSER_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_SOURCE_IDS_DATA_PARAM, queryText);
        return paramMap;
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param queryText the query text
     * @return paramMap the Map
     */
    @Override
    Map<String, String> getParametersMapForSearchByText(String queryText) {
        Map<String, String> paramMap = new HashMap<String, String>(7);
        paramMap.put(WEB_SERVICE_WDK_RECORD_TYPE_PARAM, WEB_SERVICE_WDK_RECORD_TYPE_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_TEXT_FIELDS_PARAM, WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_DETAIL_TABLE_PARAM, WEB_SERVICE_DETAIL_TABLE_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_PRIMARY_KEY_COLUMN_PARAM, WEB_SERVICE_PRIMARY_KEY_COLUMN_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_PROJECT_ID_PARAM, WEB_SERVICE_PROJECT_ID_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_O_FIELDS_PARAM, WEB_SERVICE_O_FIELDS_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_TEXT_EXPRESSION_PARAM, queryText + WILDCARD);
        return paramMap;
    }
}
