package edu.mum.asd.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.asd.library.dao.IDAO;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO bookdao=idaofaccotry.getIDAO("BookDao");
		bookdao.delete(request.getParameter("callno"));
		response.sendRedirect("ViewBook");
	}
}
