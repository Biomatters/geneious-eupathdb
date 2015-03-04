package com.biomatters.plugins.eupathdb.webservices;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
	 * This method makes web service call and returns response in string format.
	 * 
	 * @param url
	 * @return string
	 */
	public static String call(String url) {
		Client client = Client.create();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.post(ClientResponse.class);
		return response.getEntity(String.class);
	}
}
