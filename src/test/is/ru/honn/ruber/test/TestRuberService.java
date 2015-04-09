package is.ru.honn.ruber.test;

import is.ru.honn.ruber.domain.History;
import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.service.RuberService;
import is.ru.honn.ruber.service.ServiceException;
import is.ru.honn.ruber.service.UserNotFoundException;
import is.ru.honn.ruber.service.UsernameExistsException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.Service;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class TestRuberService extends TestCase
{
	Logger log = Logger.getLogger(TestRuberService.class.getName());

	/**
	 * Test instances
	 */
	User testUser;
	Trip testTrip;


	@Autowired
	private RuberService service;

	@Rule
	public ExpectedException thrown = ExpectedException.none();


	@Before
	public void setUp() throws Exception
	{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("app.xml");
		service = (RuberService)ctx.getBean("RuberService");
		testUser = new User(java.util.UUID.randomUUID().toString(), "testUser", "testFirstName", "testLastName", "testPassword", "testEmail", "testPicture", "testPromoCode");
		testTrip = new Trip(1, 1, 1, 2.0, java.util.UUID.randomUUID().toString(), testUser.getId(), Trip.TripStatus.completed);
	}


	/**
	 * Testing basic signup of users
	 * Testing RuberService.signup
	 */
	@Test
	public void TestUserSignupSuccess(){
		User newUser = service.signup(testUser);

		// Should return a User object
		assertNotNull(newUser);
	}

	/**
	 * Testing the Exception handling in
	 * Testing RuberService.signup
	 * --
	 * @throws UsernameExistsException
	 */
	@Test
	public void TestUserSignupException() throws UsernameExistsException{

		// Expecting a Username exception
		thrown.expect(UsernameExistsException.class);

		// Double signup of same user, should throw Exception
		service.signup(testUser);
		service.signup(testUser);
	}


	/**
	 * Asserting that the Exception handling in getUser does what it should
	 * Testing RuberService.getUser
	 * --
	 * @throws UserNotFoundException
	 */
	@Test
	public void TestGetUserxception() throws UserNotFoundException{

		// Excepting a not found exception
		thrown.expect(UserNotFoundException.class);

		// Attempting to fetch User by ID
		User foundUser = service.getUser("random-id-that-does-not-exist");
	}

	/**
	 * Asserting that getUser does what it should when run successfully
	 * --
	 * Testing RuberService.getUser
	 */
	@Test
	public void TestGetUserSuccess(){

		// Signing up the user so he exists
		service.signup(testUser);

		// Calling the service function
		User foundUser = service.getUser(testUser.getId());

		// Asserting that the user wasn't null
		assertNotNull(foundUser);
	}

	/**
	 * Testing adding a new trip
	 * Testing RuberService.addTrip
	 */
	@Test
	public void AddTrip() {

		Trip addedTrip = service.addTrip(testTrip);

		assertEquals(testTrip.getUuId(), addedTrip.getUuId());
	}
	//endregion

	/**
	 * Testing whether fetching the History for a user
	 * Testing RuberService.getHistory
	 */
	@Test
	public void TestGetHistorySuccess() throws ServiceException{


		// Signing the user up
		service.signup(testUser);

		// Adding a new trip tied to the user
		testTrip.setUuId(testUser.getId());
		Trip addedTrip = service.addTrip(testTrip);

		// Fetching the user history
		History history = service.getHistory(0, 100, testUser.getId());

		// Asserting that the trip length is 1
		assertEquals(history.getTrips().size(), 1);

	}

	/**
	 * Testing whether
	 * Testing RuberService.getHistory
	 * --
	 * @throws UserNotFoundException
	 *
	 */
	@Test
	public void TestGetHistoryException() throws ServiceException{

		// Excepting a not found exception
		thrown.expect(UserNotFoundException.class);

		// User doesn't exist, should throw exception
		History history = service.getHistory(0,100, "some-random-id");
	}

}