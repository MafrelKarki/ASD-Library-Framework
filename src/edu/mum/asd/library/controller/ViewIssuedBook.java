package edu.mum.asd.library.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.model.Loan;
@WebServlet("/ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Issued Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");
		
		List<Loan> list=BookDao.viewIssuedBooks();
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>Librarians</div>");
		out.println("<div class='panel-body'>");
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>Callno</th><th>Student Id</th><th>Student Name</th><th>Student Mobile</th><th>Issued Date</th><th>Return Status</th></tr>");
		for(Loan bean:list){
			out.println("<tr><td>"+bean.getCallNo()+"</td><td>"+bean.getStudent().getUserId()+"</td><td>"+bean.getStudent().getFirstName() +" "+bean.getStudent().getLastName()+"</td><td>"+bean.getStudent().getPhone()+"</td><td>"+bean.getIssuedDate()+"</td><td>"+bean.getReturnStatus()+"</td></tr>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
