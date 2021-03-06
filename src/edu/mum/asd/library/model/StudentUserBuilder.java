package edu.mum.asd.library.model;

/**
 * @author Mafrel
 * @purpose Implementing Builder Pattern to create the objects of user
 */
public class StudentUserBuilder implements UserBuilder {

	private Student student;
	
	public StudentUserBuilder() {
		super();
		this.student = new Student();
	}


	@Override
	public void buildName(String firstName, String lastName) {
		student.setFirstName(firstName);
		student.setLastName(lastName);

	}

	@Override
	public void buildContact(String email, String phone, String address) {
		student.setEmail(email);
		student.setPhone(phone);
		student.setAddress(address);
	}

	@Override
	public void buildPassword(String password) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		student.setPassword(passwordEncoder.encode(password));
		student.setPassword(password);
	}

	@Override
	public void buildExtra(Librarian approvedBy) {
		student.setRole(UserRole.STUDENT);
		student.setApprovedBy(approvedBy);
		student.setEligible(true);
//		System.out.println("inside build extra");
	}

	@Override
	public User getUser() {
		return student;
	}

	@Override
	public void buildId(long id) {
		student.setUserId(id);
	}
	

}
