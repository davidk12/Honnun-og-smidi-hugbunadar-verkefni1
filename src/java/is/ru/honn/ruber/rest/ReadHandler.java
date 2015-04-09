package is.ru.honn.ruber.rest;

import is.ru.honn.ruber.domain.Trip;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 */
public interface ReadHandler {

	void processContent(Trip newTrip);
}
