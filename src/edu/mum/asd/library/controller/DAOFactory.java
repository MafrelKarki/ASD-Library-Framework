package edu.mum.asd.library.controller;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;

/**
 * @author Binyam
 * @purpose implementing factory method for DAO
 */
public class DAOFactory {
	public IDAO getIDAO(String type) {

		if (type == "BookDao") {
			return new BookDao();
		}
		return new LibrarianDao();

	}
}
