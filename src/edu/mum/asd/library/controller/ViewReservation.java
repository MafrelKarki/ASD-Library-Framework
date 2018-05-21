package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Book;

@WebServlet("/ViewReservation")
public class ViewReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Inside your reservation");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userid");
		
		
		DAOFactory idaoFactory = new DAOFactory();
		IDAO reservation = idaoFactory.getIDAO("Reservation");
		
		
		String callno = reservation.findCallNoByUserId(userId);

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Reserved Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("navstudent.html").include(request, response);
		out.println("<div class='container'>");
//		DAOFactory idaofaccotry = new DAOFactory();
		IDAO bookFactory = idaoFactory.getIDAO("BookDao");
		Book reservedBook = bookFactory.getBook(callno);
		System.out.println(callno);
		System.out.println(reservedBook);
		// List<Librarian> list = librarian.viewLibraians();
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>Your Reserved Book</div>");
		out.println("<div class='panel-body'>");
		out.println("<table class='table table-bordered table-striped'>");
		// out.println(
		// "<tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Phone
		// Number</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
		// for (Librarian bean : list) {
		out.println("<tr>" + "<td>" + reservedBook.getCallno() + "</td>" + "<td>" + reservedBook.getName() + "</td>"
				+ "<td>" + reservedBook.getAuthor() + "</td>" + "<td>" + reservedBook.getPublisher() + "</td>" + "<td>"
				+ reservedBook.getIssued() + "</td>" + "<td>" + reservedBook.getQuantity() + "</td>" + "</tr>");
		// }
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}
}
