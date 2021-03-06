package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.LibraryUserBuilder;
import edu.mum.asd.library.model.UserBuilder;
import edu.mum.asd.library.model.UserDirector;
import edu.mum.asd.library.model.UserVisitor;
import edu.mum.asd.library.model.Visitor;

@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String password = request.getParameter("password");

		UserBuilder userBuilder = new LibraryUserBuilder();
		UserDirector userDirector = new UserDirector(userBuilder);

		userDirector.buildName(firstName, lastName);
		userDirector.buildContact(email, phone, address);
		userDirector.buildPassword(password);
		Librarian bean = (Librarian) userDirector.getUser();

		// Librarian bean = new Librarian(firstName, lastName, email, phone, password, address);
		FacadeSave facedSave = new FacadeSave();
		facedSave.save(bean);

		// Implementing Visitor design pattern for sending email to student after registration
		Visitor visitor = new UserVisitor();
		visitor.visit(bean);

		out.print("<h4>Librarian added successfully</h4>");
		request.getRequestDispatcher("addlibrarianform.html").include(request, response);

		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
