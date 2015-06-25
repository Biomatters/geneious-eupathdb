package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>EuPathDatabase</code> represents EuPathDB service {http://eupathdb.org/} which
 * provides web services to search for genes in EuPathDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class EuPathDatabase extends EukaryoticDatabase {
    private static final String HELP = "Provides services to search for genes in EuPathDB. EuPathDB (formerly ApiDB) is an integrated database covering the eukaryotic pathogens in the genera Acanthamoeba, Annacaliia, Babesia, Crithidia, Cryptosporidium, Edhazardia, Eimeria, Encephalitozoon, Endotrypanum, Entamoeba, Enterocytozoon, Giardia, Gregarina, Hamiltosporidium, Leishmania, Nematocida, Neospora, Nosema, EuPathdium, Theileria, Toxoplasma, Trichomonas, Trypanosoma and Vavraia, Vittaforma.";
    private static final String DESCRIPTION = "Provides services to search for genes in EuPathDB";
    private static final String NUCLEOTIDE_OR_PROTEIN32_ICON = "nucleotideOrProtein32.png";
    private static final String NAME = "EuPathDB Plugin";
    private static final String DB_URL = "http://eupathdb.org";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE = "Acanthamoeba castellanii str. Neff,Ajellomyces capsulatus G186AR,Ajellomyces capsulatus NAm1,Albugo candida 2VRR,Albugo laibachii Nc14,Allomyces macrogynus ATCC 38327,Anncaliia algerae PRA109,Anncaliia algerae PRA339,Aphanomyces astaci APO3,Aphanomyces invadans NJM9701,Aspergillus aculeatus ATCC 16872,Aspergillus carbonarius ITEM 5010,Aspergillus clavatus NRRL 1,Aspergillus flavus NRRL3357,Aspergillus fumigatus Af293,Aspergillus nidulans FGSC A4,Aspergillus niger ATCC 1015,Aspergillus niger CBS 513.88,Aspergillus terreus NIH2624,Babesia bigemina strain BOND,Babesia bovis T2Bo,Babesia microti strain RI,Batrachochytrium dendrobatidis JEL423,Botryotinia fuckeliana B05.10,Candida albicans SC5314,Candida glabrata CBS 138,Chromera velia CCMP2878,Coccidioides immitis H538.4,Coccidioides immitis RS,Coccidioides posadasii C735 delta SOWgp,Coprinopsis cinerea okayama7#130,Crithidia fasciculata strain Cf-Cl,Cryptococcus gattii R265,Cryptococcus gattii WM276,Cryptococcus neoformans var. grubii H99,Cryptococcus neoformans var. neoformans B-3501A,Cryptococcus neoformans var. neoformans JEC21,Cryptosporidium hominis TU502,Cryptosporidium muris RN66,Cryptosporidium parvum Iowa II,Cytauxzoon felis strain Winnie,Edhazardia aedis USNM 41457,Eimeria acervulina Houghton,Eimeria brunetti Houghton,Eimeria falciformis Bayer Haberkorn 1970,Eimeria maxima Weybridge,Eimeria mitis Houghton,Eimeria necatrix Houghton,Eimeria praecox Houghton,Eimeria tenella strain Houghton,Encephalitozoon cuniculi EC1,Encephalitozoon cuniculi EC2,Encephalitozoon cuniculi EC3,Encephalitozoon cuniculi GB-M1,Encephalitozoon hellem ATCC 50504,Encephalitozoon hellem Swiss,Encephalitozoon intestinalis ATCC 50506,Encephalitozoon romaleae SJ-2008,Entamoeba dispar SAW760,Entamoeba histolytica HM-1:IMSS,Entamoeba histolytica HM-1:IMSS-A,Entamoeba histolytica HM-1:IMSS-B,Entamoeba histolytica HM-3:IMSS,Entamoeba histolytica KU27,Entamoeba invadens IP1,Entamoeba moshkovskii Laredo,Entamoeba nuttalli P19,Enterocytozoon bieneusi H348,Fusarium graminearum PH-1,Fusarium oxysporum f. sp. lycopersici 4287,Fusarium verticillioides 7600,Giardia Assemblage A isolate WB,Giardia Assemblage A2 isolate DH,Giardia Assemblage B isolate GS,Giardia Assemblage B isolate GS_B,Giardia Assemblage E isolate P15,Gregarina niphandrodes Unknown strain,Hammondia hammondi strain H.H.34,Hyaloperonospora arabidopsidis Emoy2,Leishmania aethiopica L147,Leishmania arabica strain LEM1108,Leishmania braziliensis MHOM/BR/75/M2903,Leishmania braziliensis MHOM/BR/75/M2904,Leishmania donovani BPK282A1,Leishmania enriettii strain LEM3045,Leishmania gerbilli strain LEM452,Leishmania infantum JPCM5,Leishmania major strain Friedlin,Leishmania mexicana MHOM/GT/2001/U1103,Leishmania panamensis MHOM/COL/81/L13,Leishmania sp. MAR LEM2494,Leishmania tarentolae Parrot-TarII,Leishmania tropica L590,Leishmania turanica strain LEM423,Magnaporthe oryzae 70-15,Malassezia globosa CBS 7966,Melampsora larici-populina 98AG31,Mitosporidium daphniae UGP3,Mucor circinelloides f. lusitanicus CBS 277.49,Naegleria fowleri ATCC 30863,Nematocida parisii ERTm1,Nematocida parisii ERTm3,Nematocida sp. 1 ERTm2,Nematocida sp. 1 ERTm6,Neosartorya fischeri NRRL 181,Neospora caninum Liverpool,Neurospora crassa OR74A,Neurospora discreta FGSC 8579,Neurospora tetrasperma FGSC 2508,Nosema bombycis CQ1,Nosema ceranae BRL01,Ordospora colligata OC4,Phanerochaete chrysosporium RP-78,Phycomyces blakesleeanus NRRL 1555(-),Phytophthora ramorum,Phytophthora sojae,Phytophthora capsici LT1534,Phytophthora cinnamomi var. cinnamomi,Phytophthora infestans T30-4,Phytophthora parasitica INRA-310,Plasmodium berghei ANKA,Plasmodium chabaudi chabaudi,Plasmodium cynomolgi strain B,Plasmodium falciparum 3D7,Plasmodium falciparum IT,Plasmodium gallinaceum 8A,Plasmodium knowlesi strain H,Plasmodium reichenowi CDC,Plasmodium vivax Sal-1,Plasmodium yoelii yoelii 17X,Plasmodium yoelii yoelii 17XNL,Plasmodium yoelii yoelii YM,Pneumocystis jirovecii SE8,Puccinia graminis f. sp. tritici CRL 75-36-700-3,Pythium aphanidermatum DAOM BR444,Pythium arrhenomanes ATCC 12531,Pythium irregulare DAOM BR486,Pythium iwayamai DAOM BR242034,Pythium ultimum DAOM BR144,Pythium ultimum var. sporangiiferum BR650,Pythium vexans DAOM BR484,Rhizopus delemar RA 99-880,Saccharomyces cerevisiae S288c,Saprolegnia diclina VS20,Saprolegnia parasitica CBS 223.65,Schizosaccharomyces japonicus yFS275,Schizosaccharomyces octosporus yFS286,Schizosaccharomyces pombe 972h-,Sclerotinia sclerotiorum 1980 UF-70,Sordaria macrospora k-hell,Spironucleus salmonicida ATCC50377,Spizellomyces punctatus DAOM BR117,Sporisorium reilianum SRZ2,Spraguea lophii 42_110,Talaromyces marneffei ATCC 18224,Talaromyces stipitatus ATCC 10500,Theileria annulata strain Ankara,Theileria equi strain WA,Theileria orientalis strain Shintoku,Theileria parva strain Muguga,Toxoplasma gondii GT1,Toxoplasma gondii ME49,Toxoplasma gondii RH,Toxoplasma gondii VEG,Trachipleistophora hominis Unknown strain,Tremella mesenterica DSM 1558,Trichoderma reesei QM6a,Trichomonas vaginalis G3,Trypanosoma brucei Lister strain 427,Trypanosoma brucei TREU927,Trypanosoma brucei gambiense DAL972,Trypanosoma congolense IL3000,Trypanosoma cruzi CL Brener Esmeraldo-like,Trypanosoma cruzi CL Brener Non-Esmeraldo-like,Trypanosoma cruzi Dm28c,Trypanosoma cruzi Sylvio X10/1,Trypanosoma cruzi marinkellei strain B7,Trypanosoma cruzi strain CL Brener,Trypanosoma evansi strain STIB 805,Trypanosoma grayi ANR4,Trypanosoma rangeli SC58,Trypanosoma vivax Y486,Ustilago maydis 521,Vavraia culicis floridensis,Vitrella brassicaformis CCMP3155,Vittaforma corneae ATCC 50505,Yarrowia lipolytica CLIB122";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE = "Alias,EC descriptions,Gene ID,Gene notes,Gene product,GO terms and definitions,Metabolic pathway names and descriptions,Phenotype,Protein domain names and descriptions,Rodent Malaria Phenotype,User comments";
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

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    @Override
    public Icons getIcons() {
        return IconUtilities.getIcons(NUCLEOTIDE_OR_PROTEIN32_ICON);
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
