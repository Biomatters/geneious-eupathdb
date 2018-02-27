package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The Class <code>ToxoDatabase</code> represents ToxoDB service {http://toxodb.org/} which
 * provides web services to search for genes in ToxoDB.
 *
 * @author cybage
 */
public class ToxoDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in ToxoDB. ToxoDB is a genome database for the genus Toxoplasma, a set of single-celled eukaryotic pathogens that cause human and animal diseases, including toxoplasmosis.";
    private static final String DESCRIPTION = "Provides services to search for genes in ToxoDB";
    private static final String NAME = "ToxoDB";
    private static final String UNIQUE_ID = "ToxoDB Service";
    private static final String PLUGIN_ICON = "toxodb16.png";
    private static final String WEB_SERVICE_URI = "http://toxodb.org/webservices/GeneQuestions";
    private static final String DB_URL = "http://toxodb.org/toxo/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias, EC descriptions, Gene ID, Gene notes, Gene product, GO terms and definitions, Metabolic pathway names and descriptions, Protein domain names and descriptions, PubMed, Similar proteins (BLAST hits v. NRDB/PDB), User comments";
    private static AtomicReference<String> WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = new AtomicReference<>(null);

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
    public String getWebServiceTextSearchOrganismParamValue() throws DatabaseServiceException {
        return getWebServiceTextSearchOrganismParamValue(WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE);
    }

    /**
     * should only be used by unit tests
     * @return  the raw reference to WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE
     */
    @Override
    protected AtomicReference<String> getWebServiceTextSearchOrganismParamReference() {
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
