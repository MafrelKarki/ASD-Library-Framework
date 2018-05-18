package edu.mum.asd.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.dbconfiguration.QueryExecutor;
import edu.mum.asd.library.model.LibrarianModel;

public class LibrarianDao {

	public static int save(LibrarianModel librarian) {
		int status = 0;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "INSERT INTO e_librarian(name,email,password,mobile) VALUES(?,?,?,?)";
		qex.insert(query, librarian.getName(), librarian.getEmail(), librarian.getPassword(), librarian.getMobile());
		qex.close();
		return status;
	}

	public static int update(LibrarianModel librarian) {
		int status = 0;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "UPDATE e_librarian SET name=?,email=?,password=?,mobile=? WHERE id=?";
		qex.insert(query, librarian.getName(), librarian.getEmail(), librarian.getPassword(), librarian.getMobile(),
				librarian.getId());
		qex.close();
		return status;
	}

	public static int delete(int id) {
		int status = 0;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "DELETE FROM e_librarian WHERE id=?";
		qex.insert(query, id);
		qex.close();
		return status;
	}

	public static boolean authenticate(String email, String password) {
		boolean status = false;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM e_librarian WHERE email=? and password=?";
		try {
			ResultSet rs = qex.getData(query, email, password);
			if (rs.next()) {
				status = true;
			}
			qex.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static List<LibrarianModel> view() {
		List<LibrarianModel> list = new ArrayList<>();
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM e_librarian";
		try {
			ResultSet rs = qex.getData(query);
			while (rs.next()) {
				list.add(new LibrarianModel(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"), rs.getLong("mobile")));
			}
			qex.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static LibrarianModel viewById(int id) {
		LibrarianModel librarian = null;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM e_librarian";
		try {
			ResultSet rs = qex.getData(query, id);
			if (rs.next()) {
				librarian = new LibrarianModel(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"), rs.getLong("mobile"));
			}
			qex.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return librarian;
	}

}
