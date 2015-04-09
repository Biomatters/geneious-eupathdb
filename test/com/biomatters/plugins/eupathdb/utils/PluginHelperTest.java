package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.PluginDocument;
import com.biomatters.plugins.eupathdb.EuPathDBGenes;
import com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
 * The class <code>PluginHelperTest</code> contains test cases for the class
 * <code>{@link PluginHelper}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EuPathDBWebService.class, PluginHelper.class})
public class PluginHelperTest {

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearch() throws Exception {
        Callback callback = new Callback();
        Query query = Mockito.mock(BasicSearchQuery.class);
        Response response = Mockito.mock(Response.class);
        EuPathDBWebService service = Mockito.mock(EuPathDBWebService.class);

        Mockito.doReturn("PF3D7_1133400").when((BasicSearchQuery) query).getSearchText();
        PowerMockito.whenNew(EuPathDBWebService.class).withAnyArguments().thenReturn(service);
        Mockito.when(
                response.readEntity(com.biomatters.plugins.eupathdb.webservices.models.Response.class))
                .thenReturn(TestUtilities.getWebServiceResponse());
        Mockito.when(
                service.post(Mockito.any(URI.class),
                        Mockito.anyMapOf(String.class, Object.class),
                        Mockito.any(MessageBodyReader.class))).thenReturn(
                response);

        PluginHelper pluginHelper = new PluginHelper();
        pluginHelper.processSearch(query, callback, EuPathDBGenes.EuPathDatabase.PIROPLASMADB);

        Assert.assertEquals(14, callback.addCount);
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