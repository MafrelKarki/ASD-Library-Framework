package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.BookIterator;
import edu.mum.asd.library.model.BookStorage;

@WebServlet("/ViewBookInStudent")
public class ViewBookInStudent extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userid");
		
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css'/>");
		out.println("<script src='https://code.jquery.com/jquery-1.12.4.js'></script> ");
		out.println("<script src='https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js'></script>");
		out.println("<script type='text/javascript'> "
				+ "$(document).ready(function(){"
				+ "$('#booksTable').DataTable()"
				+ "});"
				+ "</script>");
		
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navstudent.html").include(request, response);

		out.println("<div class='container'>");
		// DAOFactory idaofaccotry=new DAOFactory();
		// IDAO bookDao=idaofaccotry.getIDAO("BookDao");
		// List<Book> list=bookDao.viewBook();

		
		// implementing iterator pattern to iterate through the books
		DAOFactory idaoFactory = new DAOFactory();
		BookStorage bookStorage = new BookStorage();
		IDAO bookDao = idaoFactory.getIDAO("BookDao");
		IDAO reservationDao = idaoFactory.getIDAO("Reservation");
		for (Book book : bookDao.viewBook()) {
			bookStorage.addBook(book);
		}

		List<Book> list = new ArrayList<>();

		// implementing the iterator's hasNext method
		BookIterator iterator = new BookIterator(bookStorage.getBooks());
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}

		out.println("<table class='table table-bordered table-striped' id='booksTable'>");
		
		out.println(
				"<thead><tr><th>Callno</th><th>Name</th><th>Author</th><th>Publisher</th><th>Quantity</th><th>Issued</th><th>Reserve Book</th></tr></thead>");
		out.println("<tbody>");
		for (Book bean : list) {
			if(reservationDao.checkifUserReserved(userId, bean.getCallno()))
			out.println("<tr>"
					+ "<td>" + bean.getCallno() + "</td>"
					+ "<td>" + bean.getName() + "</td>"
					+ "<td>" + bean.getAuthor()+ "</td>"
					+ "<td>" + bean.getPublisher() + "</td>"
					+ "<td>" + bean.getQuantity() + "</td>"
					+ "<td>"+ bean.getIssued() + "</td>"
					+ "<td>"
								+ "<button class='btn btn-success' id='reserveBook' data-callno="+bean.getCallno()+ " data-userId = "+userId+ ">Reserved</button>"
					+ "</td>"
				+ "</tr>");
			
			else
				out.println("<tr>"
						+ "<td>" + bean.getCallno() + "</td>"
						+ "<td>" + bean.getName() + "</td>"
						+ "<td>" + bean.getAuthor()+ "</td>"
						+ "<td>" + bean.getPublisher() + "</td>"
						+ "<td>" + bean.getQuantity() + "</td>"
						+ "<td>"+ bean.getIssued() + "</td>"
						+ "<td>"
									+ "<button class='btn btn-danger' id='reserveBook' data-callno="+bean.getCallno()+ " data-userId = "+userId+ ">Reserve This</button>"
						+ "</td>"
					+ "</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");

		out.println("</div>");

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
