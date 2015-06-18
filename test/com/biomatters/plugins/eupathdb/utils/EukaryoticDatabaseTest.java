package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.PluginDocument;
import com.biomatters.plugins.eupathdb.database.EuPathDatabase;
import com.biomatters.plugins.eupathdb.database.EukaryoticDatabase;
import com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService;
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
import java.net.URI;
import java.util.Map;

/**
 * The class <code>EukaryoticDatabaseTest</code> contains test cases for the class
 * <code>{@link com.biomatters.plugins.eupathdb.utils.EukaryoticDatabaseTest}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
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
        Mockito.doReturn("PF3D7_1133400").when((BasicSearchQuery) query).getSearchText();
        Mockito.when(
                response.readEntity(com.biomatters.plugins.eupathdb.webservices.models.Response.class))
                .thenReturn(TestUtilities.getWebServiceResponse());
        eukaryoticDatabase.search(query, callback);
        Assert.assertEquals(14, callback.addCount);
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for search by text.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearchForSearchByText() throws Exception {
        Mockito.doReturn("adc").when((BasicSearchQuery) query).getSearchText();
        Mockito.when(
                response.readEntity(com.biomatters.plugins.eupathdb.webservices.models.Response.class))
                .thenReturn(TestUtilities.getWebServiceResponse());
        eukaryoticDatabase.search(query, callback);
        Assert.assertEquals(14, callback.addCount);
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test for error response.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearch_ErrorResponse() throws Exception {
        expectedEx.expect(DatabaseServiceException.class);
        expectedEx.expectMessage("The input to parameter 'Text term (use * as wildcard)' is required<br><b>Type: </b>User Error<br><b>Code: </b>020");

        Mockito.doReturn("adc").when((BasicSearchQuery) query).getSearchText();
        Mockito.when(
                response.readEntity(com.biomatters.plugins.eupathdb.webservices.models.Response.class))
                .thenReturn(TestUtilities.getWebServiceErrorResponse());
        eukaryoticDatabase.search(query, callback);
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