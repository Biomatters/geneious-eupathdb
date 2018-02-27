package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The Class <code>MicrosporidiaDatabase</code> represents MicrosporidiaDB service {https://microsporidiadb.org/} which
 * provides web services to search for genes in MicrosporidiaDB.
 *
 * @author cybage
 */
public class MicrosporidiaDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in MicrosporidiaDB. MicrosporidiaDB belongs to the EuPathDB family of databases and is an integrated genomic and functional genomic database for the phylum Microsporidia.";
    private static final String DESCRIPTION = "Provides services to search for genes in MicrosporidiaDB";
    private static final String NAME = "MicrosporidiaDB";
    private static final String UNIQUE_ID = "MicrosporidiaDB Service";
    private static final String PLUGIN_ICON = "microsporidiadb16.png";
    private static final String WEB_SERVICE_URI = "https://microsporidiadb.org/webservices/GeneQuestions";
    private static final String DB_URL = "https://microsporidiadb.org/micro/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
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
