package edu.mum.asd.library.controller;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;
import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;


/**
 * @author Binyam
 * @purpose implementing factory method for DAO
 */
public class FacadeSave {
	private IDAO bookIDAO;
	private IDAO libraryIDAO;
	
	public FacadeSave() {
		bookIDAO = new BookDao();
		libraryIDAO = new LibrarianDao();
	      
	   }
	public int save(Book bean)
	{
		return bookIDAO.save(bean);
	}
	public int save(Librarian bean)
	{
		return libraryIDAO.save(bean);
	}
	
	
}
