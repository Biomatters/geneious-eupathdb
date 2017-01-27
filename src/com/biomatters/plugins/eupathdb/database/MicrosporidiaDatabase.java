package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>MicrosporidiaDatabase</code> represents MicrosporidiaDB service {http://microsporidiadb.org/} which
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
    private static final String WEB_SERVICE_URI = "http://microsporidiadb.org/webservices/GeneQuestions";
    private static final String DB_URL = "http://microsporidiadb.org/micro/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Anncaliia, Anncaliia algerae PRA109, Anncaliia algerae PRA339, Edhazardia, Edhazardia aedis USNM 41457, Encephalitozoon, Encephalitozoon cuniculi, Encephalitozoon cuniculi EC1, Encephalitozoon cuniculi EC2, Encephalitozoon cuniculi EC3, Encephalitozoon cuniculi GB-M1, Encephalitozoon hellem, Encephalitozoon hellem ATCC 50504, Encephalitozoon hellem Swiss, Encephalitozoon intestinalis, Encephalitozoon intestinalis ATCC 50506, Encephalitozoon romaleae, Encephalitozoon romaleae SJ-2008, Enterocytozoon, Enterocytozoon bieneusi H348, Mitosporidium, Mitosporidium daphniae UGP3, Nematocida, Nematocida parisii, Nematocida parisii ERTm1, Nematocida parisii ERTm3, Nematocida sp. 1, Nematocida sp. 1 ERTm2, Nematocida sp. 1 ERTm6, Nosema, Nosema bombycis, Nosema bombycis CQ1, Nosema ceranae, Nosema ceranae BRL01, Ordospora, Ordospora colligata OC4, Spraguea, Spraguea lophii 42_110, Trachipleistophora, Trachipleistophora hominis Unknown strain, Vavraia, Vavraia culicis subsp. floridensis, Vittaforma, Vittaforma corneae ATCC 50505";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias, EC descriptions, Gene ID, Gene notes, Gene product, GO terms and definitions, Metabolic pathway names and descriptions, Protein domain names and descriptions, PubMed, Similar proteins (BLAST hits v. NRDB/PDB), User comments";
    private static final String[] TAGS = {"ECU", "Eint", "EBI"};

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
    public String getWebServiceTextSearchOrganismParamValue() {
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
