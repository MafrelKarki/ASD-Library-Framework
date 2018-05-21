package edu.mum.asd.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Book;
import edu.mum.asd.library.model.BookIterator;
import edu.mum.asd.library.model.BookStorage;

@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css'/>");
		out.println("<script src='https://code.jquery.com/jquery-1.12.4.js'></script> ");
		out.println("<script src='https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js'></script>");
		out.println("<script type='text/javascript'> " + "$(document).ready(function(){"
				+ "$('#booksTable').DataTable()" + "});" + "</script>");

		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);

		out.println("<div class='container'>");
		DAOFactory idaofaccotry = new DAOFactory();
		IDAO bookDao = idaofaccotry.getIDAO("BookDao");
		List<Book> list =new ArrayList<>();
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>Books in Library</div>");
		out.println("<div class='panel-body'>");
		BookStorage bookStorage = new BookStorage();
		for (Book book : bookDao.viewBook()) {
			bookStorage.addBook(book);
		}

		// implementing the iterator's hasNext method
		BookIterator iterator = new BookIterator(bookStorage.getBooks());
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}

		out.println("<table class='table table-bordered table-striped' id='booksTable'>");

		out.println(
				"<thead><tr><th>Callno</th><th>Name</th><th>Author</th><th>Publisher</th><th>Quantity</th><th>Issued</th><th>Delete</th></tr></thead>");
		out.println("<tbody>");
		for (Book bean : list) {
			out.println("<tr><td>" + bean.getCallno() + "</td><td>" + bean.getName() + "</td><td>" + bean.getAuthor()
					+ "</td><td>" + bean.getPublisher() + "</td><td>" + bean.getQuantity() + "</td><td>"
					+ bean.getIssued() + "</td><td><a href='DeleteBook?callno=" + bean.getCallno()
					+ "'>Delete</a></td></tr>");
		}
		out.println("</tbody>");
		out.println("</table>");

		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		// DAOFactory idaofaccotry=new DAOFactory();
		// IDAO bookDao=idaofaccotry.getIDAO("BookDao");
		// List<Book> list=bookDao.viewBook();

		// implementing iterator pattern to iterate through the books

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
