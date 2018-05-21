package edu.mum.asd.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.LibraryUserBuilder;
import edu.mum.asd.library.model.Loan;
import edu.mum.asd.library.model.Payment;
import edu.mum.asd.library.model.Student;
import edu.mum.asd.library.model.UserBuilder;
import edu.mum.asd.library.model.UserDirector;
import edu.mum.asd.library.model.UserRole;

public class LibrarianDao implements IDAO{

	
	public  int save(Librarian bean) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into user(firstname, lastname, email, phone, password, address, role) values(?,?,?,?,?,?,?)");
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPhone());
			ps.setString(5, bean.getPassword());
			ps.setString(6, bean.getAddress());
			ps.setString(7, bean.getRole().toString());
			
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public  int update(Librarian bean,long id) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con
					.prepareStatement("update user set firstname=?, lastname=?, email=?, phone=?, password=?, address=? where userid=?");
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPhone());
			ps.setString(5, bean.getPassword());
			ps.setString(6, bean.getAddress());
			ps.setLong(7, id);
			
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public  List<Librarian> view() {
		List<Librarian> list = new ArrayList<Librarian>();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from user where role = 'LIBRARIAN' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				Librarian bean = new Librarian();
				UserBuilder userBuilder = new LibraryUserBuilder();
				UserDirector userDirector = new UserDirector(userBuilder);
				
				userDirector.buildUserId(rs.getLong("userid"));
				userDirector.buildName(rs.getString("firstname"), rs.getString("lastname"));
				userDirector.buildContact(rs.getString("email"), rs.getString("phone"), rs.getString("address"));
				userDirector.buildPassword(rs.getString("password"));
				Librarian bean = (Librarian) userDirector.getUser();
				
//				bean.setUserId(rs.getLong("userid"));
//				bean.setFirstName(rs.getString("firstname"));
//				bean.setLastName(rs.getString("lastname"));
//				bean.setEmail(rs.getString("email"));
//				bean.setPhone(rs.getString("phone"));
//				bean.setPassword(rs.getString("password"));
//				bean.setAddress(rs.getString("address"));
				list.add(bean);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public  Librarian viewById(int id) {
		Librarian bean = new Librarian();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from user where userid=? and role = 'LIBRARIAN' ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				UserBuilder userBuilder = new LibraryUserBuilder();
				UserDirector userDirector = new UserDirector(userBuilder);
				
				userDirector.buildUserId(rs.getLong("userid"));
				userDirector.buildName(rs.getString("firstname"), rs.getString("lastname"));
				userDirector.buildContact(rs.getString("email"), rs.getString("phone"), rs.getString("address"));
				userDirector.buildPassword(rs.getString("password"));
				bean = (Librarian) userDirector.getUser();
				
				
//				bean.setUserId(rs.getLong(1));
//				bean.setFirstName(rs.getString(2));
//				bean.setLastName(rs.getString(3));
//				bean.setEmail(rs.getString(4));
//				bean.setPhone(rs.getString(5));
//				bean.setPassword(rs.getString(6));
//				bean.setAddress(rs.getString(7));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return bean;
	}

	public  int delete(int id) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("delete from user where userid=? and role = ?");
			ps.setInt(1, id);
			ps.setString(2, UserRole.LIBRARIAN.toString());
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	@Override
	public  boolean authenticate(String email, String password) {
		boolean status = false;
		try {
			
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			passwordEncoder.matches("mafrels", user.getPassword())
			
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=? and role = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, UserRole.LIBRARIAN.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	@Override
	public int save(LibraryItem bean) {
		Librarian librarian=(Librarian)bean;
		return save(librarian);
	}
	@Override
	public int update(LibraryItem bean) {
		Librarian librarian=(Librarian)bean;
		
		return update(librarian);
	}

	@Override
	public List<Librarian> viewLibraians() {
		// TODO Auto-generated method stub
		return view();
	}

	@Override
	public int delete(String callno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIssued(String callno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkIssue(String callno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int issueBook(Loan bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnBook(String callno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Loan> viewIssuedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<Book> viewBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Student bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> viewStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan getLoanedBook(int calno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(String calno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payment> getPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalAmounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLoan(Loan loan) {
		// TODO Auto-generated method stub
		return 0;
	}
}
