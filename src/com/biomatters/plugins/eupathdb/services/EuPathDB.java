package com.biomatters.plugins.eupathdb.services;

import com.biomatters.geneious.publicapi.plugin.GeneiousServiceListener;
import com.biomatters.plugins.eupathdb.database.*;

/**
 * The Class <code>EuPathDB</code> is the root for the genome database services
 * and executes queries on the inclusive EuPathDB database
 *
 * @author sidney
 */
public class EuPathDB extends EuPathDatabaseService {

    public EuPathDB() {
        super(new EuPathDatabase());
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

}
