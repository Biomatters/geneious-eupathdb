package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.plugins.eupathdb.EuPathDBGenes.EuPathDatabase;
import com.biomatters.plugins.eupathdb.utils.SequenceDocumentGenerator.DocType;
import com.biomatters.plugins.eupathdb.webservices.WebServiceClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
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

        // will be fixed by CYB-21
        String queryText = ((BasicSearchQuery) paramQuery).getSearchText();
        queryText = PluginUtilities.deEscape(queryText);
        StringBuilder url;
        try {
            if (isQueryTextContainsTag(queryText, database)) {
                url = buildTagSearchUrl(database).append(
                        PluginUtilities.encode(queryText));
            } else {
                url = buildTextSearchUrl(database).append(
                        PluginUtilities.encode(queryText)).append(WILDCARD);
            }
        } catch (UnsupportedEncodingException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED+": "
                    + e.getMessage(), false);
        }

        String response = WebServiceClient.call(url.toString());
        List<Map<String, String>> list = JSONParser.parse(response);

        DefaultSequenceDocument document;
        if (!(list == null || list.isEmpty())) {
            Map<String, String> responseMap = list.get(0);
            if (responseMap.get(EuPathDBConstants.RESPONSE_KEY_ERROR) != null) {
                String type = responseMap.get(EuPathDBConstants.RESPONSE_KEY_ERROR_TYPE);
                String code = responseMap.get(EuPathDBConstants.RESPONSE_KEY_ERROR_CODE);
                StringBuilder errorMsg = new StringBuilder(
                        responseMap.get(EuPathDBConstants.RESPONSE_KEY_ERROR));

                if (type != null && !type.isEmpty()) {
                    errorMsg.append("<br><b>Type: </b>").append(type);
                }
                if (code != null && !code.isEmpty()) {
                    errorMsg.append("<br><b>Code: </b>").append(code);
                }
                throw new DatabaseServiceException(errorMsg.toString(), false);
            } else {
                for (Map<String, String> map : list) {
                    for (DocType docType : DocType.values()) {
                        document = SequenceDocumentGenerator
                                .getDefaultSequenceDocument(map,
                                        getDBUrl(database), docType);
                        if (document != null) {
                            paramRetrieveCallback.add(document,
                                    Collections.<String, Object>emptyMap());
                        }
                    }
                }
            }
        }
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
     * Builds the tag search url.
     *
     * @param database the database
     * @return the string builder
     * @throws DatabaseServiceException the database service exception
     */
    private StringBuilder buildTagSearchUrl(EuPathDatabase database)
            throws DatabaseServiceException {
        StringBuilder url = new StringBuilder(getPrefixUrl(database));
        url.append(EuPathDBConstants.WEB_SERVICE_SUFFIX_TAG_SEARCH_URL)
                .append('.')
                .append(EuPathDBConstants.WEB_SERVICE_RESPONSE_FORMAT)
                .append('?').append(getSuffixUrl())
                .append(EuPathDBConstants.WEB_SERVICE_PARAM_SEPARATOR)
                .append(EuPathDBConstants.WEB_SERVICE_DS_GENE_IDS_PARAM);

        return url;
    }

    /**
     * Builds the text search url.
     *
     * @param database the database
     * @return the string builder
     * @throws DatabaseServiceException the database service exception
     */
    private StringBuilder buildTextSearchUrl(EuPathDatabase database)
            throws DatabaseServiceException {
        StringBuilder url = new StringBuilder(getPrefixUrl(database));
        url.append(EuPathDBConstants.WEB_SERVICE_SUFFIX_TEXT_SEARCH_URL)
                .append('.')
                .append(EuPathDBConstants.WEB_SERVICE_RESPONSE_FORMAT)
                .append('?').append(getSuffixUrl())
                .append(EuPathDBConstants.WEB_SERVICE_PARAM_SEPARATOR)
                .append(addTextSearchParams(database))
                .append(EuPathDBConstants.WEB_SERVICE_PARAM_SEPARATOR)
                .append(EuPathDBConstants.WEB_SERVICE_TEXT_EXPRESSION_PARAM);

        return url;
    }

    /**
     * Gets the prefix url.
     *
     * @param database the database
     * @return the prefix url
     * @throws DatabaseServiceException the database service exception
     */
    private String getPrefixUrl(EuPathDatabase database)
            throws DatabaseServiceException {
        try {
            return PluginUtilities.getValue(database
                    + EuPathDBConstants.WEB_SERVICE_PREFIX_URL);
        } catch (IOException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED+": "
                    + e.getMessage(), false);
        }
    }

    /**
     * Gets the suffix url.
     *
     * @return the suffix url
     */
    private String getSuffixUrl() {
        return EuPathDBConstants.WEB_SERVICE_O_FILEDS_PARAM
                + EuPathDBConstants.WEB_SERVICE_OFILEDS_PARAM_VALUE;
    }

    /**
     * Adds the text search params.
     *
     * @param database the database
     * @return the string
     * @throws DatabaseServiceException the database service exception
     */
    private String addTextSearchParams(EuPathDatabase database)
            throws DatabaseServiceException {
        StringBuilder url = new StringBuilder();

        String species;
        String textFields;
        try {
            species = PluginUtilities.getValue(database
                    + EuPathDBConstants.WEB_SERVICE_SPECIES_PARAM_VALUE);
            textFields = PluginUtilities
                    .encode(EuPathDBConstants.WEB_SERVICE_TEXT_FILEDS_PARAM_VALUE);
            species = PluginUtilities.encode(species);
        } catch (UnsupportedEncodingException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED+": "
                    + e.getMessage(), false);
        } catch (IOException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED+": "
                    + e.getMessage(), false);
        }

        url.append(EuPathDBConstants.WEB_SERVICE_SPECIES_PARAM).append(species)
                .append(EuPathDBConstants.WEB_SERVICE_PARAM_SEPARATOR)
                .append(EuPathDBConstants.WEB_SERVICE_TEXT_FIELDS_PARAM)
                .append(textFields)
                .append(EuPathDBConstants.WEB_SERVICE_PARAM_SEPARATOR)
                .append(EuPathDBConstants.WEB_SERVICE_MAX_PVALUE_PARAM)
                .append(EuPathDBConstants.WEB_SERVICE_MAX_PVALUE_PARAM_VALUE);

        return url.toString();
    }

    /**
     * Gets the DB url.
     *
     * @param database the database
     * @return the DB url
     * @throws DatabaseServiceException the database service exception
     */
    private String getDBUrl(EuPathDatabase database)
            throws DatabaseServiceException {
        try {
            return PluginUtilities.getValue(database + EuPathDBConstants.DBURL);
        } catch (IOException e) {
            throw new DatabaseServiceException(e, SEARCH_FAILED+": "
                    + e.getMessage(), false);
        }
    }
}
