package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Student;

@WebServlet("/ViewStudents")
public class ViewStudents extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Students</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		out.println("<div class='container'>");
		DAOFactory idaofaccotry = new DAOFactory();
		IDAO studentsDao = idaofaccotry.getIDAO("Student");
		List<Student> list = studentsDao.viewStudents();
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>Students</div>");
		out.println("<div class='panel-body'>");
		out.println("<table class='table table-bordered table-striped'>");
		out.println(
				"<tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Phone Number</th><th>Address</th><th>Delete</th></tr>");
		for (Student bean : list) {
			out.println("<tr>" 
					+ "<td>" + bean.getUserId() + "</td>" 
					+ "<td>" + bean.getFirstName() + "</td>" 
					+ "<td>" + bean.getLastName() + "</td>" 
					+ "<td>" + bean.getEmail() + "</td>" 
					+ "<td>" + bean.getPhone()+ "</td>" 
					+ "<td>" + bean.getAddress() + "</td>" 
					+ "<td><a href='DeleteStudent?id=" + bean.getUserId()+ "'>Delete</a></td>"
					+ "</tr>");
		}
//		+ "<td><a href='EditLibrarianForm?id=" + bean.getUserId() + "'>Edit</a></td>" 
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}
}
