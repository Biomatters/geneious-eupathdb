package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;
import com.biomatters.plugins.eupathdb.database.OrthoMCLDatabase;

/**
 * The Class <code>OrthoMCLDB</code> provides documents in response to a
 * search query executed on the genome database OrthoMCL.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class OrthoMCLDB extends DatabaseService {

    private static final String ORTHOMCL_HELP = "Provides services to search for sequences in OrthoMCL. OrthoMCL belongs to the EuPathDB family of databases.";
    private static final String ORTHOMCL_DESCRIPTION = "Provides services to search for sequences in OrthoMCL";
    private static final String ORTHOMCL = "OrthoMCL Service";
    private static final String ORTHOMCL_PLUGIN = "OrthoMCL Service";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return ORTHOMCL_PLUGIN;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return ORTHOMCL;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return ORTHOMCL_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return ORTHOMCL_HELP;
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
     * Search for data from AmoebaDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        OrthoMCLDatabase orthoMCLDatabase = new OrthoMCLDatabase();
        orthoMCLDatabase.search(paramQuery, callback);
    }
}
