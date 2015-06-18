package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;
import com.biomatters.plugins.eupathdb.database.TrichDatabase;

/**
 * The Class <code>TrichDB</code> provides documents in response to a
 * search query executed on the genome database TrichDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class TrichDB extends DatabaseService {

    private static final String TRICHDB_DB_HELP = "Provides services to search for genes in TrichDB. The Trichomonas vaginalis genome project was undertaken at The Institute for Genomic Research (TIGR) directed by Jane Carlton in collaboration with Patricia Johnson and the Trichomonas research community.";
    private static final String TRICHDB_DB_DESCRIPTION = "Provides services to search for genes in TrichDB";
    private static final String TRICHDB_DB = "TrichDB Service";
    private static final String TRICHDB_DB_PLUGIN = "TrichDB Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return TRICHDB_DB_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return TRICHDB_DB;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return TRICHDB_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return TRICHDB_DB_HELP;
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
     * Search for data from TrichDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        TrichDatabase trichDatabase = new TrichDatabase();
        trichDatabase.search(paramQuery, callback);
    }
}
