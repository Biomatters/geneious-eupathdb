package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.google.gson.JsonSyntaxException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * The class <code>JSONParserTest</code> contains tests for the class
 * <code>{@link JSONParser}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class JSONParserTest {
    String responseJson = "";
    FileReader reader = null;
    BufferedReader bufferedReader = null;

    /**
     * Run the List<Map<String, String>> parseJSON(String) method test for recordset response.
     *
     * @throws Exception
     */
    @Test
    public void testParseJSON() throws Exception {
        read("JSONResponseString.txt");
        List<Map<String, String>> result = JSONParser.parse(responseJson);
        assertEquals("PF3D7_1133400", result.get(0).get("primary_key"));
    }

    /**
     * Run the List<Map<String, String>> parseJSON(String) method test for error response.
     *
     * @throws Exception
     */
    @Test
    public void testParseErrorJSON() throws Exception {
        read("JSONErrorResponse.txt");
        List<Map<String, String>> result = JSONParser.parse(responseJson);
        assertEquals("020", result.get(0).get(EuPathDBConstants.RESPONSE_KEY_ERROR_CODE));
    }

    /**
     * Run the List<Map<String, String>> parseJSON(String) method test for broken json response.
     *
     * @throws Exception
     */
    @Test
    public void testParseBrokenJSON() throws Exception {
        read("JSONBrokenResponse.txt");
        try {
            JSONParser.parse(responseJson);
            fail("It should have thrown DatabaseServiceException.");
        } catch (DatabaseServiceException e) {
            assertTrue("It should have thrown JsonSyntaxException.", e.getCause() instanceof JsonSyntaxException);
        }
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     */
    @Before
    public void setUp() throws Exception {
        // add additional set up code here
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
     * Launch the test.
     *
     * @param args
     *            the command line arguments
     *
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(JSONParserTest.class);
    }

    /**
     * Reads and return data string from given file.
     *
     * @param Filename the file name
     * @throws IOException
     */
    private void read(String Filename) throws IOException {
        String inputLine;
        StringBuilder jsonString = new StringBuilder();
        reader = new FileReader(getClass().getResource(Filename).getPath());
        bufferedReader = new BufferedReader(reader);

        while ((inputLine = bufferedReader.readLine()) != null) {
            jsonString.append(inputLine);
        }
        responseJson = jsonString.toString();
    }
}