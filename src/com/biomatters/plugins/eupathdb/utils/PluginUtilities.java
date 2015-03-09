package com.biomatters.plugins.eupathdb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;

/**
 * The Class <code>PluginUtilities</code> provides various string manipulation
 * operations and access to the properties file.
 * 
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class PluginUtilities {

	private static final String ENCODING = "UTF-8";
	private static Properties Properties = new Properties();

	/**
	 * Utility class
	 */
	private PluginUtilities() {
	}

	/**
	 * Encode.
	 * 
	 * @param token
	 *            the token
	 * @return the string
	 * @throws DatabaseServiceException
	 *             the database service exception
	 */
	public static String encode(String token) throws DatabaseServiceException {
		try {
			return URLEncoder.encode(token, ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new DatabaseServiceException(e, "Search failed: "
					+ e.getMessage(), false);
		}
	}

	/**
	 * Returns token enclosed in quotes. Quotes that are escaped between are
	 * ignored and the backslash is removed from the resulting tokens.
	 * 
	 * @param str
	 * @return the string
	 */
	public static String deEscape(String str) {
		if (str.length() > 0) {
			StringBuilder token = new StringBuilder(str);
			if (token.charAt(0) == '\"' || token.charAt(0) == '\'') {
				token.deleteCharAt(0);
			}
			if (token.length() > 0 && token.charAt(token.length() - 1) == '\"'
					|| token.charAt(token.length() - 1) == '\'') {
				token.deleteCharAt(token.length() - 1);
			}
			return token.toString().replace("\\\"", "\"");
		}
		return str;
	}

	/**
	 * Load properties.
	 * 
	 * @throws DatabaseServiceException
	 */
	public static void loadProperties() throws DatabaseServiceException {
		InputStream inputStream = PluginUtilities.class
				.getResourceAsStream(EuPathDBConstants.EUPATHDB_PROPERTIES);
		try {
			Properties.load(inputStream);
		} catch (IOException e) {
			throw new DatabaseServiceException(e, "Search failed: "
					+ e.getMessage(), false);
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @param key
	 *            the key
	 * @return the value
	 * @throws DatabaseServiceException
	 */
	public static String getValue(String key) throws DatabaseServiceException {
		if (Properties.isEmpty()) {
			loadProperties();
		}
		return Properties.getProperty(key);
	}
}
