package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.components.Dialogs;
import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.geneious.publicapi.plugin.GeneiousService;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.EuPathDBPlugin;
import com.biomatters.plugins.eupathdb.services.EuPathDatabaseService;
import com.biomatters.plugins.eupathdb.utils.ApplicationMessageBodyReader;
import com.biomatters.plugins.eupathdb.utils.ResponseMessageBodyReader;
import com.biomatters.plugins.eupathdb.utils.SequenceDocumentGenerator;
import com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import com.biomatters.plugins.eupathdb.webservices.models.Response;
import com.biomatters.plugins.eupathdb.webservices.models.wadl.Application;
import com.biomatters.plugins.eupathdb.webservices.models.wadl.Method;
import com.biomatters.plugins.eupathdb.webservices.models.wadl.Param;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/**
 * The Class <code>EukaryoticDatabase</code> is an abstract class represents EuPathDB databases which
 * provides web services to search for genes.
 *
 * @author cybage
 */
public abstract class EukaryoticDatabase {

    /* Web service parameters */
    private static final String WEB_SERVICE_O_FIELDS_PARAM = "o-fields";
    private static final String WEB_SERVICE_MAX_P_VALUE_PARAM = "max_pvalue";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM = "text_fields";
    private static final String WEB_SERVICE_DS_GENE_IDS_PARAM = "ds_gene_ids_data";
    private static final String WEB_SERVICE_TEXT_EXPRESSION_PARAM = "text_expression";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM = "text_search_organism";
    private static final String WEB_SERVICE_O_FIELDS_PARAM_VALUE = "primary_key,organism,gene_product,cds";
    private static final String WEB_SERVICE_O_FIELDS_PARAM_VALUE_FOR_ID = "primary_key";
    private static final String WEB_SERVICE_MAX_P_VALUE_PARAM_VALUE = "-30";

    private static final String PATH_XML_GENE_BY_LOCUS_TAG = "GeneByLocusTag.xml";
    private static final String PATH_XML_GENES_BY_TEXT_SEARCH = "GenesByTextSearch.xml";
    private static final String PATH_WADL_GENES_BY_TEXT_SEARCH = "GenesByTextSearch.wadl";
    private static final String PATH_XML_GENES_BY_TAXON = "GenesByTaxon.xml";
    private static final String PATH_XML_GENES_WITH_USER_COMMENTS = "GenesWithUserComments.xml";
    private static final String PATH_XML_GENES_WITH_UPDATED_ANNOTATIONS = "GenesWithUpdatedAnnotation.xml";
    private static final String PATH_XML_GENES_BY_MR4_REAGENTS = "GenesByMr4Reagents.xml";

    private static final int BATCH_SIZE = 250;
    private static final String NO_RESULTS_FROM_GENE_ID_LIST = "No results found. Consider disabling Gene IDs Only to search all text fields";
    private static final String NO_RESULTS_BAD_GENE_ID_LIST = "No results found. The query string is not in the correct format for a list of gene IDs";
    private static final String NO_RESULTS_FROM_FREE_TEXT = "No results found. Consider using * to search partial words. For example CO*I matches COI and COXI";
    private static final String INFO_MESSAGE = "EuPathDB does not support searching for a single wildcard '*'. Please use a more specific search term.";
    private static final String MISSING_RESULT_MESSAGE = "Failed to download all matching sequences. The following sequences are missing from the search results:";
    // gene id format: begins with two letters, followed by string of alphanumeric or '_'
    private static final Pattern GENE_ID_PATTERN = Pattern.compile("\\s*(?:(?:[a-zA-Z]{1,8}[|])?[a-zA-Z]{2}\\w+\\s*[,; ]\\s*)*(?:[a-zA-Z]{1,8}[|])?[a-zA-Z]{2}\\w+\\s*");

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    public abstract String getUniqueID();

    /**
     * Gets the name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets the description.
     *
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Gets the help.
     *
     * @return the help
     */
    public abstract String getHelp();

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    public Icons getIcons() {
        String iconName = getIconName();
        if(iconName == null) {
            return IconUtilities.getIcons("nucleotideOrProtein32.png");
        }
        return IconUtilities.getIconsFromJar(EuPathDBPlugin.class, iconName);
    }

    /**
     *
     * @return The name of the icon image file.  The file must be in the classpath.
     */
    protected abstract String getIconName();

    /**
     * Abstract method to define DB specific service end point.
     *
     * @return WEB_SERVICE_URI the String
     */
    protected abstract String getEndPointURI();

    /**
     * Abstract method to define DB URL.
     *
     * @return DBURL the String
     */
    protected abstract String getDBUrl();

    /**
     * Abstract method to define DB specific value for text_search_organism parameter.
     *
     * @return WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE the String
     */
    public abstract String getWebServiceTextSearchOrganismParamValue() throws DatabaseServiceException;

    /**
     * This should only be used by unit tests - exposes the raw reference normally only used internally
     *
     * @return the actual reference used to store organism psrsm values downloaded from the web
     */
    protected abstract AtomicReference<String> getWebServiceTextSearchOrganismParamReference();

    /**
     * define DB specific value for text_search_organism parameter.
     *
     * @return the String list of all organism names for text search on the database
     */
    protected String getWebServiceTextSearchOrganismParamValue(AtomicReference<String> organismDefaults) throws DatabaseServiceException {
        if (organismDefaults.get() == null) { // this would not be needed if we could use Java 8 lambdas in the next part
            organismDefaults.compareAndSet(null, getOrganismDefaultsFromWadl());
        }
        return organismDefaults.get();
    }

    /**
     * Abstract method to define DB specific value for text_fields parameter.
     *
     * @return WEB_SERVICE_TEXT_FIELDS_PARAM_VALUE the String
     */
    protected abstract String getWebServiceTextFieldsParamValue();

    /**
     * Builds the tag search uri.
     *
     * @return uri the URI
     */
    URI buildURIForGenesByLocusTag() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENE_BY_LOCUS_TAG).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    URI buildURIForGenesByTextSearch() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_BY_TEXT_SEARCH).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    private URI buildURIForGenesByTextSearchWadl() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_WADL_GENES_BY_TEXT_SEARCH).build();
    }

    // all unused buildURI* methods are intended to be used with a future advanced query implementation.

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    @SuppressWarnings("unused")
    URI buildURIForGenesByTaxon() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_BY_TAXON).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    @SuppressWarnings("unused")
    URI buildURIForGenesWithUserComments() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_WITH_USER_COMMENTS).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    @SuppressWarnings("unused")
    URI buildURIForGenesWithUpdatedAnnotation() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_WITH_UPDATED_ANNOTATIONS).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    @SuppressWarnings("unused")
    URI buildURIForGenesByMr4Reagents() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_BY_MR4_REAGENTS).build();
    }


    /**
     * Processes search in the EuPathDB family of databases.
     *
     * @param paramQuery            the param query
     * @param paramRetrieveCallback the param retrieve callback
     * @param paramArrayOfURN       URN array
     * @param childServiceList      child Services
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException
     */
    public void search(Query paramQuery, RetrieveCallback paramRetrieveCallback, URN[] paramArrayOfURN, List<GeneiousService> childServiceList) throws DatabaseServiceException {
        String queryText = ((BasicSearchQuery) paramQuery).getSearchText();
        List<Record> allMissingRecordIDs = new ArrayList<>();
        if (!(queryText == null || (queryText = queryText.trim()).isEmpty())) {
            if(queryText.equals("*")) {
                Dialogs.showMessageDialog(INFO_MESSAGE, "EuPath Database Info", null, Dialogs.DialogIcon.INFORMATION);
                paramRetrieveCallback.setFinalStatus("\"" + INFO_MESSAGE + "\"", false);
                return;
            }
            //get URN element from paramArrayOfURN which is used when Agent is re-run.
            List<String> urnElementList = new ArrayList<>();
            if (paramArrayOfURN.length > 0) {
                for (URN urn : paramArrayOfURN) {
                    urnElementList.add(urn.element);
                }
            }
            boolean isLocusTagSearch = isQueryForLocusTagSearch(paramQuery);
            Map<String, String> paramMap = isLocusTagSearch
                    ? getParametersMapForSearchByTag(queryText)
                    : getParametersMapForSearchByText(queryText);

            //Override the O_FIELDS value of the parameterMap to get only ID at first request.
            paramMap.put(WEB_SERVICE_O_FIELDS_PARAM, WEB_SERVICE_O_FIELDS_PARAM_VALUE_FOR_ID);
            URI uri = isLocusTagSearch
                    ? buildURIForGenesByLocusTag()
                    : buildURIForGenesByTextSearch();
            EuPathDBWebService service = new EuPathDBWebService();
            Response response = getResponseFromWebService(paramMap, uri, service);

            List<Record> records = response.getRecordset().getRecord();
            if (!(records == null || records.isEmpty())) {
                int documentCount = 0;
                int uptoDocument = 0;
                int totalDocument = records.size();
                if(!paramRetrieveCallback.isCanceled()) {
                    paramRetrieveCallback.setMessage("Downloading " + totalDocument + " matching sequence");
                }
                while (!paramRetrieveCallback.isCanceled() && uptoDocument < totalDocument) {
                    uptoDocument = documentCount + BATCH_SIZE;
                    if (uptoDocument > totalDocument) {
                        uptoDocument = totalDocument;
                    }
                    List<Record> missingRecordId = executeInBatch(records, documentCount, uptoDocument, totalDocument, paramRetrieveCallback, service, urnElementList, childServiceList);
                    allMissingRecordIDs.addAll(missingRecordId);
                    documentCount = uptoDocument;
                }
                if(!(paramRetrieveCallback.isCanceled() || allMissingRecordIDs.isEmpty())) {
                    String missingIDList = getRecordIdsInString(allMissingRecordIDs);
                    Dialogs.showMessageDialog(MISSING_RESULT_MESSAGE + "\n" + missingIDList, "Search Results Missing in " + getName(), null, Dialogs.DialogIcon.INFORMATION);
                }
            } else {
                String notFoundMessage = NO_RESULTS_FROM_FREE_TEXT;
                if (isLocusTagSearch) {
                    if (isQueryForLocusTagSearch(queryText)) {
                        notFoundMessage = NO_RESULTS_FROM_GENE_ID_LIST;
                    } else {
                        notFoundMessage = NO_RESULTS_BAD_GENE_ID_LIST;
                    }
                }
                paramRetrieveCallback.setFinalStatus(notFoundMessage, false);
            }
        }
    }

    /**
     * Retrive the x id in batch and execute the search.
     *
     * @param records               - whole records.
     * @param documentCount         - count of document
     * @param uptoCount             - used to retrive record upto the count.
     * @param totalCount            - total count of record
     * @param paramRetrieveCallback - RetrieveCallback
     * @param service               - EuPathDBWebService
     * @param urnElementList        - URN element list
     * @param childServiceList      - child Services
     * @return - Missing Records which is expected to be retrieved from response but didn't retrieved.
     * @throws DatabaseServiceException
     */
    private List<Record> executeInBatch(List<Record> records, int documentCount, int uptoCount, int totalCount, RetrieveCallback paramRetrieveCallback, EuPathDBWebService service, List<String> urnElementList, List<GeneiousService> childServiceList) throws DatabaseServiceException {
        List<Record> misMatchRecord = new ArrayList<>();
        List<Record> recordInBatch = records.subList(documentCount, uptoCount);
        String recordIds = retrieveIDsInCSVString(recordInBatch, urnElementList);
        if (!recordIds.isEmpty()) {
            Map<String, String> parameterMap = getParametersMapForSearchByTag(recordIds);
            Response response = getResponseFromWebService(parameterMap, buildURIForGenesByLocusTag(), service);
            misMatchRecord = reportSearchResult(recordInBatch, paramRetrieveCallback, response, documentCount, totalCount, childServiceList);
        }
        return misMatchRecord;
    }

    /**
     * Get all the Id in string format from the list separated by delimiter ','
     *
     * @param recordInBatch  - Record containing ID
     * @param urnElementList - URN element list
     * @return - ALL Id separated by comma.
     */
    protected String retrieveIDsInCSVString(List<Record> recordInBatch, List<String> urnElementList) {
        StringBuilder idList = new StringBuilder();
        String delimiter = ",";
        for (Record record : recordInBatch) {
            String id = record.getId();
            if (!urnElementList.contains(id)) {
                //To get Id for OrthoMCL Database as its contain id followed by pipe '|'.
                if (id.contains("|")) {
                    String[] splitID = id.trim().split("\\|");
                    if (splitID.length > 1) {
                        id = splitID[1];
                    }
                }
                if (idList.indexOf(id) == -1) {
                    idList.append(id);
                    if (!record.equals(recordInBatch.get(recordInBatch.size() - 1))) {
                        idList.append(delimiter);
                    }
                }
            }
        }
        return idList.toString();
    }

    /**
     * Prepares search result from each record and report back to the caller
     * using RetrieveCallback.
     *
     * @param recordInBatch    - Batch records
     * @param callback         - the RetrieveCallback
     * @param response         - the Response
     * @param documentCount    - count of document
     * @param totalDocuments   - total count of record
     * @param childServiceList - child Services
     * @return - Missing Records which is expected to be retrieved from response but didn't retrieved.
     */
    private List<Record> reportSearchResult(List<Record> recordInBatch, RetrieveCallback callback, Response response, int documentCount, int totalDocuments, List<GeneiousService> childServiceList) throws DatabaseServiceException {
        List<Record> missingRecords = new ArrayList<>();
        if (!(response == null || response.getRecordset() == null || response
                .getRecordset().getRecord() == null)) {
            DefaultSequenceDocument document;
            SequenceDocument.Alphabet alphabet = SequenceDocument.Alphabet.NUCLEOTIDE;
            if (response.getRecordset().getType().equalsIgnoreCase("sequence")) {
                alphabet = SequenceDocument.Alphabet.PROTEIN;
            }

            List<Record> records = response.getRecordset().getRecord();
            if (recordInBatch.size() > records.size()) {
                missingRecords.addAll(recordInBatch);
                missingRecords.removeAll(records);
            }
            for (Record record : records) {
                if (callback.isCanceled()) {
                    break;
                }
                if (recordInBatch.contains(record)) {
                    documentCount++;
                    double progress = (double) documentCount / totalDocuments;
                    callback.setProgress(progress);
                    callback.setMessage("Downloading sequence " + documentCount + " of " + totalDocuments);

                    document = SequenceDocumentGenerator
                            .getDefaultSequenceDocument(record,
                                    getDBUrl(), alphabet, getName(), childServiceList);
                    if (document != null) {
                        callback.add(document,
                                Collections.emptyMap());

                    }
                }
            }
        }
        return missingRecords;
    }

    private String getOrganismDefaultsFromWadl() throws DatabaseServiceException {
        URI uri = buildURIForGenesByTextSearchWadl();
        EuPathDBWebService service = new EuPathDBWebService();
        Application application = getApplicationFromWadl(uri, service);

        List<Object> methodOrResource = application.getResources().get(0).getResource().get(0).getMethodOrResource();
        for (Object mOrR:methodOrResource) {
            if (mOrR instanceof Method) {
                for (Param p:((Method)mOrR).getRequest().getParam()) {
                    if (p.getName().equals("text_search_organism")) {
                        return p.getDefault();
                    }
                }
            }
         }
        throw new DatabaseServiceException("Could not retrieve organism list from webservice", false);
    }

    private Application getApplicationFromWadl(URI wadlUri, EuPathDBWebService service) throws DatabaseServiceException {
        Application application;
        try {
            application =  service.get(wadlUri, new ApplicationMessageBodyReader()).readEntity(Application.class);
        } catch (ProcessingException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        }
        return application;
    }

    /**
     * Get the response of the service.
     *
     * @param paramMap - parameter value map
     * @param uri      - URI
     * @param service  - EuPathDBWebService
     * @return {@link com.biomatters.plugins.eupathdb.webservices.models.Response}
     * @throws DatabaseServiceException
     */
    private Response getResponseFromWebService(Map<String, String> paramMap, URI uri, EuPathDBWebService service) throws DatabaseServiceException {
        Response response;
        try {
            response = service.post(uri, paramMap,
                    new ResponseMessageBodyReader()).readEntity(Response.class);
        } catch (ProcessingException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        }
        DatabaseServiceException databaseServiceException = getException(response);
        if (databaseServiceException != null) {
            throw databaseServiceException;
        }
        return response;
    }

    /**
     * Generates DatabaseServiceException if there is error in response from web
     * service.
     *
     * @param response the Response
     * @return DatabaseServiceException
     */
    private DatabaseServiceException getException(Response response) {
        DatabaseServiceException exception = null;
        if (!(response == null || response.getError() == null)) {
            String errorMsg = getErrorMessage(response.getError());
            exception = new DatabaseServiceException(errorMsg, false);
        }
        return exception;
    }

    /**
     * Get the error message string.
     *
     * @param error the Error
     * @return String
     */
    private String getErrorMessage(com.biomatters.plugins.eupathdb.webservices.models.Error error) {
        String type = error.getType();
        String code = error.getCode();
        StringBuilder errorMsg = new StringBuilder("The EuPathDB server responded with an error message:\n\n");

        if (!(type == null || type.isEmpty())) {
            errorMsg.append(type).append(" ");
        }
        if (!(code == null || code.isEmpty())) {
            errorMsg.append("(#").append(code).append("): ");
        }
        errorMsg.append(error.getMsg());
        return errorMsg.toString();
    }

    private boolean isQueryForLocusTagSearch(Query paramQuery) {
       return EuPathDatabaseService.isLocusTagsOnly(paramQuery);
    }

    /**
     * Checks if the query text is proper format for locus tag search.
     * Search contains one or more exact gene IDs (locus tags) separated by comma, semicolon, or whitespace
     *
     * @param queryText the query text
     * @return true, if query text is in correct format for a locus tag search
     */
    private boolean isQueryForLocusTagSearch(String queryText) {
        return GENE_ID_PATTERN.matcher(queryText).matches();
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param queryText the query text
     * @return paramMap the Map
     */
    Map<String, String> getParametersMapForSearchByTag(String queryText) {
        Map<String, String> paramMap = new HashMap<>(2);
        paramMap.put(WEB_SERVICE_O_FIELDS_PARAM,
                WEB_SERVICE_O_FIELDS_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_DS_GENE_IDS_PARAM, queryText);
        return paramMap;
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param queryText the query text
     * @return paramMap the Map
     */
    Map<String, String> getParametersMapForSearchByText(String queryText) throws DatabaseServiceException {
        Map<String, String> paramMap = new HashMap<>(5);
        paramMap.put(WEB_SERVICE_O_FIELDS_PARAM,
                WEB_SERVICE_O_FIELDS_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM,
                getWebServiceTextSearchOrganismParamValue());
        paramMap.put(WEB_SERVICE_TEXT_FIELDS_PARAM,
                getWebServiceTextFieldsParamValue());
        paramMap.put(WEB_SERVICE_MAX_P_VALUE_PARAM,
                WEB_SERVICE_MAX_P_VALUE_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_TEXT_EXPRESSION_PARAM, queryText);
        return paramMap;
    }

    /**
     * Append all record Id in String format.
     *
     * @param records - Record-list
     * @return - All Record-Id in string format.
     */
    private String getRecordIdsInString(List<Record> records) {
        StringBuilder ids = new StringBuilder();
        if(!(records == null || records.isEmpty())) {
            int size = records.size();
            ids.append("\n");
            for (int i = 0; i < size; i++) {
                ids.append(records.get(i).getId());
                if (i != size - 1) {
                    ids.append("\n");
                }
            }
        }
        return ids.toString();
    }
}
