package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.Loan;

@WebServlet("/ReturnBookForm")
public class ReturnBookForm extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String calno=request.getParameter("calno");
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Return Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);

		out.println("<div class='container'>");
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>Book Loan information</div>");
		out.println("<div class='panel-body'>"); 
		out.println("<div class='row'>");
		out.println("<div class='col-md-6'>");
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th colspan='2'>Student Information</th></tr>");
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO bookdao=idaofaccotry.getIDAO("BookDao");
		Loan loanbook = bookdao.getLoanedBook(calno);
		out.println("<tr><td>Student Names: </td><td>"+loanbook.getStudent().getFirstName()+" "+loanbook.getStudent().getLastName()+"</td></tr>");
		out.println("<tr><td>Address: </td><td>"+loanbook.getStudent().getAddress()+"</td></tr>");
		out.println("<tr><td>E-mail Address: </td><td>"+loanbook.getStudent().getEmail()+"</td></tr>");
		out.println("<tr><td>Phone Number: </td><td>"+loanbook.getStudent().getPhone()+"</td></tr>");
		out.println("</table>");    
		out.println("</div>");
		out.println("<div class='col-md-6'>");
		out.println("<table class='table table-bordered table-striped'>");
		Book book=bookdao.getBook(calno);
		out.println("<tr><th colspan='2'>Book title and Author: "+book.getName()+" / "+book.getAuthor()+"</th></tr>");
		
		out.println("<tr><td>Cal No: </td><td>"+book.getCallno()+"</td></tr>");
		out.println("<tr><td>Issued on: </td><td>"+loanbook.getIssuedDate()+"</td></tr>");
		out.println("<tr><td>Returned on: </td><td>"+loanbook.getReturnDate()+"</td></tr>");
		out.println("</table>");
		out.println("</div>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
