package com.biomatters.plugins.eupathdb.webservices;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * This Class <code>WebServiceClient</code> creates jersey client and makes web
 * service call. It retrieves web service response and returns it back in string
 * format.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class WebServiceClient {

    /**
     * Since serving as a utility.
     */
    private WebServiceClient() {
    }

    /**
     * This method makes web service call and returns response in string format.
     *
     * @param url the webservice url
     * @return string the json string
     */
    public static String call(String url) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response response = target.request().post(Entity.text(""));

        return response.readEntity(String.class);
    }
}
