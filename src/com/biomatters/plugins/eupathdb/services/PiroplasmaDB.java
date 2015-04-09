package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.EuPathDBGenes.EuPathDatabase;
import com.biomatters.plugins.eupathdb.utils.EuPathDBConstants;
import com.biomatters.plugins.eupathdb.utils.PluginHelper;

/**
 * The Class <code>PiroplasmaDB</code> provides documents in response to a
 * search query executed on the genome database PiroplasmaDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PiroplasmaDB extends DatabaseService {

    private static final String PIROPLASMA_DB_HELP = "Provides services to search for genes in PiroPlasmaDB. PiroplasmaDB is a genome database for the genus Piroplasma";
    private static final String PIROPLASMA_DB_DESCRIPTION = "Provides services to search for genes in PiroPlasmaDB";
    private static final String PIROPLASMA_DB = "PiroplasmaDB Service";
    private static final String PIROPLASMA_DB_PLUGIN = "PiroplasmaDB Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return PIROPLASMA_DB_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return PIROPLASMA_DB;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return PIROPLASMA_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return PIROPLASMA_DB_HELP;
    }

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    @Override
    public Icons getIcons() {
        return IconUtilities.getIcons(EuPathDBConstants.PLUGIN_ICON);
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
     * Search for data from PiroplasmaDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {

        PluginHelper pluginHelper = new PluginHelper();
        pluginHelper.processSearch(paramQuery, callback,
                EuPathDatabase.PIROPLASMADB);
    }
}
