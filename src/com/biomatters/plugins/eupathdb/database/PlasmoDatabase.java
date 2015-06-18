package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>PlasmoDatabase</code> represents PlasmoDB service {http://plasmodb.org/} which
 * provides web services to search for genes in PlasmoDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PlasmoDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://plasmodb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://plasmodb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Plasmodium berghei ANKA,Plasmodium chabaudi chabaudi,Plasmodium cynomolgi strain B,Plasmodium falciparum 3D7,Plasmodium falciparum IT,Plasmodium gallinaceum 8A,Plasmodium knowlesi strain H,Plasmodium reichenowi CDC,Plasmodium vivax Sal-1,Plasmodium yoelii yoelii 17X,Plasmodium yoelii yoelii 17XNL,Plasmodium yoelii yoelii YM";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Genes of previous release,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,Rodent Malaria Phenotype,User comments";
    private static final String[] TAGS = {"PF", "MAL", "PV", "PY",
            "PB", "PC", "PK"};

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
