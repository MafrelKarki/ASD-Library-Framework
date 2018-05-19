package edu.mum.asd.library.model;


/**
 * @author Mafrel
 * @purpose Implementing Builder Pattern to create the objects of user
 */
public interface UserBuilder {
	public void buildId(long id);
	public void buildName(String firstName, String lastName);
	public void buildContact(String email, String phone, String address);
	public void buildPassword(String password);
	public void buildExtra(Librarian approvedBy);
	public User getUser();
}
