package com.biomatters.plugins.eupathdb.webservices;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import org.glassfish.jersey.client.JerseyInvocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * The class <code>EuPathDBWebServiceTest</code> contains test cases for the class
 * <code>{@link com.biomatters.plugins.eupathdb.webservices.EuPathDBWebService}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EuPathDBWebService.class, JerseyInvocation.Builder.class})
public class EuPathDBWebServiceTest {

    /**
     * The Response
     */
    private Response response;
    /**
     * The EuPathDBWebService
     */
    private EuPathDBWebService spyService;

    /**
     * Performs initial setups for each test case.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Client client = Mockito.mock(Client.class);
        WebTarget target = Mockito.mock(WebTarget.class);
        response = Mockito.mock(Response.class);
        JerseyInvocation.Builder builder = Mockito.mock(JerseyInvocation.Builder.class);
        spyService = PowerMockito.spy(new EuPathDBWebService());

        Mockito.when(client.target(Mockito.any(URI.class))).thenReturn(target);
        Mockito.when(target.request(MediaType.APPLICATION_XML)).thenReturn(builder);
        Mockito.when(builder.post(Entity.text(""))).thenReturn(response);
        PowerMockito
                .when(spyService,
                        PowerMockito.method(EuPathDBWebService.class,
                                "createClient")).withNoArguments()
                .thenReturn(client);
    }

    /**
     * Test case for the method Response post(URI uri, Map<String, Object>
     * paramMap) to test webservice response having status code 200
     *
     * @throws Exception
     */
    @Test
    public void testPost() throws Exception {
        Mockito.when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());

        Map<String, String> paramMap = new HashMap<String, String>(2);
        paramMap.put("testParam1", "testVal1");
        paramMap.put("testParam2", "testVal2");

        Response wsResponse = spyService.post(new URI("http://testService.com"), paramMap);
        Assert.assertEquals(200, wsResponse.getStatus());
    }

    /**
     * Test case for the method Response post(URI uri) to test webservice
     * response having status code 400 and above.
     *
     * @throws Exception
     */
    @Test(expected = DatabaseServiceException.class)
    public void testPost_WebApplicationException() throws Exception {
        Response.StatusType statusInfo = Response.Status.BAD_REQUEST;

        Mockito.when(response.getStatus()).thenReturn(Response.Status.BAD_REQUEST.getStatusCode());
        Mockito.when(response.getStatusInfo()).thenReturn(statusInfo);

        spyService.post(new URI("http://testService.com"));
    }
}