package is.ru.honn.ruber.domain;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 * POJO class for Trips
 */
public class Trip {

	/**
	 * Enum for trip status
	 */
	public enum TripStatus {
		completed, incompleted
	}

	public Trip(){

	}

	public Trip(int endTime, int startTime, int requestTime, double distance, String productId, String uuId, TripStatus status) {
		this.endTime = endTime;
		this.startTime = startTime;
		this.requestTime = requestTime;
		this.distance = distance;
		this.productId = productId;
		this.uuId = uuId;
		this.status = status;
	}

	private int startTime;
	private int requestTime;
	private double distance;
	private String productId;
	private	int endTime;
	private String uuId;
	private TripStatus status;

	/**
	 * Getters for trip
	 */
	public int getStartTime() {
		return startTime;
	}
	public int getRequestTime() {
		return requestTime;
	}
	public double getDistance() {
		return distance;
	}
	public String getProductId() {
		return productId;
	}
	public int getEndTime() {
		return endTime;
	}
	public String getUuId() {
		return uuId;
	}
	public TripStatus getStatus() {
		return status;
	}

	/**
	 * Setters for Trip
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public void setRequestTime(int requestTime) {
		this.requestTime = requestTime;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public void setStatus(TripStatus status) {
		this.status = status;
	}

	/**
	 * ToString for Trip
	 * @return
	 */
	@Override
	public String toString() {
		return "Trip{" +
				"startTime=" + startTime +
				", requestTime=" + requestTime +
				", distance=" + distance +
				", productId='" + productId + '\'' +
				", endTime=" + endTime +
				", uuId='" + uuId + '\'' +
				", status=" + status +
				'}';
	}
}
