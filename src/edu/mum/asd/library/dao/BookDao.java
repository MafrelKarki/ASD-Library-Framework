package edu.mum.asd.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.asd.library.dbconfiguration.QueryExecutor;
import edu.mum.asd.library.model.BookModel;
import edu.mum.asd.library.model.IssueBookModel;

public class BookDao {

	public static int save(BookModel book) {
		int status = 0;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "INSERT INTO e_book VALUES(?,?,?,?,?,?)";
		qex.insert(query, book.getCallno(), book.getName(), book.getAuthor(), book.getPublisher(), book.getQuantity(),
				0);
		qex.close();
		return status;
	}

	public static List<BookModel> view() {
		List<BookModel> list = new ArrayList<BookModel>();
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM e_book";
		try {
			ResultSet rs = qex.getData(query);
			while (rs.next()) {
				list.add(new BookModel(rs.getString("callno"), rs.getString("name"), rs.getString("author"),
						rs.getString("publisher"), rs.getInt("quantity"), rs.getInt("issued")));
			}
			qex.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static int delete(String callno) {
		int status = 0;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "DELETE FROM e_book WHERE callno=?";
		qex.insert(query, callno);
		qex.close();
		return status;
	}

	public static int getIssued(String callno) {
		int issued = 0;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT issued FROM e_book WHERE callno=?";
		try {
			ResultSet rs = qex.getData(query, callno);
			if (rs.next()) {
				issued = rs.getInt("issued");
			}
			qex.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return issued;
	}

	public static boolean checkIssue(String callno) {
		boolean status = false;
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM e_book WHERE callno=? AND quantity>issued";
		try {
			ResultSet rs = qex.getData(query, callno);
			if (rs.next()) {
				status = true;
			}
			qex.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}

	public static int issueBook(IssueBookModel issuedBook) {
		if (checkIssue(issuedBook.getCallno())) {
			int status = 1;
			QueryExecutor qex = QueryExecutor.getInstance();
			String query = "INSERT INTO e_issuebook VALUES (?,?,?,?,?,?)";
			qex.insert(query, issuedBook.getCallno(), issuedBook.getStudentid(), issuedBook.getStudentname(),
					issuedBook.getStudentmobile(), new java.util.Date(System.currentTimeMillis()), "NO");
			try {
				if (status > 0) {
					query = "UPDATE e_book SET issued=? WHERE callno=?";
					qex.update(query, getIssued(issuedBook.getCallno()) + 1, issuedBook.getCallno());
				}
				qex.close();
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
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "UPDATE e_issuebook SET returnstatus='YES' WHERE callno=? AND studentid=?";
		try {
			qex.update(query, callno, studentid);
			status = 1;
			if (status > 0) {
				query = "UPDATE e_book SET issued=? WHERE callno=?";
				qex.update(query, getIssued(callno) - 1, callno);
			}
			qex.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<IssueBookModel> viewIssuedBooks() {
		List<IssueBookModel> list = new ArrayList<IssueBookModel>();
		QueryExecutor qex = QueryExecutor.getInstance();
		String query = "SELECT * FROM e_issuebook ORDER BY issueddate desc";
		try {
			ResultSet rs = qex.getData(query);
			while (rs.next()) {
				list.add(new IssueBookModel(rs.getString("callno"),rs.getString("studentid"),rs.getString("studentname"), rs.getLong("studentmobile"),
						rs.getDate("issueddate"),rs.getString("returnstatus")));
			}
			qex.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
