package search.client;

import org.json.JSONArray;

public interface SearchClient {
	public JSONArray searchCity(String restLink,String city) throws Exception;
}
