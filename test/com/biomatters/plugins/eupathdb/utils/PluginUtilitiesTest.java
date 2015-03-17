package com.biomatters.plugins.eupathdb.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The class <code>PluginUtilitiesTest</code> contains tests for the class
 * <code>{@link PluginUtilities}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PluginUtilitiesTest {

    /**
     * Run the String encode(String) method test for reserved characters.
     *
     * @throws Exception
     */
    @Test
    public void testEncodeReservedChars() throws Exception {
        String val = PluginUtilities.encode("test !'();:@&=+$,/?%#[]~^<>\\\"");
        assertEquals("test+%21%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%25%23%5B%5D%7E%5E%3C%3E%5C%22", val);
    }

    /**
     * Run the String encode(String) method test for unreserved characters.
     *
     * @throws Exception
     */
    @Test
    public void testEncodeUnreservedChars() throws Exception {
        String testString = "* - _ .";
        String val = PluginUtilities.encode(testString);
        assertEquals(testString, val);
    }

    /**
     * Run the String deEscape(String) method test.
     *
     * @throws Exception
     */
    @Test
    public void testDeEscape() throws Exception {
        String testString = "'test \"data\" string'";
        assertEquals("test \"data\" string", PluginUtilities.deEscape(testString));

        testString = "\"test 'data' string\"";
        assertEquals("test 'data' string", PluginUtilities.deEscape(testString));
    }

    /**
     * Run the String getValue(String) method test.
     *
     * @throws Exception
     */
    @Test
    public void testGetValue() throws Exception {
        assertEquals("http://PiroplasmaDB.org/webservices/GeneQuestions",
                PluginUtilities.getValue("PIROPLASMADB.WEB_SERVICE_PREFIX_URL"));
    }
}