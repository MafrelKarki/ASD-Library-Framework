package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.LibrarianDao;
import edu.mum.asd.library.model.Librarian;

@WebServlet("/ViewLibrarian")
public class ViewLibrarian extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");

		List<Librarian> list=LibrarianDao.view();
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>Librarians</div>");
		out.println("<div class='panel-body'>");
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Phone Number</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
		for(Librarian bean:list){
			out.println("<tr>"
					+ "<td>"+bean.getUserId()+"</td>"
					+ "<td>"+bean.getFirstName()+"</td>"
					+ "<td>"+bean.getLastName()+"</td>"
					+ "<td>"+bean.getEmail()+"</td>"
					+ "<td>"+bean.getPhone()+"</td>"
					+ "<td>"+bean.getAddress()+"</td>"
					+ "<td><a href='EditLibrarianForm?id="+bean.getUserId()+"'>Edit</a></td>"
					+ "<td><a href='DeleteLibrarian?id="+bean.getUserId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}
}
