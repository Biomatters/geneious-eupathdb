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
    private static final String HELP = "Provides services to search for genes in AmoebaDB. AmoebaDB belongs to the EuPathDB family of databases and is an integrated genomic and functional genomic database for Entamoeba and Acanthamoeba parasites.";
    private static final String DESCRIPTION = "Provides services to search for genes in AmoebaDB";
    private static final String NAME = "AmoebaDB";
    private static final String UNIQUE_ID = "AmoebaDB Service";
    private static final String PLUGIN_ICON = "amoebadb16.png";
    private static final String WEB_SERVICE_URI = "http://amoebadb.org/webservices/GeneQuestions";
    private static final String DB_URL = "http://amoebadb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Acanthamoeba castellanii str. Neff,Entamoeba dispar SAW760,Entamoeba histolytica HM-1:IMSS,Entamoeba histolytica HM-1:IMSS-A,Entamoeba histolytica HM-1:IMSS-B,Entamoeba histolytica HM-3:IMSS,Entamoeba histolytica KU27,Entamoeba invadens IP1,Entamoeba moshkovskii Laredo,Entamoeba nuttalli P19,Naegleria fowleri ATCC 30863";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"EDI", "EIN", "EHI"};

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
