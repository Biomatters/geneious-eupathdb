package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.utilities.StringUtilities;
import com.biomatters.plugins.eupathdb.webservices.models.Response;

import javax.ws.rs.ProcessingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * The class <code>TestUtilities</code> contains utility methods for test cases.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class TestUtilities {

    /**
     * Reads and return data string from given file.
     *
     * @param filename the filename
     * @return String
     * @throws java.io.IOException
     */
    public static String read(String filename) throws IOException {
        FileReader reader;
        String fileData;

        URL resource = TestUtilities.class.getResource(filename);
        String path = resource.getPath();
        reader = new FileReader(path);
        fileData = StringUtilities.getTextFromReader(reader, true);

        return fileData;
    }

    /**
     * Returns dummy web service response string in xml format.
     *
     * @return String
     * @throws java.io.IOException
     */
    public static Response getWebServiceResponse(String fileName) throws IOException {
        String responseStr = TestUtilities.read(fileName);
        return unmarshal(Response.class, responseStr);
    }

    /**
     * Unmarshals given string into given class.
     *
     * @param type        the Type
     * @param inputString the String
     * @param <T>         the Class<T>
     * @return <T> the Class<T>
     */
    public static <T> T unmarshal(Class<T> type, String inputString) {
        try {
            InputStream entityStream = new ByteArrayInputStream(inputString.getBytes());
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            return type.cast(jaxbContext.createUnmarshaller().unmarshal(entityStream));
        } catch (JAXBException jaxbException) {
            throw new ProcessingException("Error in deserializing a " + type.getName(),
                    jaxbException);
        }
    }
}
