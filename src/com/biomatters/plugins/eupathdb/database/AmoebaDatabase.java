package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>AmoebaDatabase</code> represents AmoebaDB service {http://amoebadb.org/} which
 * provides web services to search for genes in AmoebaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class AmoebaDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://amoebadb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://amoebadb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Acanthamoeba castellanii str. Neff,Entamoeba dispar SAW760,Entamoeba histolytica HM-1:IMSS,Entamoeba histolytica HM-1:IMSS-A,Entamoeba histolytica HM-1:IMSS-B,Entamoeba histolytica HM-3:IMSS,Entamoeba histolytica KU27,Entamoeba invadens IP1,Entamoeba moshkovskii Laredo,Entamoeba nuttalli P19,Naegleria fowleri ATCC 30863";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"EDI", "EIN", "EHI"};

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
