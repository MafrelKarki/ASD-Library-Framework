package edu.mum.asd.library.dao;

import edu.mum.asd.library.model.LibraryItem;

public interface IDAO {
	public int save(LibraryItem bean);
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
