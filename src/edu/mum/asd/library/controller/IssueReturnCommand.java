package edu.mum.asd.library.controller;

import edu.mum.asd.library.model.Loan;

/**
 * @author Binyam
 * @purpose implementing command design pattern for issuing and returning book
 */
public interface IssueReturnCommand {
	public int executeIssue(Loan bean);

	public int executeReturn(String callno, int studentid);
}
