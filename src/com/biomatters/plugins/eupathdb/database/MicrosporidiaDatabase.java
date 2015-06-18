package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>MicrosporidiaDatabase</code> represents MicrosporidiaDB service {http://microsporidiadb.org/} which
 * provides web services to search for genes in MicrosporidiaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class MicrosporidiaDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://microsporidiadb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://microsporidiadb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Anncaliia algerae PRA109,Anncaliia algerae PRA339,Edhazardia aedis USNM 41457,Encephalitozoon cuniculi EC1,Encephalitozoon cuniculi EC2,Encephalitozoon cuniculi EC3,Encephalitozoon cuniculi GB-M1,Encephalitozoon hellem ATCC 50504,Encephalitozoon hellem Swiss,Encephalitozoon intestinalis ATCC 50506,Encephalitozoon romaleae SJ-2008,Enterocytozoon bieneusi H348,Mitosporidium daphniae UGP3,Nematocida parisii ERTm1,Nematocida parisii ERTm3,Nematocida sp. 1 ERTm2,Nematocida sp. 1 ERTm6,Nosema bombycis CQ1,Nosema ceranae BRL01,Ordospora colligata OC4,Spraguea lophii 42_110,Trachipleistophora hominis Unknown strain,Vavraia culicis floridensis,Vittaforma corneae ATCC 50505";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"ECU", "Eint", "EBI"};

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
