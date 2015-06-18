package com.biomatters.plugins.eupathdb.database;

import java.util.Arrays;
import java.util.List;

/**
 * The Class <code>FungiDatabase</code> represents FungiDB service {http://fungidb.org/} which
 * provides web services to search for genes in FungiDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class FungiDatabase extends EukaryoticDatabase {
    private static final String WEB_SERVICE_URI = "http://fungidb.org/webservices/GeneQuestions";
    private static final String DBURL = "http://fungidb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Ajellomyces capsulatus G186AR,Ajellomyces capsulatus NAm1,Albugo candida 2VRR,Albugo laibachii Nc14,Allomyces macrogynus ATCC 38327,Aphanomyces astaci APO3,Aphanomyces invadans NJM9701,Aspergillus aculeatus ATCC 16872,Aspergillus carbonarius ITEM 5010,Aspergillus clavatus NRRL 1,Aspergillus flavus NRRL3357,Aspergillus fumigatus Af293,Aspergillus nidulans FGSC A4,Aspergillus niger ATCC 1015,Aspergillus niger CBS 513.88,Aspergillus terreus NIH2624,Batrachochytrium dendrobatidis JEL423,Botryotinia fuckeliana B05.10,Candida albicans SC5314,Candida glabrata CBS 138,Coccidioides immitis H538.4,Coccidioides immitis RS,Coccidioides posadasii C735 delta SOWgp,Coprinopsis cinerea okayama7#130,Cryptococcus gattii R265,Cryptococcus gattii WM276,Cryptococcus neoformans var. grubii H99,Cryptococcus neoformans var. neoformans B-3501A,Cryptococcus neoformans var. neoformans JEC21,Fusarium graminearum PH-1,Fusarium oxysporum f. sp. lycopersici 4287,Fusarium verticillioides 7600,Hyaloperonospora arabidopsidis Emoy2,Magnaporthe oryzae 70-15,Malassezia globosa CBS 7966,Melampsora larici-populina 98AG31,Mucor circinelloides f. lusitanicus CBS 277.49,Neosartorya fischeri NRRL 181,Neurospora crassa OR74A,Neurospora discreta FGSC 8579,Neurospora tetrasperma FGSC 2508,Phanerochaete chrysosporium RP-78,Phycomyces blakesleeanus NRRL 1555(-),Phytophthora ramorum,Phytophthora sojae,Phytophthora capsici LT1534,Phytophthora cinnamomi var. cinnamomi,Phytophthora infestans T30-4,Phytophthora parasitica INRA-310,Pneumocystis jirovecii SE8,Puccinia graminis f. sp. tritici CRL 75-36-700-3,Pythium aphanidermatum DAOM BR444,Pythium arrhenomanes ATCC 12531,Pythium irregulare DAOM BR486,Pythium iwayamai DAOM BR242034,Pythium ultimum DAOM BR144,Pythium ultimum var. sporangiiferum BR650,Pythium vexans DAOM BR484,Rhizopus delemar RA 99-880,Saccharomyces cerevisiae S288c,Saprolegnia diclina VS20,Saprolegnia parasitica CBS 223.65,Schizosaccharomyces japonicus yFS275,Schizosaccharomyces octosporus yFS286,Schizosaccharomyces pombe 972h-,Sclerotinia sclerotiorum 1980 UF-70,Sordaria macrospora k-hell,Spizellomyces punctatus DAOM BR117,Sporisorium reilianum SRZ2,Talaromyces marneffei ATCC 18224,Talaromyces stipitatus ATCC 10500,Tremella mesenterica DSM 1558,Trichoderma reesei QM6a,Ustilago maydis 521,Yarrowia lipolytica CLIB122";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Protein domain names and descriptions,User comments";
    private static final String[] TAGS = {"NC", "Ndi"};

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
