package edu.mum.asd.library.controller;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;

public class DAOFactory {
public IDAO getIDAO(String type)
{
	
	if (type=="BookDao") {
		return new BookDao();
	}
	return new LibrarianDao();
	
}
}
