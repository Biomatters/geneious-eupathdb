package com.biomatters.plugins.eupathdb.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
	private JSONParser(){
	}

	/**
	 * This method uses <code>JSONCustomMapper</code> to map a JSON response to
	 * custom Java collection.
	 * 
	 * @param jsonString
	 * @return recordList
	 */
	public static List<Map<String, String>> parse(String jsonString) {

		List<Map<String, String>> recordList;
		Gson gson = new GsonBuilder().registerTypeAdapter(List.class,
				new JSONCustomMapper()).create();

		Type type = new TypeToken<List<Map<String, String>>>() {
		}.getType();
		recordList = gson.fromJson(jsonString, type);

		return recordList;
	}

}
