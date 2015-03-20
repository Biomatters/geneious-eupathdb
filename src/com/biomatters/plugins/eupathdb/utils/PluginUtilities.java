package com.biomatters.plugins.eupathdb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

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
     * @param token the token
     * @return the string
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encode(String token) throws UnsupportedEncodingException {
        return URLEncoder.encode(token, ENCODING);
    }

    /**
     * Returns token enclosed in quotes. Quotes that are escaped between are
     * ignored and the backslash is removed from the resulting tokens.
     *
     * @param str the string
     * @return the string
     */
    public static String deEscape(String str) {
        if (str != null && str.length() > 0) {
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
     * @throws java.io.IOException
     */
    public static void loadProperties() throws IOException {
        InputStream inputStream = PluginUtilities.class
                .getResourceAsStream(EuPathDBConstants.EUPATHDB_PROPERTIES);
        Properties.load(inputStream);
    }

    /**
     * Gets the value.
     *
     * @param key the key
     * @return the value
     * @throws java.io.IOException
     */
    public static String getValue(String key) throws IOException {
        if (Properties.isEmpty()) {
            loadProperties();
        }
        return Properties.getProperty(key);
    }
}
