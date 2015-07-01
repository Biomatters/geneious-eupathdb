package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>TriTrypDatabase</code> represents TriTrypDB service {http://tritrypdb.org/} which
 * provides web services to search for genes in TriTrypDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class TriTrypDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in TriTrypDB. TriTrypDB is an integrated genomic and functional genomic database for pathogens of the family Trypanosomatidae, including organisms in both Leishmania and Trypanosoma genera.";
    private static final String DESCRIPTION = "Provides services to search for genes in TriTrypDB";
    private static final String NAME = "TriTrypDB";
    private static final String UNIQUE_ID = "TriTrypDB Service";
    private static final String PLUGIN_ICON = "tritrypdb16.png";
    private static final String WEB_SERVICE_URI = "http://tritrypdb.org/webservices/GeneQuestions";
    private static final String DB_URL = "http://tritrypdb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Crithidia fasciculata strain Cf-Cl,Leishmania aethiopica L147,Leishmania arabica strain LEM1108,Leishmania braziliensis MHOM/BR/75/M2903,Leishmania braziliensis MHOM/BR/75/M2904,Leishmania donovani BPK282A1,Leishmania enriettii strain LEM3045,Leishmania gerbilli strain LEM452,Leishmania infantum JPCM5,Leishmania major strain Friedlin,Leishmania mexicana MHOM/GT/2001/U1103,Leishmania panamensis MHOM/COL/81/L13,Leishmania sp. MAR LEM2494,Leishmania tarentolae Parrot-TarII,Leishmania tropica L590,Leishmania turanica strain LEM423,Trypanosoma brucei Lister strain 427,Trypanosoma brucei TREU927,Trypanosoma brucei gambiense DAL972,Trypanosoma congolense IL3000,Trypanosoma cruzi CL Brener Esmeraldo-like,Trypanosoma cruzi CL Brener Non-Esmeraldo-like,Trypanosoma cruzi Dm28c,Trypanosoma cruzi Sylvio X10/1,Trypanosoma cruzi marinkellei strain B7,Trypanosoma cruzi strain CL Brener,Trypanosoma evansi strain STIB 805,Trypanosoma grayi ANR4,Trypanosoma rangeli SC58,Trypanosoma vivax Y486";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Phenotype,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"Tb"};

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
