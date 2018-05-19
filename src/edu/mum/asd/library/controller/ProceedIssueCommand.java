package edu.mum.asd.library.controller;


import edu.mum.asd.library.dao.IDAO;
import edu.mum.asd.library.model.Loan;

public class ProceedIssueCommand implements IssueReturnCommand {

	
	@Override
	public int executeIssue(Loan bean) {
		DAOFactory idaofaccotry=new DAOFactory();
		IDAO bookdao=idaofaccotry.getIDAO("BookDao");
		return bookdao.issueBook(bean);
		
	}

	@Override
	public int executeReturn(String callno, int studentid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
