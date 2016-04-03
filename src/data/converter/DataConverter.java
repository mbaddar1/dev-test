package data.converter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DataConverter {
	public static void convertJsonArrayToCSV(JSONArray jsonArr, String csvPath) {
		try {
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
			FileWriter fileWriter = new FileWriter(csvPath);
			CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			String[] colHeaders = {"_id", "name", "type", "latitude", "longitude"};
			csvFilePrinter.printRecord(colHeaders);
			if(jsonArr != null)
			{
				for(int i=0;i<jsonArr.length();i++) {
					JSONObject cityJsonObj = jsonArr.getJSONObject(i);
					ArrayList<String> tmp = new ArrayList<String>();
					tmp.add(String.valueOf(cityJsonObj.getLong("_id")));
					tmp.add(cityJsonObj.getString("name"));
					tmp.add(cityJsonObj.getString("type"));
					JSONObject geoPositionJsonObj = cityJsonObj.getJSONObject("geo_position");
					tmp.add(String.valueOf(geoPositionJsonObj.getLong("latitude")));
					tmp.add(String.valueOf(geoPositionJsonObj.getLong("longitude")));
					csvFilePrinter.printRecord(tmp);
				}
				csvFilePrinter.flush();
				csvFilePrinter.close();
			}
			else throw new NullPointerException("Null Json Array");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
