package goeuro;

import org.json.JSONArray;

import data.converter.DataConverter;
import goeuro.client.GoEuroSearchClient;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String apiLink = "http://api.goeuro.com/api/v2/position/suggest/en";
		if(args.length !=1) {
			System.err.println("Usage : java -jar GoEuroTest.jar CITY_NAME");
		}
		JSONArray ret = new GoEuroSearchClient().searchCity(apiLink,args[0]);
		DataConverter.convertJsonArrayToCSV(ret, "FoundCities.csv");
		System.out.println("Program ended successfully");
	}

}
