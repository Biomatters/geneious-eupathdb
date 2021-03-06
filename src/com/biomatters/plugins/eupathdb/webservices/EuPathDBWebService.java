package com.biomatters.plugins.eupathdb.webservices;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.plugins.eupathdb.EuPathDBPlugin;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.MessageBodyReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * The Class <code>EuPathDBWebService</code> provides interfaces to call post
 * web services for the given URI. It allows customization of URI, parameters
 * map, and MessageBodyReader.
 *
 * @author cybage
 */
public class EuPathDBWebService {

    private static final int HTTP_ERROR_CODE = 400;

    private static final int TIMEOUT_FIVE_MINUTE = 300000;

    /**
     * Builds client.
     *
     * @return Client
     */
    private Client createClient() {
        Client client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT_FIVE_MINUTE);
        client.property(ClientProperties.READ_TIMEOUT, TIMEOUT_FIVE_MINUTE);
        client.register(new LoggingFilter(Logger.getLogger(EuPathDBPlugin.class.getName()), false));
        return client;
    }

    /**
     * Builds URI from given uri as well as registers given
     * MessageBodyReader instance with client, makes web service get call.
     *
     * @param uri      the URI
     * @param reader   the MessageBodyReader
     * @return Response
     */
    public Response get(URI uri, MessageBodyReader<?> reader) throws DatabaseServiceException {
        Client client = createClient().register(reader);
        UriBuilder uriBuilder = UriBuilder.fromUri(uri);
        URI paramURI = uriBuilder.build();
        Response response;
        try {
            WebTarget target = client.target(paramURI);
            response = target.request(MediaType.APPLICATION_XML).get();
            if (response.getStatus() == 301) {
                URI redirectUri = response.getLocation();
                if (redirectUri != null) {
                    target = client.target(redirectUri);
                    response = target.request(MediaType.APPLICATION_XML).get();
                }
            }
            if (response.getStatus() >= HTTP_ERROR_CODE) {
                WebApplicationException we = new WebApplicationException(response);
                throw new DatabaseServiceException(we, we.getMessage(), true);
            }
        } catch (WebApplicationException e) {
            throw new DatabaseServiceException(e, e.getMessage(), true);
        } catch (ProcessingException e) {
            throw new DatabaseServiceException(e, e.getMessage(), true);
        }
        return response;
    }

    /**
     * Builds URI from given uri and parameter map and makes web service post call using given client.
     *
     * @param client   the Client
     * @param uri      the URI
     * @param paramMap the Map
     * @return Response
     */
    private Response post(Client client, URI uri, Map<String, String> paramMap) throws DatabaseServiceException {
        Form form = new Form();
        for (Entry<String, String> entry : paramMap.entrySet()) {
            form.param(entry.getKey(), entry.getValue());
        }
        Response response;
        try {
            WebTarget target = client.target(uri);
            response = target.request(MediaType.APPLICATION_XML).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
            if (response.getStatus() == 301) {
                URI redirectUri = response.getLocation();
                if (redirectUri != null) {
                    target = client.target(redirectUri);
                    response = target.request(MediaType.APPLICATION_XML).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
                }
            }
            if (response.getStatus() >= HTTP_ERROR_CODE) {
                WebApplicationException we = new WebApplicationException(response);
                throw new DatabaseServiceException(we, we.getMessage(), true);
            }
        } catch (WebApplicationException e) {
            throw new DatabaseServiceException(e, e.getMessage(), true);
        } catch (ProcessingException e) {
            throw new DatabaseServiceException(e, e.getMessage(), true);
        }

        return response;
    }

    /**
     * Builds URI from given uri and parameter map as well as registers given
     * MessageBodyReader instance with client, makes web service post call.
     *
     * @param uri      the URI
     * @param paramMap the Map
     * @param reader   the MessageBodyReader
     * @return Response
     */
    public Response post(URI uri, Map<String, String> paramMap, MessageBodyReader<?> reader) throws DatabaseServiceException {
        Client client = createClient().register(reader);
        return post(client, uri, paramMap);
    }

    /**
     * Builds URI from given uri and parameter map and makes web service post
     * call.
     *
     * @param uri      the URI
     * @param paramMap the Map
     * @return Response
     */
    public Response post(URI uri, Map<String, String> paramMap) throws DatabaseServiceException {
        return post(createClient(), uri, paramMap);
    }

    /**
     * Makes web service post call for the given URI.
     *
     * @param uri the URI
     * @return Response
     */
    public Response post(URI uri) throws DatabaseServiceException {
        return post(uri, new HashMap<String, String>());
    }
}
