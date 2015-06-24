package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;
import com.biomatters.plugins.eupathdb.database.GiardiaDatabase;

/**
 * The Class <code>GiardiaDB</code> provides documents in response to a
 * search query executed on the genome database GiardiaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class GiardiaDB extends DatabaseService {

    private static final String GIARDIADB_DB_HELP = "Provides services to search for genes in GiardiaDB. Giardia lamblia is a significant, environmentally transmitted, human pathogen and an amitochondriate protist.";
    private static final String GIARDIADB_DB_DESCRIPTION = "Provides services to search for genes in GiardiaDB";
    private static final String GIARDIADB_DB = "GiardiaDB Service";
    private static final String GIARDIADB_DB_PLUGIN = "GiardiaDB Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return GIARDIADB_DB_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return GIARDIADB_DB;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return GIARDIADB_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return GIARDIADB_DB_HELP;
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
     * Search for data from GiardiaDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        GiardiaDatabase giardiaDatabase = new GiardiaDatabase();
        giardiaDatabase.search(paramQuery, callback);
    }
}