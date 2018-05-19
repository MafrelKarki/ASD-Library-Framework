package edu.mum.asd.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.LibraryUserBuilder;
import edu.mum.asd.library.model.UserBuilder;
import edu.mum.asd.library.model.UserDirector;
import edu.mum.asd.library.model.UserRole;

public class LibrarianDao implements IDAO{

	
	
	public static int save(Librarian bean) {
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

	public static int update(Librarian bean,long id) {
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

	public static List<Librarian> view() {
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

	public static Librarian viewById(int id) {
		Librarian bean = null;
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

	public static int delete(int id) {
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

	public static boolean authenticate(String email, String password) {
		boolean status = false;
		try {
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
}
