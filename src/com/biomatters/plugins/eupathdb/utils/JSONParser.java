package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * The Class <code>JSONParser</code> provides parsing of JSON response using a
 * custom deserializer <code>JSONCustomMapper</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class JSONParser {

    /**
     * Utility class
     */
    private JSONParser() {
    }

    /**
     * This method uses <code>JSONCustomMapper</code> to map a JSON response to
     * custom Java collection.
     *
     * @param jsonString the json string
     * @return recordList
     * @throws DatabaseServiceException
     */
    public static List<Map<String, String>> parse(String jsonString)
            throws DatabaseServiceException {

        List<Map<String, String>> recordList;
        Gson gson = new GsonBuilder().registerTypeAdapter(List.class,
                new JSONCustomMapper()).create();

        Type type = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        try {
            recordList = gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        } catch (JsonIOException e) {
            throw new DatabaseServiceException(e, e.getMessage(), false);
        }

        return recordList;
    }

}
