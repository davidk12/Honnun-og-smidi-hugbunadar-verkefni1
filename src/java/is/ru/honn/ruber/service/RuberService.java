package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.Price;
import is.ru.honn.ruber.domain.Product;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.History;

import java.util.List;

/**
 * RuberServiceInterface
 */
public interface RuberService
{
	/**
	 * Provider functions
	 */
	public List<Product> getProducts(double latitude, double longitude) throws ServiceException;
	public List<Price> getPriceEstimates(double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws ServiceException;

	/**
	 * Java API functions
	 */
	public Trip addTrip(Trip newTrip);
	public History getHistory(int offset, int limit, String userId) throws ServiceException;
	public User signup(User user);
	public List<User> getUsers(int offset, int limit) throws ServiceException;
	public User getUser(String userId) throws UserNotFoundException;
}