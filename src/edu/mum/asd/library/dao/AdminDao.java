package edu.mum.asd.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.model.Admin;

public class AdminDao {
	public static int save(Admin bean) {
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

	public static int update(Admin bean) {
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
			ps.setString(7, bean.getRole().toString());
			
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<Admin> view() {
		List<Admin> list = new ArrayList<Admin>();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin bean = new Admin();
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

	public static Admin viewById(int id) {
		Admin bean = new Admin();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from user where id=? and role = 'ADMIN' ");
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

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("delete from user where id=?");
			ps.setInt(1, id);
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
			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
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
}
