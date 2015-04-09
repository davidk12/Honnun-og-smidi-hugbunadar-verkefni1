package is.ru.honn.ruber.rest;

import is.ru.honn.ruber.domain.Trip;
import org.json.simple.JSONObject;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 * Manages Trip objects
 */
public class JSONTrip {

	/**
	 * Returns a Trip in JSON form in a POJO object
	 * --
	 * @param tripJSON
	 * @return
	 */
	public static Trip JSONToTrip(JSONObject tripJSON){

		Trip newTrip = new Trip();

		// Mapping the JSON properties to Trip object
		newTrip.setStartTime(Integer.parseInt(tripJSON.get("start_time").toString()));
		newTrip.setRequestTime(Integer.parseInt(tripJSON.get("request_time").toString()));
		newTrip.setDistance(Double.parseDouble(tripJSON.get("distance").toString()));
		newTrip.setProductId(tripJSON.get("product_id").toString());
		newTrip.setEndTime(Integer.parseInt(tripJSON.get("end_time").toString()));
		newTrip.setUuId(tripJSON.get("uuid").toString());
		newTrip.setStatus((Trip.TripStatus.valueOf(tripJSON.get("status").toString())));
		return newTrip;
	}
}
