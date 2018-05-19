package edu.mum.asd.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.Loan;
import edu.mum.asd.library.model.Student;

public class BookDao implements IDAO {

	public static int save(Book bean) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("insert into e_book values(?,?,?,?,?,?)");
			ps.setString(1, bean.getCallno());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getAuthor());
			ps.setString(4, bean.getPublisher());
			ps.setInt(5, bean.getQuantity());
			ps.setInt(6, 0);
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<Book> view() {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from e_book");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book bean = new Book();
				bean.setCallno(rs.getString("callno"));
				bean.setName(rs.getString("name"));
				bean.setAuthor(rs.getString("author"));
				bean.setPublisher(rs.getString("publisher"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setIssued(rs.getInt("issued"));

				list.add(bean);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public static int delete(String callno) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("delete from e_book where callno=?");
			ps.setString(1, callno);
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static int getIssued(String callno) {
		int issued = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from e_book where callno=?");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				issued = rs.getInt("issued");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return issued;
	}

	public static boolean checkIssue(String callno) {
		boolean status = false;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from e_book where callno=? and quantity>issued");
			ps.setString(1, callno);
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

	public static int issueBook(Loan bean) {
		System.out.println("inside issue book method");
		System.out.println("loan details");

		System.out.println("--------------------------------------------");
		System.out.println(bean);
		System.out.println("---------------------------------------------");

		String callno = bean.getCallNo();
		boolean checkstatus = checkIssue(callno);
		// System.out.println("Check status: " + checkstatus);
		if (checkstatus) {
			int status = 0;
			try {
				Connection con = DB.getCon();
				PreparedStatement ps = con.prepareStatement(
						"insert into loan(callno, studentid, issuedate, returndate, isreturned) values(?, ?, ?, ?, ?)");
				// System.out.println("after prepared statement");
				ps.setString(1, bean.getCallNo());
				ps.setLong(2, bean.getStudent().getUserId());

				ps.setDate(3, bean.getIssuedDate());
				ps.setDate(4, bean.getReturnDate());
				ps.setString(5, bean.getReturnStatus());
				// java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				// ps.setDate(5, currentDate);
				// ps.setString(6, "no");

				status = ps.executeUpdate();
				if (status > 0) {
					// System.out.println("inside if");
					PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
					ps2.setInt(1, getIssued(callno) + 1);
					ps2.setString(2, callno);
					status = ps2.executeUpdate();
					// System.out.println("status: " + status);
				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			return status;
		} else {
			return 0;
		}
	}

	public static int returnBook(String callno, int studentid) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con
					.prepareStatement("update loan set isreturned='yes' where callno=? and studentid=?");
			ps.setString(1, callno);
			ps.setInt(2, studentid);

			status = ps.executeUpdate();
			if (status > 0) {
				PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
				ps2.setInt(1, getIssued(callno) - 1);
				ps2.setString(2, callno);
				status = ps2.executeUpdate();
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<Loan> viewIssuedBooks() {
		List<Loan> list = new ArrayList<Loan>();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from loan order by issuedate desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Loan bean = new Loan();
				bean.setCallNo(rs.getString("callno"));
				bean.setIssuedDate(rs.getDate("issuedate"));
				bean.setReturnDate(rs.getDate("returnDate"));
				bean.setReturnStatus(rs.getString("isreturned"));
				Student student = StudentDao.viewById((int) rs.getLong("studentid"));

				// needs to be added after implementing find student by id method in dao
				bean.setStudent(student);

				list.add(bean);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	@Override
	public int save(LibraryItem bean) {
		Book bookModel = (Book) bean;
		return save(bookModel);
	}
}
