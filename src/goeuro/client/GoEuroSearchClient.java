package goeuro.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import search.client.SearchClient;

public class GoEuroSearchClient implements SearchClient{

	@Override
	public JSONArray searchCity(String restLink, String city) throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(restLink).path(city);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.get();
		
		if(response.getStatus() == 200) {
			String responseStr = response.readEntity(String.class);
			if(responseStr == null || responseStr.isEmpty())
				return null;
			JSONArray cities = new JSONArray(responseStr);
			return cities;
		}
		else {
			throw new Exception("Error in the request! response status: "+response.getStatus()+
					" response status info"+response.getStatusInfo().toString());
		}
	}
}
