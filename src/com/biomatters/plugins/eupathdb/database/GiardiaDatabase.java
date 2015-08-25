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
    private static final String HELP = "Provides services to search for genes in GiardiaDB. Giardia lamblia is a significant, environmentally transmitted, human pathogen and an amitochondriate protist.";
    private static final String DESCRIPTION = "Provides services to search for genes in GiardiaDB";
    private static final String NAME = "GiardiaDB";
    private static final String UNIQUE_ID = "GiardiaDB Service";
    private static final String PLUGIN_ICON = "giardiadb16.png";
    private static final String WEB_SERVICE_URI = "http://giardiadb.org/webservices/GeneQuestions";
    private static final String DB_URL = "http://giardiadb.org/giardiadb/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Giardia Assemblage A isolate WB,Giardia Assemblage A2 isolate DH,Giardia Assemblage B isolate GS,Giardia Assemblage B isolate GS_B,Giardia Assemblage E isolate P15,Spironucleus salmonicida ATCC50377";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,Cellular localization,Community annotation,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"GL"};

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return UNIQUE_ID;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return HELP;
    }

    @Override
    public String getIconName() {
        return PLUGIN_ICON;
    }

    /**
     * Overridden method to define DB specific service end point.
     *
     * @return WEB_SERVICE_URI the String
     */
    @Override
    protected String getEndPointURI() {
        return WEB_SERVICE_URI;
    }

    /**
     * DB specific tags used to identify if search text contains gene ID.
     *
     * @return TAGS the List<String>
     */
    protected List<String> getTags() {
        return Arrays.asList(TAGS);
    }

    /**
     * Overridden method to define DB URL.
     *
     * @return DB_URL the String
     */
    @Override
    protected String getDBUrl() {
        return DB_URL;
    }

    /**
     * Overridden method to define DB specific value for text_search_organism parameter.
     *
     * @return WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE the String
     */
    @Override
    protected String getWebServiceTextSearchOrganismParamValue() {
        return WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE;
    }

    /**
     * Overridden method to define DB specific value for text_fields parameter.
     *
     * @return WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE the String
     */
    @Override
    protected String getWebServiceTextFieldsParamValue() {
        return WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE;
    }

}
