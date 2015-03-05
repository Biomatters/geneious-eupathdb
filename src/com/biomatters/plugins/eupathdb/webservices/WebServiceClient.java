package com.biomatters.plugins.eupathdb.webservices;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
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
	 * Since serving as a utility.
	 */
	private WebServiceClient(){
	}

	/**
	 * This method makes web service call and returns response in string format.
	 * 
	 * @param url
	 * @return string
	 * @throws DatabaseServiceException 
	 */
	public static String call(String url) throws DatabaseServiceException {
		Client client = Client.create();

		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.post(ClientResponse.class);
		if(response.getStatus() != 200) {
			throw new DatabaseServiceException("Search failed: ", true);
		}
		return response.getEntity(String.class);
	}
}
