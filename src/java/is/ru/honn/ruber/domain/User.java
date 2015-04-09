package is.ru.honn.ruber.domain;

/**
 * Created by Unnar/Davíð on 28.9.2014.
 */
public class User {

	public User(String id, String username, String firstName, String lastName, String password, String email, String picture, String promoCode) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.picture = picture;
		this.promoCode = promoCode;
	}

	/**
	 * User properties
	 */
	private String id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String picture;
	private String promoCode;


	/**
	 * Setters for User
	 */
	public void setId(String id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}


	/**
	 * Getters for User
	 */
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getPicture() {
		return picture;
	}
	public String getPromoCode() {
		return promoCode;
	}


	/**
	 * ToString for User
	 * @return
	 */
	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", picture='" + picture + '\'' +
				", promoCode='" + promoCode + '\'' +
				'}';
	}
}
