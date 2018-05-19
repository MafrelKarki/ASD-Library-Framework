package edu.mum.asd.library.controller;

import edu.mum.asd.library.model.Loan;

public interface IssueReturnCommand {
public int executeIssue (Loan bean);
public int executeReturn (String callno,int studentid);
}
