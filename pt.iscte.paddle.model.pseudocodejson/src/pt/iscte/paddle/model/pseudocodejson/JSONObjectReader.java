package pt.iscte.paddle.model.pseudocodejson;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONObjectReader {

	public static JSONObjectReader from(Object object) throws TransposeException {
		return JSONObjectReader.from(object, "Expected JSON object");
	}

	public static JSONObjectReader from(Object object, String errorMessage) throws TransposeException {
		if (object == null || !(object instanceof JSONObject)) {
			throw new TransposeException(errorMessage);
		}
		return new JSONObjectReader((JSONObject) object);
	}

	private JSONObject json;

	public JSONObjectReader(JSONObject json) {
		this.json = json;
	}

	public String getString(String key) {
		Object value = json.get(key);
		return value != null ? value.toString() : null;
	}

	public String getString(String key, String fallback) {
		String value = getString(key);
		return value != null ? value : fallback;
	}

	public String requireString(String key) throws TransposeException {
		String value = getString(key);
		if (value == null) {
			throw new TransposeException("Missing required value for key: " + key);
		}
		return value;
	}

	public int[] getSemanticVersion(String key) {
		String[] version = getString(key, "").split("\\.");
		if (version.length == 3) {
			try {
				return new int[] { Integer.parseInt(version[0]), Integer.parseInt(version[1]),
						Integer.parseInt(version[2]) };
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public boolean getBoolean(String key, boolean fallback) {
		Object value = json.get(key);
		if (value != null && value instanceof Boolean) {
			return (Boolean) value;
		}
		return fallback;
	}

	public Object getAny(String key) {
		return json.get(key);
	}

	public JSONObjectReader getObject(String key) throws TransposeException {
		return json.get(key) != null ? requireObject(key) : null;
	}

	public JSONObjectReader requireObject(String key) throws TransposeException {
		return JSONObjectReader.from(json.get(key), "Expected JSON object for key: " + key);
	}

	public ArrayList<JSONObjectReader> getArray(String key) throws TransposeException {
		ArrayList<JSONObjectReader> array = new ArrayList<>();
		Object value = json.get(key);
		if (value != null) {
			if (!(value instanceof JSONArray)) {
				throw new TransposeException("Expected array of JSON objects for key: " + key);
			}
			for (Object element : (JSONArray) value) {
				array.add(JSONObjectReader.from(element, "Expected JSON object inside array for key:" + key));
			}
		}
		return array;
	}
}
