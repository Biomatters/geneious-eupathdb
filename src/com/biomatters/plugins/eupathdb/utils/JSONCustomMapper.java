package com.biomatters.plugins.eupathdb.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * The Class <code>JSONCustomMapper</code> provides a custom deserializer for
 * JSON.
 * 
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class JSONCustomMapper implements
		JsonDeserializer<List<Map<String, String>>> {

	private static final String NAME = "name";
	private static final String VALUE = "value";
	private static final String FIELDS = "fields";
	private static final String RECORDS = "records";
	private static final String RESPONSE = "response";
	private static final String RECORDSET = "recordset";

	/**
	 * Provides a custom implementation to map a JSON response to custom Java
	 * collection.
	 * 
	 * @param jsonElement
	 *            The JSON element containing the JSON response.
	 * @param type
	 *            the type
	 * @param jdc
	 *            the jdc
	 * @return the list
	 * @throws JsonParseException
	 *             the json parse exception
	 */
	@Override
	public List<Map<String, String>> deserialize(JsonElement jsonElement,
			Type type, JsonDeserializationContext jdc)
			throws JsonParseException {

		JsonArray jsonArray = jsonElement.getAsJsonObject().get(RESPONSE)
				.getAsJsonObject().get(RECORDSET).getAsJsonObject()
				.get(RECORDS).getAsJsonArray();

		List<Map<String, String>> records = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		for (JsonElement element : jsonArray) {
			map = new HashMap<String, String>();
			JsonArray array = element.getAsJsonObject().get(FIELDS)
					.getAsJsonArray();
			for (JsonElement field : array) {
				map.put(field.getAsJsonObject().get(NAME).getAsString(), field
						.getAsJsonObject().get(VALUE).getAsString());
			}
			records.add(map);
		}
		return records;
	}

}
