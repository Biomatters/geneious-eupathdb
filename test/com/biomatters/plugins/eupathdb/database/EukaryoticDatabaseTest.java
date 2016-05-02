package com.biomatters.plugins.eupathdb.database;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.PluginDocument;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.plugin.GeneiousService;
import com.biomatters.plugins.eupathdb.utils.TestUtilities;
import com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class <code>EukaryoticDatabaseTest</code> contains test cases for the class
 * <code>{@link EukaryoticDatabaseTest}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.swing.*", "javax.security.*", "org.freehep.graphicsio.raw.*",
        "org.freehep.graphicsio.gif.*", "org.freehep.graphicsio.ppm.*", "apple.laf.*", "com.apple.*",
        "javax.imageio.*", "javax.net.*", "com.sun.*", "javax.xml.parsers.*", "org.xml.*", "javax.crypto.*"})
@SuppressStaticInitializationFor("com.biomatters.geneious.publicapi.plugin.Icons$PointlessJPanelHolder")
@PrepareForTest({EuPathDBWebService.class, EukaryoticDatabase.class})
public class EukaryoticDatabaseTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private Query query;
    private Callback callback;
    private EukaryoticDatabase eukaryoticDatabase;
    private Response response;

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     */
    @Before
    public void setUp() throws Exception {
        callback = new Callback();
        eukaryoticDatabase = new EuPathDatabase();
        query = Mockito.mock(BasicSearchQuery.class);
        response = Mockito.mock(Response.class);

        EuPathDBWebService service = Mockito.mock(EuPathDBWebService.class);
        PowerMockito.whenNew(EuPathDBWebService.class).withAnyArguments().thenReturn(service);
        Mockito.when(
                service.post(Mockito.any(URI.class),
                        Mockito.anyMapOf(String.class, String.class),
                        Mockito.any(MessageBodyReader.class)))
                .thenReturn(response);
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for search by tag.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearchForSearchByTag() throws Exception {
        mockAndProcessSearch("PF3D7_1133400", "ResponseDataXML.txt");
        Assert.assertEquals(7, callback.addCount);
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for search by text.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearchForSearchByText() throws Exception {
        mockAndProcessSearch("adc", "ResponseDataXML.txt");
        Assert.assertEquals(7, callback.addCount);
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for search on OrthoMCL DB Service.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearchForOrthoMCL() throws Exception {
        mockAndProcessSearch("OG5_228447", "ResponseDataXMLForOrthoMCL.txt");
        Assert.assertEquals(2, callback.addCount);
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for error response.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearch_ErrorResponse() throws Exception {
        expectedEx.expect(DatabaseServiceException.class);
        expectedEx.expectMessage("User Error (#020): The input to parameter 'Text term (use * as wildcard)' is required");

        mockAndProcessSearch("adc", "ResponseDataXML_Error.txt");
    }

    /**
     * Test retrieveIDsInCSVString to retrieve Id Correctly.
     */
    @Test
    public void testRetrieveIDsInCSVStringTest() {
        String result = eukaryoticDatabase.retrieveIDsInCSVString(getRecords(), new ArrayList<String>());
        Assert.assertEquals("PF3D7_1111,PF3D7_1112,PF3D7_1113|,PF3D7_1114", result);
    }

    /**
     * Test retrieveIDsInCSVString to only retrieve Id which are not in URN-List
     */
    @Test
    public void testRetrieveIDsInCSVStringTestWithURN() {
        List<String> urnList = new ArrayList<String>();
        urnList.add("PF3D7_1111");
        urnList.add("PF3D7_1113|");
        String result = eukaryoticDatabase.retrieveIDsInCSVString(getRecords(), urnList);
        Assert.assertEquals("PF3D7_1112,PF3D7_1114", result);
    }

    /**
     * Test data for records
     *
     * @return - list of records
     */
    private List<Record> getRecords() {
        List<Record> records = new ArrayList<Record>();
        records.add(new Record("PF3D7_1111", null));
        records.add(new Record("PF3D7_1111", null));
        records.add(new Record("abc|PF3D7_1112", null));
        records.add(new Record("abc|PF3D7_1112", null));
        records.add(new Record("PF3D7_1113|", null));
        records.add(new Record("|PF3D7_1114", null));
        return records;
    }

    /**
     * Mocking search-query and readEntity.
     * Hit search based on mocking data.
     *
     * @param searchQuery - search query.
     * @param fileName    - test-data file name.
     * @throws IOException
     * @throws DatabaseServiceException
     */
    private void mockAndProcessSearch(String searchQuery, String fileName) throws IOException, DatabaseServiceException {
        Mockito.doReturn(searchQuery).when((BasicSearchQuery) query).getSearchText();
        Mockito.when(
                response.readEntity(com.biomatters.plugins.eupathdb.webservices.models.Response.class))
                .thenReturn(TestUtilities.getWebServiceResponse(fileName));
        eukaryoticDatabase.search(query, callback, new URN[]{}, new ArrayList<GeneiousService>());
    }

    /**
     *
     */
    class Callback extends RetrieveCallback {

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
        }
    }
}