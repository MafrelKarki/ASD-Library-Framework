package edu.mum.asd.library.model;

public class ReservedState implements ItemState {

	private Loan loan;

	public ReservedState(Loan loan) {
		this.loan=loan;
	}
	
	@Override
	public void changeItemState() {
		loan.setReturnStatus("RES");
		loan.setCurrentState(new ReservedState(loan));
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}
