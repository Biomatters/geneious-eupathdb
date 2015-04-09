package com.biomatters.plugins.eupathdb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class <code>EuPathDBUtilities</code> provides various string manipulation
 * operations and access to the properties file.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class EuPathDBUtilities {

    private static final Properties PROPERTIES = new Properties();

    /**
     * Utility class
     */
    private EuPathDBUtilities() {
    }

    /**
     * Load properties.
     *
     * @throws java.io.IOException
     */
    private static void loadProperties() throws IOException {
        InputStream inputStream = EuPathDBUtilities.class
                .getResourceAsStream(EuPathDBConstants.EUPATHDB_PROPERTIES);
        PROPERTIES.load(inputStream);
    }

    /**
     * Gets the value.
     *
     * @param key the key
     * @return the value
     * @throws java.io.IOException
     */
    public static String getValue(String key) throws IOException {
        synchronized (PROPERTIES) {
            if (PROPERTIES.isEmpty()) {
                loadProperties();
            }
        }
        return PROPERTIES.getProperty(key);
    }
}
