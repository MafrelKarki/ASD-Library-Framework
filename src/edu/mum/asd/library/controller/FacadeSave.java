package edu.mum.asd.library.controller;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;
import edu.mum.asd.library.dao.StudentDao;
import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.Student;

/**
 * @author Binyam
 * @purpose implementing facade design pattern for Saving Librarian and book
 */
public class FacadeSave {
	private IDAO bookIDAO;
	private IDAO libraryIDAO;
	private IDAO studentIDAO;

	public FacadeSave() {
		bookIDAO = new BookDao();
		libraryIDAO = new LibrarianDao();
		studentIDAO = new StudentDao();
		
	      
	   }

	public int save(Book bean) {
		return bookIDAO.save(bean);
	}

	public int save(Librarian bean) {
		return libraryIDAO.save(bean);
	}

	public int save(Student bean) {
		return studentIDAO.save(bean);
	}

}
