package is.ru.honn.ruber.rest;

import is.ru.honn.ruber.domain.History;
import is.ru.honn.ruber.domain.Trip;
import is.ruframework.http.SimpleHttpRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 * A content reader specific to History
 */
public class HistoryReader extends ContentReader{

	JSONParser parser = new JSONParser();

	/**
	 * Read method for reading a JSON history object
	 * --
	 * @param importURL
	 */
	@Override
	public void read(String importURL){
		try{
			// New history DTO
			History history = new History();
			List<Trip> tripList = new ArrayList<Trip>();

			// Polling an url for JSON trip data
			JSONObject parsedJSON = (JSONObject)parser.parse(SimpleHttpRequest.sendGetRequest(importURL));

			// Setting the history contents
			history.setOffset(Integer.parseInt(parsedJSON.get("offset").toString()));
			history.setCount(Integer.parseInt(parsedJSON.get("count").toString()));
			history.setLimit(Integer.parseInt(parsedJSON.get("limit").toString()));

			// Array of trip
			JSONArray jsonTrips = (JSONArray)parsedJSON.get("history");

			// Iterating through each json object and making a Product instance
			Iterator<JSONObject> it = jsonTrips.iterator();
			while(it.hasNext()){
				JSONObject next = it.next();
				// Getting the Trip in JSON form
				Trip current = JSONTrip.JSONToTrip(next);
				//tripList.add(current);
				handler.processContent(current);
			}

		}catch(IOException ex){
			log.severe("JSONReader: read: " + ex.getMessage());

		}catch (ParseException ex){
			log.severe("JSONReader: read: " + ex.getMessage());
		}
	}
}
