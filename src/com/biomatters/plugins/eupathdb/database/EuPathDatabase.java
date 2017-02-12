package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>EuPathDatabase</code> represents EuPathDB service {http://eupathdb.org/} which
 * provides web services to search for genes in EuPathDB.
 *
 * @author cybage
 */
public class EuPathDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in EuPathDB. EuPathDB (formerly ApiDB) is an integrated database covering the eukaryotic pathogens in the genera Acanthamoeba, Annacaliia, Babesia, Crithidia, Cryptosporidium, Edhazardia, Eimeria, Encephalitozoon, Endotrypanum, Entamoeba, Enterocytozoon, Giardia, Gregarina, Hamiltosporidium, Leishmania, Nematocida, Neospora, Nosema, EuPathdium, Theileria, Toxoplasma, Trichomonas, Trypanosoma and Vavraia, Vittaforma.";
    private static final String DESCRIPTION = "Provides services to search for genes in EuPathDB";
    private static final String NAME = "EuPathDB";
    private static final String DB_URL = "http://eupathdb.org/eupathdb/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Amoebozoa, Acanthamoeba, Acanthamoeba castellanii str. Neff, Entamoeba, Entamoeba dispar, Entamoeba dispar SAW760, Entamoeba histolytica, Entamoeba histolytica HM-1:IMSS, Entamoeba histolytica HM-1:IMSS-A, Entamoeba histolytica HM-1:IMSS-B, Entamoeba histolytica HM-3:IMSS, Entamoeba histolytica KU27, Entamoeba invadens, Entamoeba invadens IP1, Entamoeba moshkovskii, Entamoeba moshkovskii Laredo, Entamoeba nuttalli, Entamoeba nuttalli P19, Naegleria, Naegleria fowleri ATCC 30863, Apicomplexa, Babesia, Babesia bigemina, Babesia bigemina strain BOND, Babesia bovis, Babesia bovis T2Bo, Babesia microti, Babesia microti strain RI, Cryptosporidium, Cryptosporidium hominis, Cryptosporidium hominis TU502, Cryptosporidium muris, Cryptosporidium muris RN66, Cryptosporidium parvum, Cryptosporidium parvum Iowa II, Cytauxzoon, Cytauxzoon felis strain Winnie, Eimeria, Eimeria acervulina, Eimeria acervulina Houghton, Eimeria brunetti, Eimeria brunetti Houghton, Eimeria falciformis, Eimeria falciformis Bayer Haberkorn 1970, Eimeria maxima, Eimeria maxima Weybridge, Eimeria mitis, Eimeria mitis Houghton, Eimeria necatrix, Eimeria necatrix Houghton, Eimeria praecox, Eimeria praecox Houghton, Eimeria tenella, Eimeria tenella strain Houghton, Gregarina, Gregarina niphandrodes Unknown strain, Hammondia, Hammondia hammondi strain H.H.34, Neospora, Neospora caninum Liverpool, Plasmodium, Plasmodium berghei";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias, EC descriptions, Gene ID, Gene notes, Gene product, GO terms and definitions, Metabolic pathway names and descriptions, Phenotype, Protein domain names and descriptions, PubMed, Rodent Malaria Phenotype, Similar proteins (BLAST hits v. NRDB/PDB), User comments";
    private static final String WEB_SERVICE_DS_GENE_IDS_PARSER_PARAM = "ds_gene_ids_parser";
    private static final String WEB_SERVICE_DS_GENE_IDS_PARSER_PARAM_VALUE = "list";
    private static final String WEB_SERVICE_WDK_USER_SIGNATURE_PARAM = "wdk_user_signature";
    private static final String WEB_SERVICE_WDK_USER_SIGNATURE_PARAM_VALUE = "none";
    private static final String WEB_SERVICE_URI = "http://eupathdb.org/eupathdb/webservices/GeneQuestions";
    private static final String[] TAGS = {"PF", "MAL", "PV", "PY",
            "PB", "PC", "PK", "EDI", "EIN", "EHI", "cgd", "GL", "ECU", "Eint",
            "EBI", "BB", "TA", "TP", "TGME", "NCLIV", "TVAG", "Tb"};

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return NAME;
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
        return null;
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
