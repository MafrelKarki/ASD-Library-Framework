package edu.mum.asd.library.controller;


import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Loan;

public class CancelIssues implements IssueReturnCommand {

	@Override
	public int executeIssue(Loan bean) {
		return 0;
		
	}

	@Override
	public int executeReturn(String callno, int studentid) {
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO bookdao=idaofaccotry.getIDAO("BookDao");
		return  bookdao.returnBook(callno);
	}

	

}
