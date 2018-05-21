package edu.mum.asd.library.controller;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;

import edu.mum.asd.library.dao.PaymentDao;
import edu.mum.asd.library.dao.ReservationDao;
import edu.mum.asd.library.dao.StudentDao;

/**
 * @author Binyam
 * @purpose implementing factory method for DAO
 */
public class DAOFactory {
	public IDAO getIDAO(String type) {

		if (type == "BookDao") {
			return new BookDao();
		} else if (type == "Student") {
			return new StudentDao();
		} else if (type == "PaymentDao") {
			return new PaymentDao();
		} else if (type == "Reservation") {
			return new ReservationDao();
		}
		return new LibrarianDao();

	}
}
