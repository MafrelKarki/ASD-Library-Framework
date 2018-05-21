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
import edu.mum.asd.library.model.CardPayment;
import edu.mum.asd.library.model.CardType;
import edu.mum.asd.library.model.CashPayment;
import edu.mum.asd.library.model.Loan;
import edu.mum.asd.library.model.Payment;
import edu.mum.asd.library.model.PaymentStrategy;

@WebServlet("/Payments")
public class Payments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payments() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int loanId=Integer.parseInt(request.getParameter("loanid"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		String paymentMode=request.getParameter("paymentMode");
		String cardNumber=request.getParameter("cardNumber");
		String cardType=request.getParameter("cardType");
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO paymentDao=idaofaccotry.getIDAO("PaymentDao");
		IDAO bookdao=idaofaccotry.getIDAO("BookDao");
		Loan loan=bookdao.getLoanedBook(loanId);
		Payment payment=new Payment(loan, amount, paymentMode);
		paymentDao.save(payment);
		loan.setReturnStatus("YES");
		paymentDao.updateLoan(loan);
		bookdao.returnBook(loan.getCallNo());
		if(cardNumber!=null && cardType!=null) {
			PaymentStrategy paymentStategy=new CardPayment(CardType.valueOf(cardType), cardNumber, amount);
			paymentStategy.pay();
		}else {
			PaymentStrategy paymentStategy=new CashPayment(amount);
			paymentStategy.pay();
		}
		
		List<Payment>payments=paymentDao.getPayments();
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
		out.println("<div class='panel panel-default'>");
		out.println("<div class='panel-heading'>FINE PAYMENTS </div>");
		out.println("<div class='panel-body'>");
		out.println("<div class='alert alert-info'>Total fine collected= $"+paymentDao.getTotalAmounts()+"</div>");
		out.println("<table class='table table-bordered table-striped'>");
		out.println(
				"<tr><th>Names</th><th>Book Title</th><th>Issued On</th><th>ExcessDays</th><th>Payed On</th><th>Payment Mode</th><th>Amount</th></tr>");
		for (Payment pay : payments) {
			out.println("<tr>" + "<td>" + pay.getLoan().getStudent().getFirstName()+" "+pay.getLoan().getStudent().getLastName() + "</td>" + "<td>" +bookdao.getBook(pay.getLoan().getCallNo()).getName() + "</td>" + "<td>"
					+ pay.getLoan().getIssuedDate() + "</td>" + "<td>" + pay.getLoan().getExcessDays() + "</td>" + "<td>" + pay.getPayedOn()
					+ "</td>" + "<td>" + pay.getPaymentMode() + "</td>" + "<td>$ "
					+ pay.getAmount() + "</td></tr>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>"); 
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}

}
