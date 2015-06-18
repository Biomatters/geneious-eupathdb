package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;
import com.biomatters.plugins.eupathdb.database.ToxoDatabase;

/**
 * The Class <code>ToxoDB</code> provides documents in response to a
 * search query executed on the genome database ToxoDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class ToxoDB extends DatabaseService {

    private static final String TOXODB_DB_HELP = "Provides services to search for genes in ToxoDB. ToxoDB is a genome database for the genus Toxoplasma, a set of single-celled eukaryotic pathogens that cause human and animal diseases, including toxoplasmosis.";
    private static final String TOXODB_DB_DESCRIPTION = "Provides services to search for genes in ToxoDB";
    private static final String TOXODB_DB = "ToxoDB Service";
    private static final String TOXODB_DB_PLUGIN = "ToxoDB Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return TOXODB_DB_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return TOXODB_DB;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return TOXODB_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return TOXODB_DB_HELP;
    }

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    @Override
    public Icons getIcons() {
        return IconUtilities.getIcons(EukaryoticDatabase.PLUGIN_ICON);
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
     * Search for data from ToxoDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        ToxoDatabase toxoDatabase = new ToxoDatabase();
        toxoDatabase.search(paramQuery, callback);
    }
}
