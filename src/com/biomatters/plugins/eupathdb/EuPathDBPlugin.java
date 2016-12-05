package com.biomatters.plugins.eupathdb;

import com.biomatters.geneious.publicapi.plugin.GeneiousPlugin;
import com.biomatters.geneious.publicapi.plugin.GeneiousService;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.plugins.eupathdb.services.EuPathDB;

/**
 * The Class <code>EuPathDBPlugin</code> represents the EuPathDB service for
 * Geneious.
 *
 * @author cybage
 */
public class EuPathDBPlugin extends GeneiousPlugin {
    /* Plugin specific constants */
    private static final String PLUGIN_NAME = "EuPathDB Plugin";
    private static final String PLUGIN_VERSION = "1.0";
    private static final String PLUGIN_DESCRIPTION = "Provides services to search for genes in various EuPathDB databases";
    private static final String PLUGIN_AUTHORS = "Biomatters Ltd, Charles Ma and Svenja Gunther";
    private static final String PLUGIN_MIN_API_VERSION = "4.1000";
    private static final int PLUGIN_MAX_API_VERSION = 4;

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return PLUGIN_DESCRIPTION;
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
        return PLUGIN_NAME;
    }

    /**
     * Gets the authors.
     *
     * @return the authors
     */
    @Override
    public String getAuthors() {
        return PLUGIN_AUTHORS;
    }

    /**
     * Gets the maximum api version.
     *
     * @return the maximum api version
     */
    @Override
    public int getMaximumApiVersion() {
        return PLUGIN_MAX_API_VERSION;
    }

    /**
     * Gets the minimum api version.
     *
     * @return the minimum api version
     */
    @Override
    public String getMinimumApiVersion() {
        return PLUGIN_MIN_API_VERSION;
    }

    /**
     *  1.0 - released 2015-09-01
     *      - first release
     */
    @Override
    public String getVersion() {
        return PLUGIN_VERSION;
    }

    /**
     * Gets the services.
     *
     * @return the services
     */
    @Override
    public GeneiousService[] getServices() {
        return new GeneiousService[]{new EuPathDB()};
    }
}
