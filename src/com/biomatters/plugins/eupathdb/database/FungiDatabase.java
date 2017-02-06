package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>FungiDatabase</code> represents FungiDB service {http://fungidb.org/} which
 * provides web services to search for genes in FungiDB.
 *
 * @author cybage
 */
public class FungiDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in FungiDB. FungiDB belongs to the EuPathDB family of databases and is an integrated genomic and functional genomic database for the kingdom Fungi.";
    private static final String DESCRIPTION = "Provides services to search for genes in FungiDB";
    private static final String NAME = "FungiDB";
    private static final String UNIQUE_ID = "FungiDB Service";
    private static final String PLUGIN_ICON = "fungidb16.png";
    private static final String WEB_SERVICE_URI = "http://fungidb.org/webservices/GeneQuestions";
    private static final String DB_URL = "http://fungidb.org/fungidb/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Oomycetes, Albugo, Albugo candida, Albugo candida 2VRR, Albugo laibachii, Albugo laibachii Nc14, Aphanomyces, Aphanomyces astaci, Aphanomyces astaci strain APO3, Aphanomyces invadans, Aphanomyces invadans NJM9701, Hyaloperonospora, Hyaloperonospora arabidopsidis Emoy2, Phytophthora, Phytophthora capsici, Phytophthora capsici LT1534, Phytophthora cinnamomi, Phytophthora cinnamomi var. cinnamomi CBS 144.22, Phytophthora infestans, Phytophthora infestans T30-4, Phytophthora parasitica, Phytophthora parasitica INRA-310, Phytophthora ramorum, Phytophthora ramorum strain Pr102, Phytophthora sojae, Phytophthora sojae strain P6497, Pythium, Phytopythium vexans, Pythium vexans DAOM BR484, Pythium aphanidermatum, Pythium aphanidermatum DAOM BR444, Pythium arrhenomanes, Pythium arrhenomanes ATCC 12531, Pythium irregulare, Pythium irregulare DAOM BR486, Pythium iwayamai, Pythium iwayamai DAOM BR242034, Pythium ultimum, Pythium ultimum DAOM BR144, Pythium ultimum var. sporangiiferum BR650, Saprolegnia, Saprolegnia diclina, Saprolegnia diclina VS20, Saprolegnia parasitica, Saprolegnia parasitica CBS 223.65, Fungi, Agaricomycetes, Coprinopsis, Coprinopsis cinerea okayama7#130, Phanerochaete, Phanerochaete chrysosporium RP-78, Blastocladiomycetes, Allomyces macrogynus ATCC 38327, Chytridiomycetes, Batrachochytrium, Batrachochytrium dendrobatidis JEL423, Spizellomyces, Spizellomyces punctatus DAOM BR117, Eurotiomycetes, Aspergillus, Aspergillus aculeatus, Aspergillus aculeatus ATCC 16872, Aspergillus carbonarius, Aspergillus carbonarius ITEM 5010, Aspergillus clavatus, Aspergillus clavatus NRRL 1, Aspergillus fischeri, Aspergillus fischeri NRRL 181, Aspergillus flavus, Aspergillus flavus NRRL3357, Aspergillus fumigatus, Aspergillus fumigatus Af293, Aspergillus nidulans, Aspergillus nidulans FGSC A4, Aspergillus niger, Aspergillus niger ATCC 1015, Aspergillus niger CBS 513.88, Aspergillus oryzae, Aspergillus oryzae RIB40, Aspergillus terreus, Aspergillus terreus NIH2624, Coccidioides, Coccidioides immitis, Coccidioides immitis H538.4, Coccidioides immitis RS, Coccidioides posadasii, Coccidioides posadasii C735 delta SOWgp, Coccidioides posadasii RMSCC 3488, Coccidioides posadasii str. Silveira, Histoplasma, Histoplasma capsulatum G186AR, Histoplasma capsulatum NAm1, Talaromyces, Talaromyces marneffei, Talaromyces marneffei ATCC 18224, Talaromyces stipitatus, Talaromyces stipitatus ATCC 10500, Leotiomycetes, Botrytis, Botrytis cinerea B05.10, Sclerotinia, Sclerotinia sclerotiorum 1980 UF-70, Pneumocystidomycetes, Pneumocystis jirovecii SE8, Pucciniomycetes, Melampsora, Melampsora larici-populina 98AG31, Puccinia, Puccinia graminis f. sp. tritici CRL 75-36-700-3, Saccharomycetes, Candida, Candida albicans, Candida albicans SC5314, Candida albicans SC5314_B, Candida glabrata, Candida glabrata CBS 138, Saccharomyces, Saccharomyces cerevisiae S288c, Yarrowia, Yarrowia lipolytica CLIB122, Schizosaccharomycetes, Schizosaccharomyces japonicus, Schizosaccharomyces japonicus yFS275, Schizosaccharomyces octosporus, Schizosaccharomyces octosporus yFS286, Schizosaccharomyces pombe, Schizosaccharomyces pombe 972h-, Sordariomycetes, Fusarium, Fusarium graminearum, Fusarium graminearum PH-1, Fusarium oxysporum, Fusarium oxysporum f. sp. lycopersici 4287, Fusarium verticillioides, Fusarium verticillioides 7600, Magnaporthe, Magnaporthe oryzae 70-15, Neurospora, Neurospora crassa, Neurospora crassa OR74A, Neurospora discreta, Neurospora discreta FGSC 8579, Neurospora tetrasperma, Neurospora tetrasperma FGSC 2508, Sordaria, Sordaria macrospora k-hell, Trichoderma, Trichoderma reesei QM6a, Tremellomycetes, Cryptococcus, Cryptococcus deuterogattii, Cryptococcus deuterogattii R265, Cryptococcus gattii, Cryptococcus gattii WM276, Cryptococcus neoformans, Cryptococcus neoformans var. grubii H99, Cryptococcus neoformans var. neoformans B-3501A, Cryptococcus neoformans var. neoformans JEC21, Tremella, Tremella mesenterica DSM 1558, Ustilaginomycetes, Malassezia, Malassezia globosa CBS 7966, Sporisorium, Sporisorium reilianum SRZ2, Ustilago, Ustilago maydis 521, Zygomycetes, Mucor, Mucor circinelloides f. lusitanicus CBS 277.49, Phycomyces, Phycomyces blakesleeanus NRRL 1555(-), Rhizopus, Rhizopus delemar RA 99-880";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"NC", "Ndi"};

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
