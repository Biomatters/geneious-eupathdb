package com.biomatters.plugins.eupathdb.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The class <code>EuPathDBUtilitiesTest</code> contains tests for the class
 * <code>{@link EuPathDBUtilities}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class EuPathDBUtilitiesTest {

    /**
     * Run the String getValue(String) method test.
     *
     * @throws Exception
     */
    @Test
    public void testGetValue() throws Exception {
        assertEquals("http://PiroplasmaDB.org/webservices/GeneQuestions",
                EuPathDBUtilities.getValue("PIROPLASMADB.WEB_SERVICE_URI"));
    }
}