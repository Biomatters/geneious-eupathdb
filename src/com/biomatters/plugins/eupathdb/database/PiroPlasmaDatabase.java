package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>PiroPlasmaDatabase</code> represents PiroPlasmaDB service {http://piroplasmadb.org/} which
 * provides web services to search for genes in PiroPlasmaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PiroPlasmaDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://piroplasmadb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://piroplasmadb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Babesia bigemina strain BOND,Babesia bovis T2Bo,Babesia microti strain RI,Cytauxzoon felis strain Winnie,Theileria annulata strain Ankara,Theileria equi strain WA,Theileria orientalis strain Shintoku,Theileria parva strain Muguga";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"BB", "TA", "TP"};

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
