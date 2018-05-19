package edu.mum.asd.library.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Mafrel
 * @purpose Implementing Builder Pattern to create the objects of user
 */
public class LibraryUserBuilder implements UserBuilder {

	private Librarian librarian;
	
	
	
	public LibraryUserBuilder() {
		super();
		this.librarian = new Librarian();
	}

	@Override
	public void buildName(String firstName, String lastName) {
		librarian.setFirstName(firstName);
		librarian.setLastName(lastName);
	}

	@Override
	public void buildContact(String email, String phone, String address) {
		librarian.setEmail(email);
		librarian.setPhone(phone);
		librarian.setAddress(address);
	}

	@Override
	public void buildPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		librarian.setPassword(passwordEncoder.encode(password));
	}

	@Override
	public void buildExtra(Librarian approvedBy) {
		// TODO Auto-generated method stub
		librarian.setRole(UserRole.LIBRARIAN);
	}

	@Override
	public User getUser() {
		return librarian;
	}

	@Override
	public void buildId(long id) {
		librarian.setUserId(id);
	}


}
