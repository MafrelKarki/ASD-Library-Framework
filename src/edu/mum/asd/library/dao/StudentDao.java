package edu.mum.asd.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.Loan;
import edu.mum.asd.library.model.Student;
import edu.mum.asd.library.model.UserRole;

public class StudentDao implements IDAO {

	@Override
	public int save(Student bean) {
		int status = 0;
		try {
			Connection con = (Connection) DB.getCon();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"insert into user(firstname, lastname, email, phone, password, address, role, approvedBy, isEligible) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPhone());
			ps.setString(5, bean.getPassword());
			ps.setString(6, bean.getAddress());
			ps.setString(7, bean.getRole().toString());
			ps.setString(8, null);
			ps.setBoolean(9, true);

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	@Override
	public List<Book> viewBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Librarian> viewLibraians() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String callno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("delete from user where userid=? and role = ?");
			ps.setInt(1, id);
			ps.setString(2, UserRole.STUDENT.toString());
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
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
	public int returnBook(String callno, int studentid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Loan> viewIssuedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(LibraryItem bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Librarian bean, long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LibraryItem viewById(int id) {
		return null;
	}

	@Override
	public boolean authenticate(String email, String password) {
		boolean status = false;
		try {

			// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			// passwordEncoder.matches("mafrels", user.getPassword())

			Connection con = (Connection) DB.getCon();
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("select * from user where email=? and password=? and role = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, UserRole.STUDENT.toString());
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> viewStudents() {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = (Connection) DB.getCon();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from user where role = ? ");
			ps.setString(1, UserRole.STUDENT.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student bean = new Student();
				bean.setUserId(rs.getLong("userid"));
				bean.setFirstName(rs.getString("firstname"));
				bean.setLastName(rs.getString("lastname"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setPassword(rs.getString("password"));
				bean.setAddress(rs.getString("address"));
				list.add(bean);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public Student getStudentById(int id) {
		Student bean = new Student();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from user where userid=? and role = 'STUDENT' ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean.setUserId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setPhone(rs.getString(5));
				bean.setPassword(rs.getString(6));
				bean.setAddress(rs.getString(7));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return bean;
	}

	// public static int save(Student bean) {
	// int status = 0;
	// try {
	// Connection con = DB.getCon();
	// PreparedStatement ps = con.prepareStatement(
	// "insert into user(firstname, lastname, email, phone, password, address, role,
	// approvedBy, isEligible) values(?,?,?,?,?,?,?,?,?)");
	// ps.setString(1, bean.getFirstName());
	// ps.setString(2, bean.getLastName());
	// ps.setString(3, bean.getEmail());
	// ps.setString(4, bean.getPhone());
	// ps.setString(5, bean.getPassword());
	// ps.setString(6, bean.getAddress());
	// ps.setString(7, bean.getRole().toString());
	// ps.setString(8, null);
	// ps.setBoolean(9, true);
	//
	// status = ps.executeUpdate();
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	//
	// return status;
	// }
	//
	// public static int update(Student bean) {
	// int status = 0;
	// try {
	// Connection con = DB.getCon();
	// PreparedStatement ps = con.prepareStatement(
	// "update user set firstname=?, lastname=?, email=?, phone=?, password=?,
	// address=? where userid=?");
	// ps.setString(1, bean.getFirstName());
	// ps.setString(2, bean.getLastName());
	// ps.setString(3, bean.getEmail());
	// ps.setString(4, bean.getPhone());
	// ps.setString(5, bean.getPassword());
	// ps.setString(6, bean.getAddress());
	// ps.setString(7, bean.getRole().toString());
	//
	// status = ps.executeUpdate();
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	//
	// return status;
	// }
	//
	// public static List<Student> view() {
	// List<Student> list = new ArrayList<Student>();
	// try {
	// Connection con = DB.getCon();
	// PreparedStatement ps = con.prepareStatement("select * from user");
	// ResultSet rs = ps.executeQuery();
	// while (rs.next()) {
	// Student bean = new Student();
	// bean.setUserId(rs.getLong("userid"));
	// bean.setFirstName(rs.getString("firstname"));
	// bean.setLastName(rs.getString("lastname"));
	// bean.setEmail(rs.getString("email"));
	// bean.setPhone(rs.getString("phone"));
	// bean.setPassword(rs.getString("password"));
	// bean.setAddress(rs.getString("address"));
	// list.add(bean);
	// }
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	//
	// return list;
	// }
	// @Override
	// public LibraryItem viewById(int id) {
	// Student bean = new Student();
	// try {
	// Connection con = DB.getCon();
	// PreparedStatement ps = con.prepareStatement("select * from user where
	// userid=? and role = 'Student' ");
	// ps.setInt(1, id);
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// bean.setUserId(rs.getLong(1));
	// bean.setFirstName(rs.getString(2));
	// bean.setLastName(rs.getString(3));
	// bean.setEmail(rs.getString(4));
	// bean.setPhone(rs.getString(5));
	// bean.setPassword(rs.getString(6));
	// bean.setAddress(rs.getString(7));
	// }
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	//
	// return bean;
	// }
	//
	// public int delete(int id) {
	// int status = 0;
	// try {
	// Connection con = DB.getCon();
	// PreparedStatement ps = con.prepareStatement("delete from e_Student where
	// id=?");
	// ps.setInt(1, id);
	// status = ps.executeUpdate();
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	//
	// return status;
	// }
	//
	// public boolean authenticate(String email, String password) {
	// boolean status = false;
	// try {
	//
	// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	//// passwordEncoder.matches("mafrels", user.getPassword())
	//
	// Connection con = DB.getCon();
	// PreparedStatement ps = con.prepareStatement("select * from user where email=?
	// and password=?");
	// ps.setString(1, email);
	// ps.setString(2, password);
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// status = true;
	// }
	// con.close();
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	//
	// return status;
	// }
	//
	// @Override
	// public int save(LibraryItem bean) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public List<Book> viewBook() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<Librarian> viewLibraians() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public int delete(String callno) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int getIssued(String callno) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public boolean checkIssue(String callno) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	//
	// @Override
	// public int issueBook(Loan bean) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int returnBook(String callno, int studentid) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public List<Loan> viewIssuedBooks() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public int update(LibraryItem bean) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int update(Librarian bean, long id) {
	// // TODO Auto-generated method stub
	// return 0;
	// }

	// @Override
	// public LibraryItem viewById(int id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
