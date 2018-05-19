package edu.mum.asd.library.model;

/**
 * @author Mafrel
 * @purpose Implementing Builder Pattern to create the objects of user
 */
public class UserDirector {

	private UserBuilder userBuilder = null;
	
	public UserDirector(UserBuilder userBuilder) {
		this.userBuilder = userBuilder;
	}
	
	public void buildName(String firstName, String lastName) {
		userBuilder.buildName(firstName, lastName);
	}

	public void buildContact(String email, String phone, String address) {
		userBuilder.buildContact(email, phone, address);
	}
	
	public void buildPassword(String password) {
		userBuilder.buildPassword(password);
	}
	
	public void buildExtra(Librarian approvedBy) {
		userBuilder.buildExtra(approvedBy);
	}
	
	public void buildUserId(long id) {
		userBuilder.buildId(id);
	}
	
	public User getUser() {
		return userBuilder.getUser();
	}
}
