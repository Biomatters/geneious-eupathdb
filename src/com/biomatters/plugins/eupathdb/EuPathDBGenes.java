package com.biomatters.plugins.eupathdb;

import javax.swing.JPanel;

import com.biomatters.geneious.publicapi.plugin.GeneiousServiceListener;
import com.biomatters.geneious.publicapi.plugin.GeneiousServiceWithPanel;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.services.PiroplasmaDB;
import com.biomatters.plugins.eupathdb.services.PlasmoDB;
import com.biomatters.plugins.eupathdb.utils.EuPathDBConstants;

/**
 * The Class <code>EuPathDBGenes</code> displays a custom panel for the EuPathDB
 * family of databases.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class EuPathDBGenes extends GeneiousServiceWithPanel {

    private static final String EU_PATH_DB_PLUGIN = "EuPathDB Service";
    private static final String NUCLEOTIDE_OR_PROTEIN32_ICON = "nucleotideOrProtein32.png";

    /**
     * Gets the panel.
     *
     * @return the panel
     */
    @Override
    public JPanel getPanel() {
        return new JPanel();
    }

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
        return IconUtilities.getIcons(NUCLEOTIDE_OR_PROTEIN32_ICON);
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
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return EU_PATH_DB_PLUGIN;
    }

    /**
     * Initialize the EuPathDB family of databases.
     *
     * @param listener the listener
     */
    @Override
    protected void initialize(GeneiousServiceListener listener) {
        listener.childServiceAdded(new PlasmoDB());
        listener.childServiceAdded(new PiroplasmaDB());
        super.initialize(listener);
    }

    /**
     * Enum for EuPathDB family of databases.
     */
    public enum EuPathDatabase {
        PLASMODB, PIROPLASMADB
    }

}
