package com.biomatters.plugins.eupathdb;

import com.biomatters.geneious.publicapi.plugin.GeneiousPlugin;
import com.biomatters.geneious.publicapi.plugin.GeneiousService;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.plugins.eupathdb.utils.EuPathDBConstants;

/**
 * The Class <code>EuPathDBPlugin</code> represents the EuPathDB service for
 * Geneious.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class EuPathDBPlugin extends GeneiousPlugin {

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return EuPathDBConstants.PLUGIN_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return null;
    }

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    @Override
    public Icons getIcons() {
        return null;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return EuPathDBConstants.PLUGIN_NAME;
    }

    /**
     * Gets the authors.
     *
     * @return the authors
     */
    @Override
    public String getAuthors() {
        return EuPathDBConstants.PLUGIN_AUTHORS;
    }

    /**
     * Gets the maximum api version.
     *
     * @return the maximum api version
     */
    @Override
    public int getMaximumApiVersion() {
        return EuPathDBConstants.PLUGIN_MAX_API_VERSION;
    }

    /**
     * Gets the minimum api version.
     *
     * @return the minimum api version
     */
    @Override
    public String getMinimumApiVersion() {
        return EuPathDBConstants.PLUGIN_MIN_API_VERSION;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    @Override
    public String getVersion() {
        return EuPathDBConstants.PLUGIN_VERSION;
    }

    /**
     * Gets the services.
     *
     * @return the services
     */
    @Override
    public GeneiousService[] getServices() {
        return new GeneiousService[]{new EuPathDBGenes()};
    }
}
