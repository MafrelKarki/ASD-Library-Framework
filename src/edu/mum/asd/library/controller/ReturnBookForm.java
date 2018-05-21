package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
		DAOFactory idaofaccotry = new DAOFactory();
		IDAO bookdao = idaofaccotry.getIDAO("BookDao");
		int id=Integer.parseInt(request.getParameter("id"));
		Loan loanbook = bookdao.getLoanedBook(id);
		out.println("<tr><td>Student Names: </td><td>" + loanbook.getStudent().getFirstName() + " "
				+ loanbook.getStudent().getLastName() + "</td></tr>");
		out.println("<tr><td>Address: </td><td>" + loanbook.getStudent().getAddress() + "</td></tr>");
		out.println("<tr><td>E-mail Address: </td><td>" + loanbook.getStudent().getEmail() + "</td></tr>");
		out.println("<tr><td>Phone Number: </td><td>" + loanbook.getStudent().getPhone() + "</td></tr>");
		out.println("</table>");
		out.println("</div>");
		out.println("<div class='col-md-6'>");
		out.println("<table class='table table-bordered table-striped'>");
		Book book = bookdao.getBook(loanbook.getCallNo());
		out.println("<tr><th colspan='2'>Book title and Author: " + book.getName() + " / " + book.getAuthor()
				+ "</th></tr>");

		out.println("<tr><td>Cal No: </td><td>" + book.getCallno() + "</td></tr>");
		out.println("<tr><td>Issued on: </td><td>" + loanbook.getIssuedDate() + "</td></tr>");
		out.println("<tr><td>Expected Returned Date: </td><td>" + loanbook.getReturnDate() + "</td></tr>");
		if ((loanbook.getReturnDate().getTime() + 86400000) < new Date().getTime()) {
			out.println(
					"<tr><th colspan='2' class='alert alert-danger' style='text-align:center'>The student needs to Pay a Fine</th></tr>");
			out.println("</table>");
			out.println("</div>");
			out.println("</div>");
			out.println(
					"<div class='panel-footer' style='text-align:center'><button type='button' class='btn btn-success' data-toggle='modal' data-target='#cashModal"+loanbook.getId()+"'>CASH PAYMENT</button> <button type='button' class='btn btn-success' data-toggle='modal' data-target='#cardPayment"+loanbook.getId()+"'>CARD PAYMENT</button></div>"
					+ "");
			out.println(
					"<!-- Modal -->\r\n" + 
					"<div class='modal fade' id='cardPayment"+loanbook.getId()+"' tabindex='-1' role='dialog'\r\n" + 
					"	aria-labelledby='exampleModalLabel' aria-hidden='true'>\r\n" + 
					"	<div class='modal-dialog modal-sm' role='document'>\r\n" + 
					"		<div class='modal-content'>\r\n" + 
					"			<div class='modal-header'>\r\n" + 
					"				<h4 class='modal-title' id='exampleModalLabel'>Fine Payment</h4>\r\n" + 
					"			</div>\r\n" + 
					"			<div class='modal-body'>\r\n" + 
					"				<form action='Payments' method='post'>\r\n" + 
					"					<div class='form-group'><input type='hidden' name='loanid' value='"+loanbook.getId()+"'>\r\n" + 
					"						<label for='firstName'>Amount ($)</label> <input type='text'\r\n" + 
					"							class='form-control' name='amount' id='amount'\r\n" + 
					"							placeholder='Amount' readonly='readonly' value='"+loanbook.computeLoan()+"'/>\r\n" + 
					"					</div>\r\n" + 
					"					<div class='form-group'>\r\n" + 
					"						<label for='paymentMode'>PAYMENT MODE</label> <input type='text'\r\n" + 
					"							class='form-control' name='paymentMode' id='paymentMode'\r\n" + 
					"							placeholder='Amount' readonly='readonly' value='CARD PAYMENT'/>\r\n" + 
					"					</div>\r\n" + 
					"					<div class='form-group'>\r\n" + 
					"						<label for='lastName'>Card Type</label>\r\n" + 
					"						<select name='cardType' class='form-control'>\r\n" + 
					"							<option hidden='true'>Choose card type </option>\r\n" + 
					"							<option value='VISA_CARD'>VISA CARD PAYMENT</option>\r\n" + 
					"							<option value='MASTER_CARD'>MASTER CARD PAYMENT</option>\r\n" + 
					"						</select>\r\n" + 
					"					</div>\r\n" + 
					"					<div class='form-group'>\r\n" + 
					"						<label for='cardNumber'>Card Number </label> <input type='text'\r\n" + 
					"							class='form-control' name='cardNumber' id='cardNumber'\r\n" + 
					"							placeholder='Card Number'/>\r\n" + 
					"					</div>\r\n" + 
					"					<button type='submit' class='btn btn-primary'>Approve Payment</button>\r\n" + 
					"				</form>\r\n" + 
					"			</div>\r\n" + 
					"		</div>\r\n" + 
					"	</div>\r\n" + 
					"</div>"
					);
			
			out.println("<!-- Modal -->\r\n" + 
					"<div class='modal fade' id='cashModal"+loanbook.getId()+"' tabindex='-1' role='dialog'\r\n" + 
					"	aria-labelledby='exampleModalLabel' aria-hidden='true'>\r\n" + 
					"	<div class='modal-dialog modal-sm' role='document'>\r\n" + 
					"		<div class='modal-content'>\r\n" + 
					"			<div class='modal-header'>\r\n" + 
					"				<h4 class='modal-title' id='exampleModalLabel'>Fine Payment</h4>\r\n" + 
					"			</div>\r\n" + 
					"			<div class='modal-body'>\r\n" + 
					"				<form action='Payments' method='post'>\r\n" + 
					"					<div class='form-group'><input type='hidden' name='loanid' value='"+loanbook.getId()+"'>\r\n" + 
					"						<label for='firstName'>Amount ($)</label> <input type='text'\r\n" + 
					"							class='form-control' name='amount' id='amount'\r\n" + 
					"							placeholder='Amount' readonly='readonly' value='"+loanbook.computeLoan()+"'/>\r\n" + 
					"					</div>\r\n" + 
					"					<div class='form-group'>\r\n" + 
					"						<label for='paymentMode'>PAYMENT MODE</label> <input type='text'\r\n" + 
					"							class='form-control' name='paymentMode' id='paymentMode'\r\n" + 
					"							placeholder='Amount' readonly='readonly' value='CASH PAYMENT'/>\r\n" + 
					"					</div>\r\n" + 
					"					<button type='submit' class='btn btn-primary'>Approve Payment</button>\r\n" + 
					"				</form>\r\n" + 
					"			</div>\r\n" + 
					"		</div>\r\n" +  
					"	</div>\r\n" + 
					"</div>");
		} else {
			out.println(
					"<tr><th colspan='2' class='alert alert-success' style='text-align:center'>Returned within the expected time</th></tr>");
			out.println("</table>");
			out.println("</div>");
			out.println("</div>");
			out.println(
					"<div class='panel-footer' style='text-align:center'><a href='BookReturned?loanid="+loanbook.getId()+"' class='btn btn-primary'>Return book</a></div>");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("cashPaymentModel.html").include(request, response);
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
