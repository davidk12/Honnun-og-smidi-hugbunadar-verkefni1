package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.*;
import is.ruframework.domain.RuObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RuberServiceStub extends RuObject implements RuberService
{
	/**
	 * Ruber data
	 */
	List<Trip> totalTrips = new ArrayList<Trip>();
	List<User> registeredUsers = new ArrayList<User>();

	@Override
	public List<Product> getProducts(double latitude, double longitude) throws ServiceException
	{
		JSONParser parser = new JSONParser();
		List productList = new ArrayList();

		try
		{
			JSONObject json = (JSONObject) parser.parse(new FileReader("products.json"));
			JSONArray products = (JSONArray) json.get("products");

			for (int i = 0;
			     i < products.size();
			     i++)
			{
				JSONObject jsonProduct = (JSONObject) products.get(i);
				Product product = new Product();
				product.setImage((String) jsonProduct.get("image"));
				product.setProduct_id((String) jsonProduct.get("product_id"));
				product.setDescription((String) jsonProduct.get("description"));
				product.setDisplay_name((String) jsonProduct.get("display_name"));
				product.setCapacity(((Long) jsonProduct.get("capacity")).intValue());
				productList.add(product);
			}
		}
		catch (Exception e)
		{
			String tmp = "Unable to read products.json file.";
			log.severe(tmp);
			throw new ServiceException(tmp, e);
		}

		return productList;
	}


	@Override
	public List<Price> getPriceEstimates(double start_latitude, double start_longitude,
	                                     double end_latitude, double end_longitude) throws ServiceException
	{
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(new Price("08f17084-23fd-4103-aa3e-9b660223934b", "USD", "UberBLACK", 23, 29, 1));
		priceList.add(new Price("9af0174c-8939-4ef6-8e91-1a43a0e7c6f6", "USD", "UberSUV", 36, 44, 1.25));
		priceList.add(new Price("aca52cea-9701-4903-9f34-9a2395253acb", null, "uberTAXI", -1, -1, 1));
		priceList.add(new Price("a27a867a-35f4-4253-8d04-61ae80a40df5", "USD", "uberX", 15, 15, 1));
		return priceList;
	}

	/**
	 * Adds a new trip to the trip list
	 * --
	 * @param newTrip New trip to add
	 * @return
	 */
	public Trip addTrip(Trip newTrip){
		totalTrips.add(newTrip);
		return newTrip;
	}

	/**
	 * Retrieves the history for a specified User
	 * --
	 * @param offset    Offset the list of returned results by this amount. Default is zero.
	 * @param limit     Number of items to retrieve. Default is 5, maximum is 100
	 * @param userId    The ID value of the user to retrieve the trips for
	 * @return
	 */
	public History getHistory(int offset, int limit, String userId) throws ServiceException{

		// Will throw exception if the user does not exist
		getUser(userId);

		// New history object
		History userHistory = new History();
		List<Trip> foundTrips = new ArrayList<Trip>();

		// Going through all trips (wasteful in a real example)
		Iterator<Trip> triperator = totalTrips.iterator();
		while(triperator.hasNext()){
			Trip current = triperator.next();

			// If the trip belongs to the user
			if(current.getUuId() == userId){
				foundTrips.add(current);
			}
		}

		// Limiting the limit to a maximum of 100 entries
		if(limit > 100){
			limit = 100;
		}

		// if offset invalid, it reverts to the default
		if(offset < 0 || offset > foundTrips.size() - 1){
			offset = 0;
		}

		// Cutting the list from the offset
		foundTrips = foundTrips.subList(offset, foundTrips.size());

		// Cutting the list by the limit
		if(limit < foundTrips.size()){
			foundTrips = foundTrips.subList(0, limit);
		}

		// Putting the trips into the History DTO
		userHistory.setTrips(foundTrips);
		// Other properties
		userHistory.setCount(foundTrips.size());
		userHistory.setLimit(limit);
		userHistory.setOffset(offset);

		return userHistory;
	}

	/**
	 * Signs up a new User
	 * Note: This serves as a mere rudimentary example, and should be treated as such
	 * For example, the user ID should be generated in the backend
	 * --
	 * @param user The user to be signed up
	 * @return The signed up user, else throw relevant exception
	 */
	public User signup(User user){
		// create an iterator for the registeredUsers list
		Iterator<User> iter = registeredUsers.iterator();

		boolean usernameFound = false;

		// iterate through all the usernames and check if it exists
		while(iter.hasNext()){
			User u = iter.next();

			// if the username is found, break out of the loop and mark usernameFound as true
			if(u.getUsername() == user.getUsername()){
				usernameFound = true;
				break;
			}
		}

		// throw the exception if the username is found
		if(usernameFound){
			throw new UsernameExistsException();
		}

		// add the user to the list if the username is not found
		registeredUsers.add(user);

		return user;
	}

	/**
	 * Returns a List of registered Users, filtered by specified number there-of
	 * --
	 * @param offset
	 * @param limit
	 * @return
	 * @throws ServiceException
	 */
	public List<User> getUsers(int offset, int limit) throws ServiceException{

		// if the offset is invalid in regards to user size
		if(offset <= 0 || offset > registeredUsers.size() - 1){
			//offset = 0;
			throw new ServiceException("Request offset is invalid!");
		}

		// Setting the default value for limit should it fall outside the allowed range
		if(limit > registeredUsers.size() || limit > 100 || limit <= 0){
			//limit = 5;
			throw new ServiceException("Requested limit is invalid!");
		}

		// if the limit overflows
		if(offset + limit > registeredUsers.size() - 1){
			limit = registeredUsers.size() - limit;
		}

		// Returning
		return registeredUsers.subList(offset, limit);
	}

	/**
	 * Retrieves a User by a specified ID value, null if not found
	 * --
	 * @param userId    The ID value of the User
	 * @return
	 */
	public User getUser(String userId) throws UserNotFoundException{

		// Iterating through the registered Users
		Iterator<User> userIt = registeredUsers.iterator();
		while(userIt.hasNext()){
			User current = userIt.next();

			// If the user's ID is found (note: string comparison, wouldn't scale well in a real example)
			if(current.getId() == userId){
				return current;
			}
		}

		// End case, user not found, throw exception
		throw new UserNotFoundException();
	}
}
