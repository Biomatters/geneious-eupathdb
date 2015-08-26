package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.GeneiousServiceListener;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;
import com.biomatters.plugins.eupathdb.database.*;

/**
 * The Class <code>EuPathDB</code> provides documents in response to a search
 * query executed on the genome database EuPathDB.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class EuPathDB extends DatabaseService {

    private static final String EUPATH_DB_HELP = "Provides services to search for genes in EuPathDB. EuPathDB (formerly ApiDB) is an integrated database covering the eukaryotic pathogens in the genera Acanthamoeba, Annacaliia, Babesia, Crithidia, Cryptosporidium, Edhazardia, Eimeria, Encephalitozoon, Endotrypanum, Entamoeba, Enterocytozoon, Giardia, Gregarina, Hamiltosporidium, Leishmania, Nematocida, Neospora, Nosema, EuPathdium, Theileria, Toxoplasma, Trichomonas, Trypanosoma and Vavraia, Vittaforma.";
    private static final String EUPATH_DB_DESCRIPTION = "Provides services to search for genes in EuPathDB";
    private static final String PLUGIN_ICON = "database16.png";
    private static final String PLUGIN_NAME = "EuPathDB";

    /**
     * Gets the unique id.
     *
     * @return the unique id
     */
    @Override
    public String getUniqueID() {
        return PLUGIN_NAME;
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
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return EUPATH_DB_DESCRIPTION;
    }

    /**
     * Gets the help.
     *
     * @return the help
     */
    @Override
    public String getHelp() {
        return EUPATH_DB_HELP;
    }

    /**
     * Gets the icons.
     *
     * @return the icons
     */
    @Override
    public Icons getIcons() {
        return IconUtilities.getIcons(PLUGIN_ICON);
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

    @Override
    protected void initialize(GeneiousServiceListener geneiousServiceListener) {
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new AmoebaDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new CryptoDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new FungiDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new GiardiaDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new MicrosporidiaDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new PiroPlasmaDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new PlasmoDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new ToxoDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new TrichDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new TriTrypDatabase()));
        geneiousServiceListener.childServiceAdded(new EuPathDatabaseService(new OrthoMCLDatabase()));
        super.initialize(geneiousServiceListener);
    }

    /**
     * Search for data from EuPathDB.
     *
     * @param paramQuery      Contains the search criteria
     * @param callback        Reports the documents obtained from DatabaseService search
     * @param paramArrayOfURN the param array of urn
     * @throws com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException the database service exception
     */
    @Override
    public void retrieve(Query paramQuery, RetrieveCallback callback,
                         URN[] paramArrayOfURN) throws DatabaseServiceException {
        EuPathDatabase EuPathDatabase = new EuPathDatabase();
        EuPathDatabase.search(paramQuery, callback, paramArrayOfURN, getChildServices());
    }
}
