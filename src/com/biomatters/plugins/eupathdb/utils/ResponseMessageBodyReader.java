package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.plugins.eupathdb.webservices.models.Response;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


/**
 * The Class <code>ResponseMessageBodyReader</code> unmarshals response.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class ResponseMessageBodyReader implements
        MessageBodyReader<Response> {

    /**
     * Class casting check.
     *
     * @param type        the Type
     * @param genericType the GenericType
     * @param annotations the Annotations
     * @param mediaType   the MediaType
     * @return boolean
     */
    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        return type == Response.class;
    }

    /**
     * Unmarshals input stream into configured bean.
     *
     * @param type         the Type
     * @param genericType  the GenericType
     * @param annotations  the Annotations
     * @param mediaType    the MediaType
     * @param httpHeaders  the MultivaluedMap
     * @param entityStream the InputStream
     * @return Response the Response
     * @throws IOException
     */
    @Override
    public Response readFrom(Class<Response> type, Type genericType,
                             Annotation[] annotations, MediaType mediaType,
                             MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            return (Response) jaxbContext.createUnmarshaller().unmarshal(entityStream);
        } catch (JAXBException jaxbException) {
            throw new ProcessingException("Failed to download results from server.", jaxbException);
        }
    }
}
