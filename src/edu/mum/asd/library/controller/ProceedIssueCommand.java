package edu.mum.asd.library.controller;

import edu.mum.asd.library.dao.BookDao;
import edu.mum.asd.library.model.Loan;

public class ProceedIssueCommand implements IssueReturnCommand {

	
	@Override
	public int executeIssue(Loan bean) {
		return BookDao.issueBook(bean);
		
	}

	@Override
	public int executeReturn(String callno, int studentid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
