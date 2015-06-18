package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>ToxoDatabase</code> represents ToxoDB service {http://toxodb.org/} which
 * provides web services to search for genes in ToxoDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class ToxoDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://toxodb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://toxodb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Eimeria acervulina Houghton,Eimeria brunetti Houghton,Eimeria falciformis Bayer Haberkorn 1970,Eimeria maxima Weybridge,Eimeria mitis Houghton,Eimeria necatrix Houghton,Eimeria praecox Houghton,Eimeria tenella strain Houghton,Hammondia hammondi strain H.H.34,Neospora caninum Liverpool,Toxoplasma gondii GT1,Toxoplasma gondii ME49,Toxoplasma gondii RH,Toxoplasma gondii VEG";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,Community annotation,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"TGME", "NCLIV"};

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
