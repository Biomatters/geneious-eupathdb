package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>GiardiaDatabase</code> represents GiardiaDB service {http://giardiadb.org/} which
 * provides web services to search for genes in GiardiaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class GiardiaDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://giardiadb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://giardiadb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Giardia Assemblage A isolate WB,Giardia Assemblage A2 isolate DH,Giardia Assemblage B isolate GS,Giardia Assemblage B isolate GS_B,Giardia Assemblage E isolate P15,Spironucleus salmonicida ATCC50377";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,Cellular localization,Community annotation,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"GL"};

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

    /**
     * Overridden method to define DB specific value for text_search_organism parameter.
     *
     * @return WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE the String
     */
    @Override
    public String getWebServiceTextSearchOrganismParamValue() {
        return WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE;
    }

    /**
     * Overridden method to define DB specific value for text_fields parameter.
     *
     * @return WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE the String
     */
    @Override
    public String getWebServiceTextFieldsParamValue() {
        return WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE;
    }

}
