package edu.mum.asd.library.dao;

import java.util.List;

import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryItem;
import edu.mum.asd.library.model.Loan;

public interface IDAO {
	public int save(LibraryItem bean);
	public List<Book> viewBook(); 
	public List<Librarian> viewLibraians();
	public int delete(String callno);
	public  int delete(int id);
	public int getIssued(String callno);
	public boolean checkIssue(String callno);
	public int issueBook(Loan  bean);
	public int returnBook(String callno,int studentid);
	public  List<Loan> viewIssuedBooks();
	public  int update(LibraryItem bean);
	public  int update(Librarian bean,long id);
	public  LibraryItem viewById(int id);
	public boolean authenticate(String email,String password);
	/*public List<BookModel> viewBook();
	public int delete(String callno);
	public int getIssued(String callno);
	public boolean checkIssue(String callno);
	public int issueBook(IssueBookModel bean);
	public int returnBook(String callno,int studentid);
	public  List<IssueBookModel> viewIssuedBooks();
	public  int save(LibrarianModel bean);
	public  int update(LibrarianModel bean);
	public List<LibrarianModel> viewLibrary();
	public  LibrarianModel viewById(int id);
	public  int delete(int id);
	public boolean authenticate(String email,String password);
	List<BookModel> view();*/
	
}
