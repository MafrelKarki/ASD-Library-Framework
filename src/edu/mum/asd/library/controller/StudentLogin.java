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
import edu.mum.asd.library.model.Student;

@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		System.out.println("inside student login do post method");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Student Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		DAOFactory idaofactory=new DAOFactory();
		IDAO studentFactory=idaofactory.getIDAO("Student");
		
		
		if (studentFactory.authenticate(email, password) > 0) {
//		if(email.equals("student@mail.com")&&password.equals("student")){
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("userid", studentFactory.authenticate(email, password));

			request.getRequestDispatcher("navstudent.html").include(request, response);
			request.getRequestDispatcher("librariancarousel.html").include(request, response);

		} else {
			request.getRequestDispatcher("navhome.html").include(request, response);
			out.println("<div class='container'>");
			out.println("<h3>Username or password error</h3>");
			request.getRequestDispatcher("studentloginform.html").include(request, response);
			out.println("</div>");
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
