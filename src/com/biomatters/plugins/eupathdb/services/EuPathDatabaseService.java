package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;

/**
 * The Class <code>EuPathDatabaseService</code> provides documents in response to a search
 * query executed on the genome database services.
 *
 * @author cybage
 */
public class EuPathDatabaseService extends DatabaseService {
    private EukaryoticDatabase eukaryoticDatabase;

    public EuPathDatabaseService(EukaryoticDatabase eukaryoticDatabase) {
        this.eukaryoticDatabase = eukaryoticDatabase;
    }

    /**
     * Get the EukaryoticDatabase
     *
     * @return - EukaryoticDatabase
     */
    public EukaryoticDatabase getEukaryoticDatabase() {
        return eukaryoticDatabase;
    }

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return eukaryoticDatabase.getUniqueID();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return eukaryoticDatabase.getName();
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return eukaryoticDatabase.getDescription();
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return eukaryoticDatabase.getHelp();
    }

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    @Override
    public Icons getIcons() {
        return eukaryoticDatabase.getIcons();
    }

    /**
     * Gets the search fields.
     *
     * @return the search fields
     */
    @Override
    public QueryField[] getSearchFields() {
        return new QueryField[0];
    }

    /**
     * Search for data from EuPathDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        eukaryoticDatabase.search(paramQuery, callback, paramArrayOfURN, getChildServices());
    }
    public static final String KEY_LOCUS_TAGS_ONLY ="LocusTagsOnly";

    @Override
    public ExtendedSearchOption[] getExtendedSearchOptions(boolean isAdvancedSearch) {
        ExtendedSearchOption[] searchOptions = super.getExtendedSearchOptions(isAdvancedSearch);
        if (isAdvancedSearch)
            return searchOptions;
        ExtendedSearchOption[] results = new ExtendedSearchOption[searchOptions.length+1];
        System.arraycopy(searchOptions,0,results,0,searchOptions.length);
        CheckboxSearchOption isGeneIdOnly = new CheckboxSearchOption(KEY_LOCUS_TAGS_ONLY, "Gene IDs Only", false);
        results[searchOptions.length] = isGeneIdOnly;
        isGeneIdOnly.setDescription("<html>Select checkbox if the query string is a list of gene IDs separated by commas, semicolons, or whitespace.<br/>If not selected, search all text fields, using * for wildcard and quotation marks to match an exact phrase</html>");
        return results;
    }

    public static boolean isLocusTagsOnly(Query query) {
        final Object value =  query.getExtendedOptionValue(KEY_LOCUS_TAGS_ONLY);
        if (value != null) {
            return (Boolean) value;
        }
        return false; // If not present use most general interpretation of query string
    }

}