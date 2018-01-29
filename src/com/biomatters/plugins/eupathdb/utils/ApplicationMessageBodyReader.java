package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.plugins.eupathdb.webservices.models.wadl.Application;

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
 * The Class <code>ApplicationMessageBodyReader</code> unmarshals application from wadl.
 *
 * @author sidney
 */
public class ApplicationMessageBodyReader implements
        MessageBodyReader<Application> {

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
        return type == Application.class;
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
    public Application readFrom(Class<Application> type, Type genericType,
                             Annotation[] annotations, MediaType mediaType,
                             MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Application.class);
            return (Application) jaxbContext.createUnmarshaller().unmarshal(entityStream);
        } catch (JAXBException jaxbException) {
            String msg = "Failed to download results from server";
            if(jaxbException.getMessage() != null){
                msg += ": " + jaxbException.getMessage();
            }
            throw new ProcessingException(msg, jaxbException);
        }
    }
}
