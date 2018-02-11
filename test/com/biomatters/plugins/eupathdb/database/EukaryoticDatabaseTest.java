package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.PluginDocument;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.TestGeneious;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The class <code>EukaryoticDatabaseTest</code> contains test cases for the class
 * <code>{@link EukaryoticDatabaseTest}</code>.
 *
 * @author sidney
 */
public class EukaryoticDatabaseTest {

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for search by tag.
     *
     * @throws Exception
     */
    @Test
    public void testRunAllSearchTests() throws DatabaseServiceException {
        TestGeneious.initialize();
        for  (EuDBTests euDBTest : EuDBTests.values()) {
            euDBTest.runTest();
        }
    }

    @Test
    public void testErrorReturn() throws DatabaseServiceException {
        TestGeneious.initialize();
        EukaryoticDatabase eukaryoticDatabase = new EuPathDatabase();
        String organisms = eukaryoticDatabase.getWebServiceTextSearchOrganismParamValue();
        AtomicReference<String> ref = eukaryoticDatabase.getWebServiceTextSearchOrganismParamReference();
        ref.set("foo,"+organisms); // this leaves class in a bad state, be sure to clean up
        try {
            EuDBTests.EuPathByText.runTest();
        } catch (DatabaseServiceException e) {
            Assert.assertTrue("Expected to catch server error message but caught "+e+": "+e.getUserMessage(), e.getMessage().contains("server responded with an error message"));
            return;  // expecting the correct error
        } finally {
            ref.set(organisms);  // clean up before running any more tests
        }
        Assert.fail("Expected to catch an error from the server, but somehow the test query succeeded");
    }

    /**
     * Test retrieveIDsInCSVString to retrieve Id Correctly.
     */
    @Test
    public void testRetrieveIDsInCSVStringTest() {
        String result = new EuPathDatabase().retrieveIDsInCSVString(getRecords(), new ArrayList<>());
        Assert.assertEquals("PF3D7_1111,PF3D7_1112,PF3D7_1113|,PF3D7_1114", result);
    }

    /**
     * Test retrieveIDsInCSVString to only retrieve Id which are not in URN-List
     */
    @Test
    public void testRetrieveIDsInCSVStringTestWithURN() {
        List<String> urnList = new ArrayList<>();
        urnList.add("PF3D7_1111");
        urnList.add("PF3D7_1113|");
        String result = new EuPathDatabase().retrieveIDsInCSVString(getRecords(), urnList);
        Assert.assertEquals("PF3D7_1112,PF3D7_1114", result);
    }

    /**
     * Test data for records
     *
     * @return - list of records
     */
    private List<Record> getRecords() {
        List<Record> records = new ArrayList<>();
        records.add(new Record("PF3D7_1111", null));
        records.add(new Record("PF3D7_1111", null));
        records.add(new Record("abc|PF3D7_1112", null));
        records.add(new Record("abc|PF3D7_1112", null));
        records.add(new Record("PF3D7_1113|", null));
        records.add(new Record("|PF3D7_1114", null));
        return records;
    }

    private enum EuDBTests {
        // tests named ByTag use query strings that should be interpreted as list of gene ids (locus tags) and return exact number of results
        // tests named ByText should use a free text query. We allow for future tests to get more responses if new matching items have been added to the database
        EuPathByTag(new EuPathDatabase(), "PF3D7_1133400", 1, 1),
        EuPathByText(new EuPathDatabase(), "ACA1_0417*", 6, 10),
        AmoebaByTag(new AmoebaDatabase(), "EDI_046900,EDI_046930", 2, 2),
        AmoebaByText(new AmoebaDatabase(), "*EDI_0469*", 4, 8),
        CryptoByTag(new CryptoDatabase(), "cgd7_2320,cgd7_2340", 2, 2),
        CryptoByText(new CryptoDatabase(), "cgd7_23*", 11, 15),
        FungiByTag(new FungiDatabase(), "NCU06650;NCU06656", 2, 2),
        FungiByText(new FungiDatabase(), "NCU0665*", 11, 15),
        GiardiaByTag(new GiardiaDatabase(), "GL50803_102438", 1, 1),
        GiardiaByText(new GiardiaDatabase(), "GL50803_1024*", 6, 10),
        MicrosporidiaByTag(new MicrosporidiaDatabase(), "ECU03_0820i", 1, 1),
        MicrosporidiaByText(new MicrosporidiaDatabase(), "ECU03_0820*", 8, 12),
        OrthoMCLByTag(new OrthoMCLDatabase(), "pfal|PF11_0344,pfal|PF11_0345", 2, 2),
        OrthoMCLByText(new OrthoMCLDatabase(), "*OG5_228447", 2, 6),
        PiroPlasmaByTag(new PiroPlasmaDatabase(), "TA14980,TA14985", 2, 2),
        PiroPlasmaByText(new PiroPlasmaDatabase(), "TA1498*", 2, 6),
        PlasmoByTag(new PlasmoDatabase(), "PF3D7_1133400,PGSY75_1133400", 2, 2),
        PlasmoByText(new PlasmoDatabase(), "PF3D7_113340*", 2, 6),
        ToxoByTag(new ToxoDatabase(), "TGME49_239250", 1, 1),
        ToxoByText(new ToxoDatabase(), "TGME49_2392*", 4, 8),
        TrichByTag(new TrichDatabase(), "TVAG_386080", 1, 1),
        TrichByText(new TrichDatabase(), "TVAG_38608*", 1, 5),
        TriTrypByTag(new TriTrypDatabase(), "Tb927.11.3120", 1, 1),
        TriTrypByText(new TriTrypDatabase(), "Tb927.11.312*", 1, 5);

        private final EukaryoticDatabase eukaryoticDatabase;
        private final String queryString;
        private final int minExpectedReturns;
        private final int maxExpectedReturns;

        EuDBTests(EukaryoticDatabase eukaryoticDatabase, String queryString, int minExpectedReturns, int maxExpectedReturns) {
            this.eukaryoticDatabase = eukaryoticDatabase;
            this.queryString = queryString;
            this.minExpectedReturns = minExpectedReturns;
            this.maxExpectedReturns = maxExpectedReturns;
        }

        void runTest() throws DatabaseServiceException {
            Callback callback = new Callback();
            Query query = Query.Factory.createQuery(queryString);
            eukaryoticDatabase.search(query, callback, new URN[]{}, new ArrayList<>());
            Assert.assertTrue(toString()+": Number of results is "+callback.addCount+", expected range ["+minExpectedReturns+", "+maxExpectedReturns+"]",
                    (callback.addCount >= minExpectedReturns) && (callback.addCount <= maxExpectedReturns));
        }
    }

    /**
     *
     */
    static class Callback extends RetrieveCallback {

        /**
         * The count of call to _add(PluginDocument pluginDocument, Map<String, Object> map)
         */
        int addCount = 0;

        /**
         * Overridden method to increase count on each call.
         *
         * @param pluginDocument the PluginDocument
         * @param map            the Map
         */
        @Override
        protected void _add(PluginDocument pluginDocument, Map<String, Object> map) {
            addCount++;
        }

        /**
         * Overridden method
         *
         * @param annotatedPluginDocument the AnnotatedPluginDocument
         * @param map                     the Map
         */
        @Override
        protected void _add(AnnotatedPluginDocument annotatedPluginDocument, Map<String, Object> map) {
            addCount++;   // should never be called, but let it contribute to an error if it is called
        }
    }
}