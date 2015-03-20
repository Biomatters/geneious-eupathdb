package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseService;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.QueryField;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.EuPathDBGenes.EuPathDatabase;
import com.biomatters.plugins.eupathdb.utils.EuPathDBConstants;
import com.biomatters.plugins.eupathdb.utils.PluginHelper;

/**
 * The Class <code>PlasmoDB</code> provides documents in response to a search
 * query executed on the genome database PlasmoDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PlasmoDB extends DatabaseService {

    private static final String PLASMO_DB_HELP = "Provides services to search for genes in PlasmoDB.PlasmoDB is a genome database for the genus Plasmodium, a set of single-celled eukaryotic pathogens that cause human and animal diseases, including malaria.";
    private static final String PLASMO_DB_DESCRIPTION = "Provides services to search for genes in PlasmoDB";
    private static final String PLASMO_DB = "PlasmoDB Service";
    private static final String PLASMO_DB_PLUGIN = "PlasmoDB Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return PLASMO_DB_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return PLASMO_DB;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return PLASMO_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return PLASMO_DB_HELP;
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
     * Search for data from PlasmoDB.
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
                EuPathDatabase.PLASMODB);
    }
}
