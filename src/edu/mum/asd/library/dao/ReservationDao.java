package edu.mum.asd.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.Loan;
import edu.mum.asd.library.model.Payment;
import edu.mum.asd.library.model.Student;

public class ReservationDao implements IDAO {

	@Override
	public int save(LibraryItem bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Student bean) {
		// TODO Auto-generated method stub
		return 0;
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
	public List<Loan> viewIssuedBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(String calno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> viewStudents() {
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
		// TODO Auto-generated method stub
		return 0;
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
	public LibraryItem viewById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reserveBook(long studentId, String callno) {
		Book book = this.findByCallno(callno);
		if(book.getQuantity()-book.getIssued() >= 1 && this.checkifUserReserved(studentId, callno) == false)
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("insert into reservation(studentid, book_callno) values(?,?)");
			ps.setLong(1, studentId);
			ps.setString(2, callno);

			ps.executeUpdate();
			this.updateBook(book);
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("inside reserve book method");

	}

	@Override
	public Book findByCallno(String callno) {
		Book book = new Book();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from e_book where CALLNO=?");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				book.setCallno(callno);
				book.setName(rs.getString("NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setPublisher(rs.getString("PUBLISHER"));
				book.setQuantity(rs.getInt("QUANTITY"));
				book.setIssued(rs.getInt("ISSUED"));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("inside findByCallno method");
		
		return book;

	}

	@Override
	public boolean checkifUserReserved(long id, String callno) {
		boolean reserved = false;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from reservation where studentid=? and book_callno = ?");
			ps.setLong(1, id);
			ps.setString(2, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				reserved = true;
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("inside checkifUserReserved method");
		return reserved;
	}

	@Override
	public void updateBook(Book book) {
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("update e_book set QUANTITY=?, ISSUED=? where callno=?");
			ps.setInt(1, book.getQuantity()-1);
			ps.setInt(2, book.getIssued()+1);
			
			ps.setString(3, book.getCallno());

			ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("inside updateBook method");

	}

	@Override
	public String findCallNoByUserId(long userid) {
		String callno = new String();
		try {
			Connection con = (Connection) DB.getCon();
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("select * from reservation where studentid = ?");
			ps.setLong(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				callno = rs.getString("book_callno");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return callno;
	}

	@Override
	public int save(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Loan getLoanedBook(int id) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int returnBook(String callno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
