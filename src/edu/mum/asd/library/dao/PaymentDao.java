package edu.mum.asd.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.mum.asd.library.dbconfiguration.QueryExecutor;
import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.Loan;
import edu.mum.asd.library.model.Payment;
import edu.mum.asd.library.model.Student;

public class PaymentDao implements IDAO {

	@Override
	public int save(Payment payment) {
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "INSERT INTO payments (loanid, amount,paymentmode,payedOn) values(?,?,?,?)";
		qex.insert(query, payment.getLoan().getId(), payment.getAmount(),payment.getPaymentMode(), new Date());
		qex.close();
		return 0;
	}
	
	@Override
	public int updateLoan(Loan loan) {
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "UPDATE loan SET isreturned='YES' WHERE loanid=?";
		qex.insert(query,loan.getId());
		qex.close();
		return 0;
	}

	@Override
	public List<Payment> getPayments() {
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM payments ORDER BY payedon DESC";
		ResultSet rs = qex.getData(query);
		List<Payment> payments = null;
		try {
			while (rs.next()) {
				payments = new ArrayList<Payment>();
				payments.add(new Payment(new BookDao().getLoanedBook(rs.getInt("loanid")),
						rs.getDouble("amount"),rs.getString("paymentmode"),rs.getDate("payedon")));
			}
		} catch (SQLException e) {
			System.out.println("Error>>>" + e.getMessage());
		}
		qex.close();
		return payments;
	}

	@Override
	public double getTotalAmounts() {
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT SUM(amount) FROM payments";
		double totalAmount = 0.0;
		ResultSet rs = qex.getData(query);
		try {
			if (rs.next()) {
				totalAmount = rs.getDouble(1);
			}
		} catch (SQLException e) {
			System.out.println("Error>>>" + e.getMessage());
		}
		qex.close();
		return totalAmount;
	}

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
	public int returnBook(String callno) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book findByCallno(String callno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkifUserReserved(long userid, String callno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findCallNoByUserId(long userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
