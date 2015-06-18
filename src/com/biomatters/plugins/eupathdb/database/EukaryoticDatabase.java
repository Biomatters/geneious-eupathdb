package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.plugins.eupathdb.utils.ResponseMessageBodyReader;
import com.biomatters.plugins.eupathdb.utils.SequenceDocumentGenerator;
import com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import com.biomatters.plugins.eupathdb.webservices.models.Response;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>EukaryoticDatabase</code> is an abstract class represents EuPathDB databases which
 * provides web services to search for genes.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public abstract class EukaryoticDatabase {
    /* Icon for all plugin*/
    public static final String PLUGIN_ICON = "database32.png";

    /* Web service parameters */
    private static final String WILDCARD = "*";
    private static final String WEB_SERVICE_O_FIELDS_PARAM = "o-fields";
    private static final String WEB_SERVICE_MAX_P_VALUE_PARAM = "max_pvalue";
    private static final String WEB_SERVICE_TEXT_FIELDS_PARAM = "text_fields";
    private static final String WEB_SERVICE_DS_GENE_IDS_PARAM = "ds_gene_ids_data";
    private static final String WEB_SERVICE_TEXT_EXPRESSION_PARAM = "text_expression";
    private static final String WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM = "text_search_organism";
    private static final String WEB_SERVICE_O_FIELDS_PARAM_VALUE = "primary_key,organism,product,cds,protein_sequence";
    private static final String WEB_SERVICE_MAX_P_VALUE_PARAM_VALUE = "-30";

    private static final String PATH_XML_GENE_BY_LOCUS_TAG = "GeneByLocusTag.xml";
    private static final String PATH_XML_GENES_BY_TEXT_SEARCH = "GenesByTextSearch.xml";
    private static final String PATH_XML_GENES_BY_TAXON = "GenesByTaxon.xml";
    private static final String PATH_XML_GENES_WITH_USER_COMMENTS = "GenesWithUserComments.xml";
    private static final String PATH_XML_GENES_WITH_UPDATED_ANNOTATIONS = "GenesWithUpdatedAnnotation.xml";
    private static final String PATH_XML_GENES_BY_MR4_REAGENTS = "GenesByMr4Reagents.xml";

    /**
     * Abstract method to define DB specific service end point.
     *
     * @return WEB_SERVICE_URI the String
     */
    protected abstract String getEndPointURI();

    /**
     * Abstract method to define DB specific tags used to identify if search text contains gene ID.
     *
     * @return TAGS the List<String>
     */
    protected abstract List<String> getTags();

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
    protected abstract String getWebServiceTextSearchOrganismParamValue();

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

    // all unused buildURI* methods are tend to be used with advanced query implementation.
    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    URI buildURIForGenesByTaxon() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_BY_TAXON).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    URI buildURIForGenesWithUserComments() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_WITH_USER_COMMENTS).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    URI buildURIForGenesWithUpdatedAnnotation() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_WITH_UPDATED_ANNOTATIONS).build();
    }

    /**
     * Builds the text search uri.
     *
     * @return uri the URI
     */
    URI buildURIForGenesByMr4Reagents() {
        return UriBuilder.fromUri(getEndPointURI()).path(PATH_XML_GENES_BY_MR4_REAGENTS).build();
    }


    /**
     * Processes search in the EuPathDB family of databases.
     *
     * @param paramQuery            the param query
     * @param paramRetrieveCallback the param retrieve callback
     */
    public void search(Query paramQuery, RetrieveCallback paramRetrieveCallback) throws DatabaseServiceException {
        String queryText = ((BasicSearchQuery) paramQuery).getSearchText();
        Map<String, String> paramMap = isQueryTextContainsTag(queryText)
                ? getParametersMapForSearchByTag(queryText)
                : getParametersMapForSearchByText(queryText);

        URI uri = buildURI(queryText);
        EuPathDBWebService service = new EuPathDBWebService();
        com.biomatters.plugins.eupathdb.webservices.models.Response wsResponse;
        try {
            wsResponse = service.post(uri, paramMap,
                    new ResponseMessageBodyReader()).readEntity(com.biomatters.plugins.eupathdb.webservices.models.Response.class);
        } catch (ProcessingException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        }

        DatabaseServiceException exception = getException(wsResponse);
        if (exception != null) {
            throw exception;
        }
        reportSearchResult(paramRetrieveCallback, wsResponse);
    }

    /**
     * Prepares search result from each record and report back to the caller
     * using RetrieveCallback.
     *
     * @param callback the RetrieveCallback
     * @param response the Response
     */
    private void reportSearchResult(RetrieveCallback callback, Response response) {
        if (!(response == null || response.getRecordset() == null || response
                .getRecordset().getRecord() == null)) {
            DefaultSequenceDocument document;
            List<Record> records = response.getRecordset().getRecord();
            for (Record record : records) {
                for (SequenceDocument.Alphabet alphabet : SequenceDocument.Alphabet
                        .values()) {
                    if (response.getRecordset().getType().equalsIgnoreCase("sequence") && alphabet.equals(SequenceDocument.Alphabet.NUCLEOTIDE)) {
                        continue;
                    }
                    document = SequenceDocumentGenerator
                            .getDefaultSequenceDocument(record,
                                    getDBUrl(), alphabet);
                    if (document != null) {
                        callback.add(document,
                                Collections.<String, Object>emptyMap());
                    }
                }
            }
        }
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
        StringBuilder errorMsg = new StringBuilder(error.getMsg());

        if (!(type == null || type.isEmpty())) {
            errorMsg.append("<br><b>Type: </b>").append(type);
        }
        if (!(code == null || code.isEmpty())) {
            errorMsg.append("<br><b>Code: </b>").append(code);
        }
        return errorMsg.toString();
    }

    /**
     * Checks if the query text contains the tag.
     *
     * @param queryText the query text
     * @return true, if is query text contains tag
     */
    boolean isQueryTextContainsTag(String queryText) {
        for (String tag : getTags()) {
            if (queryText.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds URI from query text and end point decided based on query search type.
     *
     * @param queryText the queryText
     * @return uri the URI
     */
    URI buildURI(String queryText) {
        return isQueryTextContainsTag(queryText) ? buildURIForGenesByLocusTag()
                : buildURIForGenesByTextSearch();
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param queryText the query text
     * @return paramMap the Map
     */
    Map<String, String> getParametersMapForSearchByTag(String queryText) {
        Map<String, String> paramMap = new HashMap<String, String>(2);
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
    Map<String, String> getParametersMapForSearchByText(String queryText) {
        Map<String, String> paramMap = new HashMap<String, String>(5);
        paramMap.put(WEB_SERVICE_O_FIELDS_PARAM,
                WEB_SERVICE_O_FIELDS_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM,
                getWebServiceTextSearchOrganismParamValue());
        paramMap.put(WEB_SERVICE_TEXT_FIELDS_PARAM,
                getWebServiceTextFieldsParamValue());
        paramMap.put(WEB_SERVICE_MAX_P_VALUE_PARAM,
                WEB_SERVICE_MAX_P_VALUE_PARAM_VALUE);
        paramMap.put(WEB_SERVICE_TEXT_EXPRESSION_PARAM,
                queryText + WILDCARD);
        return paramMap;
    }
}
