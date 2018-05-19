package edu.mum.asd.library.model;

public class Librarian extends User implements LibraryItem{
	
	private UserRole role;
	
	public UserRole getRole() {
		return this.role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}

	public Librarian() {
		super();
		this.role = UserRole.LIBRARIAN;
	}

	public Librarian(String firstName, String lastName, String email, String phone, String password, String address) {
		super(firstName, lastName, email, phone, password, address);
		this.role = UserRole.LIBRARIAN;
	}
	
	
//private int id;
//private String name,email,password;
//private long mobile;
//
//public LibrarianModel() {}
//
//public LibrarianModel(int id, String name, String email, String password, long mobile) {
//	super();
//	this.id = id;
//	this.name = name;
//	this.email = email;
//	this.password = password;
//	this.mobile = mobile;
//}
//public LibrarianModel(String name, String email, String password, long mobile) {
//	super();
//	this.name = name;
//	this.email = email;
//	this.password = password;
//	this.mobile = mobile;
//}
//
//public int getId() {
//	return id;
//}
//public void setId(int id) {
//	this.id = id;
//}
//public String getName() {
//	return name;
//}
//public void setName(String name) {
//	this.name = name;
//}
//public String getEmail() {
//	return email;
//}
//public void setEmail(String email) {
//	this.email = email;
//}
//public String getPassword() {
//	return password;
//}
//public void setPassword(String password) {
//	this.password = password;
//}
//public long getMobile() {
//	return mobile;
//}
//public void setMobile(long mobile) {
//	this.mobile = mobile;
//}

}
