package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.BasicSearchQuery;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.RetrieveCallback;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.PluginDocument;
import com.biomatters.plugins.eupathdb.EuPathDBGenes;
import com.biomatters.plugins.eupathdb.webservices.WebServiceClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * The class <code>PluginHelperTest</code> contains tests for the class
 * <code>{@link PluginHelper}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(WebServiceClient.class)
public class PluginHelperTest {
    private String responseJson = "";
    private FileReader reader = null;
    private BufferedReader bufferedReader = null;

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     */
    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(WebServiceClient.class);
        read();
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception if the clean-up fails for some reason
     */
    @After
    public void tearDown() throws Exception {
        // Add additional tear down code here
        if (reader != null) {
            reader.close();
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

    /**
     * Run the processSearch(Query, RetrieveCallback, EuPathDatabase) method test.
     *
     * @throws Exception
     */
    @Test
    public void testProcessSearch() throws Exception {
        class Callback extends RetrieveCallback {
            public int addCount = 0;

            @Override
            protected void _add(PluginDocument pluginDocument, Map<String, Object> map) {
                addCount++;
            }

            @Override
            protected void _add(AnnotatedPluginDocument annotatedPluginDocument, Map<String, Object> map) {
            }
        }
        Callback callback = new Callback();
        Query query = Mockito.mock(BasicSearchQuery.class);

        Mockito.when(WebServiceClient.call(Mockito.anyString())).thenReturn(responseJson);
        Mockito.doReturn("PF3D7_1133400").when((BasicSearchQuery) query).getSearchText();

        PluginHelper pluginHelper = new PluginHelper();
        pluginHelper.processSearch(query, callback, EuPathDBGenes.EuPathDatabase.PIROPLASMADB);
        if (callback.addCount != 2) {
            Assert.fail("There should be 2 instance of the DefaultSequenceDocument are added to the RetrieveCallback. However added instance are " + callback.addCount + ".");
        }
    }

    /**
     * Reads and return data string from given file.
     *
     * @throws java.io.IOException
     */
    private void read() throws IOException {
        String inputLine;
        StringBuilder jsonString = new StringBuilder();
        reader = new FileReader(getClass().getResource("JSONResponseString.txt").getPath());
        bufferedReader = new BufferedReader(reader);

        while ((inputLine = bufferedReader.readLine()) != null) {
            jsonString.append(inputLine);
        }
        responseJson = jsonString.toString();
    }
}