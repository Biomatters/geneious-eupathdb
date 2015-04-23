package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.plugins.eupathdb.EuPathDBGenes.EuPathDatabase;
import com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService;
import com.biomatters.plugins.eupathdb.webservices.models.Error;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import com.biomatters.plugins.eupathdb.webservices.models.Response;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>PluginHelper</code> handles the creation of URL along with
 * query string parameters needed to make a REST call.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PluginHelper {

    private static final String WILDCARD = "*";
    private static final String SEARCH_FAILED = "Search failed";

    /**
     * Processes search in the EuPathDB family of databases.
     *
     * @param paramQuery            the param query
     * @param paramRetrieveCallback the param retrieve callback
     * @param database              the database
     * @throws DatabaseServiceException the database service exception
     */
    public void processSearch(Query paramQuery,
                              RetrieveCallback paramRetrieveCallback, EuPathDatabase database)
            throws DatabaseServiceException {
        String queryText = ((BasicSearchQuery) paramQuery).getSearchText();
        Map<String, String> paramMap = isQueryTextContainsTag(queryText,
                database) ? getParametersMapForSearchByTag(queryText)
                : getParametersMapForSearchByText(database, queryText);

        URI uri = buildURI(database, queryText);
        EuPathDBWebService service = new EuPathDBWebService();
        Response wsResponse;
        try{
            wsResponse = service.post(uri, paramMap,
                    new ResponseMessageBodyReader()).readEntity(Response.class);
        } catch (ProcessingException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        } catch (IllegalStateException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        }

        DatabaseServiceException exception = getException(wsResponse);
        if (exception != null) {
            throw exception;
        }
        reportSearchResult(paramRetrieveCallback, wsResponse, database);
    }


    /**
     * Checks if the query text contains the tag.
     *
     * @param queryText the query text
     * @param database  the database
     * @return true, if is query text contains tag
     */
    private boolean isQueryTextContainsTag(String queryText,
                                           EuPathDatabase database) {
        String[] tags = EuPathDBConstants.TAGS.get(database.name());
        if (tags != null) {
            for (String tag : tags) {
                if (queryText.contains(tag)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prepares search result from each record and report back to the caller
     * using RetrieveCallback.
     *
     * @param callback the RetrieveCallback
     * @param response the Response
     * @param database the EuPathDatabase
     * @throws DatabaseServiceException
     */
    private void reportSearchResult(RetrieveCallback callback,
                                    Response response, EuPathDatabase database)
            throws DatabaseServiceException {
        if (!(response == null || response.getRecordset() == null || response
                .getRecordset().getRecord() == null)) {
            DefaultSequenceDocument document;
            List<Record> records = response.getRecordset().getRecord();
            for (Record record : records) {
                for (SequenceDocument.Alphabet alphabet : SequenceDocument.Alphabet
                        .values()) {
                    document = SequenceDocumentGenerator
                            .getDefaultSequenceDocument(record,
                                    getDBUrl(database), alphabet);
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
     * Builds URI from query text and end point decided based on query search type.
     *
     * @param database  the EuPathDatabase
     * @param queryText the queryText
     * @return uri the URI
     * @throws DatabaseServiceException
     */
    private URI buildURI(EuPathDatabase database,
                         String queryText) throws DatabaseServiceException {
        return isQueryTextContainsTag(queryText, database) ? buildTagSearchUri(database)
                : buildTextSearchUri(database);
    }

    /**
     * Builds the tag search uri.
     *
     * @param database the EuPathDatabase
     * @return uri the URI
     * @throws DatabaseServiceException
     */
    private URI buildTagSearchUri(EuPathDatabase database)
            throws DatabaseServiceException {
        UriBuilder uriBuilder = UriBuilder.fromUri(getEndPointUri(database));
        uriBuilder = uriBuilder
                .path(EuPathDBConstants.WEB_SERVICE_PATH_XML_GENE_BY_LOCUS_TAG);
        return uriBuilder.build();
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param queryText the query text
     * @return paramMap the Map
     */
    private Map<String, String> getParametersMapForSearchByTag(String queryText) {
        Map<String, String> paramMap = new HashMap<String, String>(2);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_O_FILEDS_PARAM,
                EuPathDBConstants.WEB_SERVICE_OFILEDS_PARAM_VALUE);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_DS_GENE_IDS_PARAM, queryText);
        return paramMap;
    }

    /**
     * Builds the text search uri.
     *
     * @param database the EuPathDatabase
     * @return uri the URI
     * @throws DatabaseServiceException
     */
    private URI buildTextSearchUri(EuPathDatabase database)
            throws DatabaseServiceException {
        URI baseURI = URI.create(getEndPointUri(database));
        UriBuilder uriBuilder = UriBuilder.fromUri(baseURI);
        uriBuilder = uriBuilder
                .path(EuPathDBConstants.WEB_SERVICE_PATH_XML_GENES_BY_TEXT_SEARCH);
        return uriBuilder.build();
    }

    /**
     * Generates a map containing parameter and value pairs for tag based search.
     *
     * @param database  the EuPathDatabase
     * @param queryText the query text
     * @return paramMap the Map
     * @throws DatabaseServiceException
     */
    private Map<String, String> getParametersMapForSearchByText(EuPathDatabase database, String queryText)
            throws DatabaseServiceException {
        String organism;
        try {
            organism = EuPathDBUtilities
                    .getValue(database
                            + EuPathDBConstants.WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM_VALUE_KEY);
        } catch (IOException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED + ": "
                    + e.getMessage(), false);
        }
        Map<String, String> paramMap = new HashMap<String, String>(5);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_O_FILEDS_PARAM,
                EuPathDBConstants.WEB_SERVICE_OFILEDS_PARAM_VALUE);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_TEXT_SEARCH_ORGANISM_PARAM,
                organism);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_TEXT_FIELDS_PARAM,
                EuPathDBConstants.WEB_SERVICE_TEXT_FILEDS_PARAM_VALUE);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_MAX_PVALUE_PARAM,
                EuPathDBConstants.WEB_SERVICE_MAX_PVALUE_PARAM_VALUE);
        paramMap.put(EuPathDBConstants.WEB_SERVICE_TEXT_EXPRESSION_PARAM,
                queryText + WILDCARD);
        return paramMap;
    }

    /**
     * Get an endpoint uri of the EuPathDB or its child DB.
     *
     * @param database the database
     * @return endpoint uri The String
     * @throws DatabaseServiceException the database service exception
     */
    private String getEndPointUri(EuPathDatabase database)
            throws DatabaseServiceException {
        try {
            return EuPathDBUtilities.getValue(database
                    + EuPathDBConstants.WEB_SERVICE_URI);
        } catch (IOException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED + ": "
                    + e.getMessage(), false);
        }
    }

    /**
     * Get EuPathDB ot its child DB URL.
     *
     * @param database the database
     * @return the DB url
     * @throws DatabaseServiceException the database service exception
     */
    private String getDBUrl(EuPathDatabase database)
            throws DatabaseServiceException {
        try {
            return EuPathDBUtilities.getValue(database
                    + EuPathDBConstants.DBURL);
        } catch (IOException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED + ": "
                    + e.getMessage(), false);
        }
    }

    /**
     * Get the error message string.
     *
     * @param error the Error
     * @return String
     */
    private String getErrorMessage(Error error) {
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
}
