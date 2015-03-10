package com.biomatters.plugins.eupathdb.utils;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>JSONParserTest</code> contains tests for the class
 * <code>{@link JSONParser}</code>.
 * 
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class JSONParserTest {
	/**
	 * Run the List<Map<String, String>> parseJSON(String) method test.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testParseJSON() throws Exception {
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		String inputLine;
		StringBuilder jsonString = new StringBuilder();
		try {
			reader = new FileReader(getClass().getResource(
					"JSONResponseString.txt").getPath());
			bufferedReader = new BufferedReader(reader);

			while ((inputLine = bufferedReader.readLine()) != null) {
				jsonString.append(inputLine);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		List<Map<String, String>> result = JSONParser.parse(jsonString
				.toString());
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 * 
	 * @throws Exception
	 *             if the initialization fails for some reason
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
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
}