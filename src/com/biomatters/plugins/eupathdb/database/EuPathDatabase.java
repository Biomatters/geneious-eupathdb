package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The Class <code>EuPathDatabase</code> represents EuPathDB service {https://eupathdb.org/} which
 * provides web services to search for genes in EuPathDB.
 *
 * @author cybage
 */
public class EuPathDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in EuPathDB. EuPathDB (formerly ApiDB) is an integrated database covering the eukaryotic pathogens in the genera Acanthamoeba, Annacaliia, Babesia, Crithidia, Cryptosporidium, Edhazardia, Eimeria, Encephalitozoon, Endotrypanum, Entamoeba, Enterocytozoon, Giardia, Gregarina, Hamiltosporidium, Leishmania, Nematocida, Neospora, Nosema, EuPathdium, Theileria, Toxoplasma, Trichomonas, Trypanosoma and Vavraia, Vittaforma.";
    private static final String DESCRIPTION = "Provides services to search for genes in EuPathDB";
    private static final String NAME = "EuPathDB";
    private static final String UNIQUE_ID = "EuPathDB Service";
    private static final String PLUGIN_ICON = "databaseSearch16.png";
    private static final String DB_URL = "https://eupathdb.org/eupathdb/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias, EC descriptions, Gene ID, Gene notes, Gene product, GO terms and definitions, Metabolic pathway names and descriptions, Phenotype, Protein domain names and descriptions, PubMed, Rodent Malaria Phenotype, Similar proteins (BLAST hits v. NRDB/PDB), User comments";
    private static final String WEB_SERVICE_DS_GENE_IDS_PARSER_PARAM = "ds_gene_ids_parser";
    private static final String WEB_SERVICE_DS_GENE_IDS_PARSER_PARAM_VALUE = "list";
    private static final String WEB_SERVICE_WDK_USER_SIGNATURE_PARAM = "wdk_user_signature";
    private static final String WEB_SERVICE_WDK_USER_SIGNATURE_PARAM_VALUE = "none";
    private static final String WEB_SERVICE_URI = "https://eupathdb.org/eupathdb/webservices/GeneQuestions";
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
    protected String getIconName() {
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

    /**
     * @param queryText the query text
     * @return paramMap the Map
     */
    @Override
    Map<String, String> getParametersMapForSearchByTag(String queryText) {
        Map<String, String> paramMap = super.getParametersMapForSearchByTag(queryText);
        paramMap.put(WEB_SERVICE_DS_GENE_IDS_PARSER_PARAM, WEB_SERVICE_DS_GENE_IDS_PARSER_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_WDK_USER_SIGNATURE_PARAM, WEB_SERVICE_WDK_USER_SIGNATURE_PARAM_VALUE);
        return paramMap;
    }
}
