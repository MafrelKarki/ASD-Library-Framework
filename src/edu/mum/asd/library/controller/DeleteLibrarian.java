package edu.mum.asd.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;

@WebServlet("/DeleteLibrarian")
public class DeleteLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO librarian=idaofaccotry.getIDAO("Librarian");
		librarian.delete(id);
		response.sendRedirect("ViewLibrarian");
	}
}
