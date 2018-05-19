package edu.mum.asd.library.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Mafrel
 * @purpose Implementing Builder Pattern to create the objects of user
 */
public class AdminUserBuilder implements UserBuilder{

	private Admin admin;

	@Override
	public void buildName(String firstName, String lastName) {
		admin.setFirstName(firstName);
		admin.setLastName(lastName);
	}

	@Override
	public void buildContact(String email, String phone, String address) {
		admin.setEmail(email);
		admin.setPhone(phone);
		admin.setAddress(address);
	}

	@Override
	public void buildPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		admin.setPassword(passwordEncoder.encode(password));
	}

	@Override
	public void buildExtra(Librarian approvedBy) {
		admin.setRole(UserRole.ADMIN);
	}

	@Override
	public User getUser() {
		return admin;
	}

	@Override
	public void buildId(long id) {
		admin.setUserId(id);
	}
	

}
