package is.ru.honn.ruber.domain;

import java.util.List;
/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 * A DTO class for History
 * Based off of User Activity v1:
 * https://developer.uber.com/v1/endpoints/#user-activity-v1
 */
public class History {


	/**
	 * History properties
	 */
	private int offset;
	private int limit;
	private int count;



	/**
	 * Trips tied to History
	 */

	private List<Trip> trips;

	/**
	 * Getters for History
	 * @return
	 */
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	public int getCount() {
		return count;
	}
	public List<Trip> getTrips() {
		return trips;
	}



	/**
	 * Setters for History
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	/**
	 * ToString for History
	 * @return
	 */
	@Override
	public String toString() {
		return "History{" +
				"offset=" + offset +
				", limit=" + limit +
				", count=" + count +
				", trips=" + trips +
				'}';
	}

}
