package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;
import com.biomatters.plugins.eupathdb.database.MicrosporidiaDatabase;

/**
 * The Class <code>MicrosporidiaDB</code> provides documents in response to a
 * search query executed on the genome database MicrosporidiaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class MicrosporidiaDB extends DatabaseService {

    private static final String MICROSPORIDIADB_DB_HELP = "Provides services to search for genes in MicrosporidiaDB. MicrosporidiaDB belongs to the EuPathDB family of databases and is an integrated genomic and functional genomic database for the phylum Microsporidia.";
    private static final String MICROSPORIDIADB_DB_DESCRIPTION = "Provides services to search for genes in MicrosporidiaDB";
    private static final String MICROSPORIDIADB_DB = "MicrosporidiaDB Service";
    private static final String MICROSPORIDIADB_DB_PLUGIN = "MicrosporidiaDB Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return MICROSPORIDIADB_DB_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return MICROSPORIDIADB_DB;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return MICROSPORIDIADB_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return MICROSPORIDIADB_DB_HELP;
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
     * Search for data from MicrosporidiaDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        MicrosporidiaDatabase microsporidiaDatabase = new MicrosporidiaDatabase();
        microsporidiaDatabase.search(paramQuery, callback);
    }
}
