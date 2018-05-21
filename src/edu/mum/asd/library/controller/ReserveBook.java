package edu.mum.asd.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.asd.library.dao.IDAO;

@WebServlet("/ReserveBook")
public class ReserveBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Inside reserve book");
		String callno = request.getParameter("callno");
		String userid = request.getParameter("userid");
		long studentId = Integer.parseInt(userid);
		
		DAOFactory idaofaccotry = new DAOFactory();
		IDAO reservation = idaofaccotry.getIDAO("Reservation");
		reservation.reserveBook(studentId, callno);
		
//		System.out.println("CallNo is-> "+callno +"\n Userid is-> "+userid);
//		String sid = request.getParameter("id");
//		int id = Integer.parseInt(sid);
//		DAOFactory idaofaccotry = new DAOFactory();
//		IDAO librarian = idaofaccotry.getIDAO("Librarian");
//		librarian.delete(id);
//		response.sendRedirect("ViewLibrarian");
	}
}
