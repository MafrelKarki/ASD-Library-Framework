package edu.mum.asd.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.dao.LibrarianDao;
import edu.mum.asd.library.model.Librarian;

@WebServlet("/EditLibrarian")
public class EditLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		Librarian bean = new Librarian(firstName, lastName, email, phone, password, address);
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO librarian=idaofaccotry.getIDAO("Librarian");
		librarian.update(bean,id);
		response.sendRedirect("ViewLibrarian");
	}

}
